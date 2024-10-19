import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;//Differnet ways to craete the thread:
class Mythread extends Thread {
    @Override
    public void run() {
        System.out.println("Running the thread 1");
    }
}
public class Threads {
    public static void main(String[] args) {
        // number 1 , by extending
        Mythread t1 = new Mythread();
        t1.start();

        // number 2
        // we have public Thread(Runnable target) constructor
        Thread t2 = new Thread(() -> {
            System.out.println("Running the thread 2");
        });
        t2.start();

        // number 3 with Anonymous Inner Class
        Runnable r = new Runnable() {
          @Override
          public void run() {
              System.out.println("Running the thread 3");
          }
        };

        // number 4 so that we can implement using the abobe lambda exparession
        // as runnable is functional Interface with one abstarct method
        Runnable z = () -> {
            System.out.println("Running the thread 4");
            System.out.println("thread name " + Thread.currentThread().getName());
        };

        Thread t4 = new Thread(z, "thread name dancer");
        t4.start();
        Thread t3 = new Thread(r);
        t3.start();

        // number 5 is also there check below
    }
}
output :
Running the thread 1
Running the thread 2
Running the thread 4
Running the thread 3
thread name thread name dancer

// Differet ways to handle race condition in thread
1. Using Synchronized Blocks
2. Using Reentrant Locks
3. Using Atomic Variables
4. Using Concurrent Collections
        private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
5. Using volatile Keyword

 ******Volatile******
 Visibility: volatile ensures that changes to a variable are visible to all threads
 Without volatile, threads might cache variables value from thread cache, but using volatile the value will be picked from RAM [Ram will have the updated value] and it will be shared same value across all the threads. 

public class VolatileExample {
    private volatile boolean flag = false;
    public void writer() {
        flag = true; // Writes to the volatile variable
    }

    public void reader() {
        while (!flag) {
            // Wait until the flag becomes true
            // Infinite loop will not run and we will save it
        }
        System.out.println("Flag is true!");
    }

    public static void main(String[] args) {
        VolatileExample example = new VolatileExample();

        // Creating the thread without run method
        Thread writerThread = new Thread(example::writer);
        Thread readerThread = new Thread(example::reader);
        
        readerThread.start();
        writerThread.start();
    }
}

Output: 
Flag is true!
*****Atomic*******
The use of Atomic classes is crucial in scenarios where you need to perform atomic operations on variables shared between multiple threads, ensuring data consistency without using explicit synchronization mechanisms like synchronized blocks or Locks. 
// AtomicInteger/AtomicLong/AtomicBoolean/AtomicReference<V>{Provides atomic operations on reference objects (like any custom object or non-primitive data types).}
public class AtomicCounterExample {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet(); // Atomic increment
    }

    public int getCount() {
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicCounterExample counter = new AtomicCounterExample();

        // Two threads incrementing the count
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());  // This will always be 2000
    }
}

***************
Thread State: Thread lifecycle

public class MyThread extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("CHILE THREAD 1 " + Thread.currentThread().getState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // see here we have not created any thread just object of the class act as thread
        MyThread t = new MyThread();
        System.out.println("CHILE THREAD 0 " + t.getState());
        t.start();
        System.out.println("CHILE THREAD 2 " + t.getState());
        Thread.sleep(100);
        System.out.println("CHILE THREAD 3 " + t.getState());
        // now first t will complete then Main thread will execute again if we use join()
        t.join();
        System.out.println("CHILE THREAD 4 " + t.getState());
        System.out.println("MAIN THREAD " + Thread.currentThread().getState());
    }
}

Output 
CHILE THREAD 0 NEW
CHILE THREAD 2 RUNNABLE
CHILE THREAD 3 TIMED_WAITING
CHILE THREAD 1 RUNNABLE
CHILE THREAD 4 TERMINATED
MAIN THREAD RUNNABLE


Purpose of Thread Priorities
Thread Scheduling: The priority of a thread is a hint to the JVM about how to prioritize the thread relative to other threads. A higher-priority thread may be given more CPU time than a lower-priority thread. However, the actual scheduling depends on the JVM and OS’s thread scheduling policies.
Prioritization: Setting priorities allows developers to influence the order in which threads are executed. For example, you might set a higher priority for a thread that performs critical tasks and a lower priority for background tasks.

highPriorityThread.setPriority(Thread.MAX_PRIORITY);
lowPriorityThread.setPriority(Thread.MIN_PRIORITY);

Thread.Interrupted;
[when it is called, thread will stop its procees if its in sleep or running and exception will be thrown which need to be handle by catch]

public class InterruptExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // Simulate work
                    Thread.sleep(1000);
                    System.out.println("Working...");
                }
            } catch (InterruptedException e) {
                // Handle the interruption
                System.out.println("Thread was interrupted");
                // Re-set the interrupt flag
                // Such that while loop stop the excution
                Thread.currentThread().interrupt();
                // another wat to stop just by return
                //return;
            }
        });

        worker.start();

        // Interrupt the thread after 3 seconds
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();

        // Check if the worker thread was interrupted 
         if (worker.isInterrupted()) {
            System.out.println("we will log the things");
        }
    }
}
*******************

public class RestartThreadExample implements Runnable {

@Override
public void run() {
    for (int i = 0; i < 5; i++) {
        System.out.println("Thread running: " + i);
        try {
            Thread.sleep(1000); // Simulating long-running task
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted, exiting...");
            return; // Exit when interrupted
        }
    }
}

public static void main(String[] args) throws InterruptedException {
    // Create and start the first thread
    Thread thread = new Thread(new RestartThreadExample());
    thread.start();

    Thread.sleep(2000); // Main thread waits for 2 seconds
    thread.interrupt(); // Interrupt the first thread

    // Wait for the first thread to finish execution
    thread.join();

    // Create and start a new thread since the previous one cannot be restarted
    System.out.println("Starting a new thread...");
    Thread newThread = new Thread(new RestartThreadExample());
    newThread.start();
}
}

output
Thread running: 0
Thread running: 1
Thread interrupted, exiting...
Starting a new thread...
Thread running: 0
Thread running: 1
Thread running: 2
Thread running: 3
Thread running: 4

Key learning
Interrupting the First Thread: The first thread is interrupted while it is sleeping, causing it to exit early. It only runs for 2 iterations before being interrupted.
Creating a New Thread: After the first thread finishes, the main thread creates a new thread and starts it. The new thread runs for 5 full iterations without interruption.

**************************
Thread.yield()
It will ask scheduler that , please now give chance to other thread, if two threads are running that each thraed will run periodically.

Daemon Thread: A daemon thread is a thread that runs in the background, When all non-daemon threads (user threads) have finished, the JVM will terminate, even if there are still daemon threads running.

background services such as logging, monitoring, or periodic maintenance tasks
The Java Virtual Machine (JVM) uses daemon threads for garbage collection.
Tasks that need to be executed periodically but do not affect the main application flow

public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Daemon thread is running...");
                } catch (InterruptedException e) {
                    System.out.println("Daemon thread interrupted.");
                }
            }
        });

        // Set the thread as daemon
        daemonThread.setDaemon(true);
        daemonThread.start();

        // Main thread sleeps for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is finishing. Daemon thread will be terminated.");
    }
}

public class ThreadExample {
    public void method1() {
        System.out.println("Method1 is running");
    }

    public static void main(String[] args) {
        ThreadExample example = new ThreadExample();
        
        // Create a Thread with method1 as the Runnable target
        Thread t1 = new Thread(example::method1);
        
        // Start the thread
        t1.start(); // This will internally call example.method1() in the new thread
    }
}

Thread t1 = new Thread(example::method1);  =  () -> example.method1()
            ||
public Thread(Runnable target) {
    this.target = target;
}
            ||
@FunctionalInterface
public interface Runnable {
    void run();
}
example::method1 is a method reference that is equivalent to a lambda expression () -> example.method1().
example::method1 matches the Runnable interface because method1 has no parameters and returns void, fitting the run method signature in the Runnable interface.

***********************************LOCK***************ReentrantLock**************************************
// bring 3 methods in mind lock(),trylock(),unlock(),lockintrruptibility()

public class Main {
    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();
        Thread t1 = new Thread(()->{
            sbi.withdraw(50);
        });

        Thread t2 = new Thread(()->{
            sbi.withdraw(50);
        });

        t1.start();
        t2.start();
    }
}

public class BankAccount {
    private int balance = 100;
    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            //Code version 1 [please run any one version]
            // IF T1 came first and taken the lock and after it t2 will come and wait for 1000ms to get lock, if did not get then go to else block.
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance>=amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(3000); // Simulate time taken to process the withdrawal
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                }
                else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            }
            else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try later");
            }
        }
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }

        //Code version 2
        // here it will work like syncrosied bock and t2 will keep trying again n agin to get lock , once t1 release the lock.
        lock.lock();
        if (balance>=amount) {
            try {
                System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                Thread.sleep(3000); // Simulate time taken to process the withdrawal
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
        else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }
}

***********************************Read Write Lock**************ReentrantReadWriteLock***************************
//A Read-Write Lock is a concurrency control mechanism that allows multiple threads to read shared data simultaneously while restricting write access to a single thread at a time. This lock type, provided by the ReentrantReadWriteLock

public class ReadWriteCounter {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public void increment() {
        writeLock.lock();
        try {
            count++;
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " incremented");
                }
            }
        };

        Thread writerThread = new Thread(writeTask);
        Thread readerThread1 = new Thread(readTask);
        Thread readerThread2 = new Thread(readTask);

        writerThread.start();
        readerThread1.start();
        readerThread2.start();

        writerThread.join();
        readerThread1.join();
        readerThread2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}

// Let prevent dead lock using the Thread communications
// wait(), notify() are used only when we have syncronsised method or block
class SharedResource {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + value);
        notify();
    }

    public synchronized int consume() {
        while (!hasData){
            try{
                wait();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        System.out.println("Consumed: " + data);
        notify();
        return data;
    }
}

class Producer implements Runnable {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resource.produce(i);
        }
    }
}

class Consumer implements Runnable {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = resource.consume();
        }
    }
}

public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();
    }
}

*********************Executors framework******************************************
//It comes by java 8 and motive is to simplify the many complexity involved in creating and managing threads
// how submit workss

submit() in AbstractExecutorService
     |
Creates RunnableFuture from Runnable or Callable
     |
Calls execute(RunnableFuture) in ThreadPoolExecutor
     |

Adds task to task queue or starts a new worker thread
     |
Worker threads pick tasks from the queue and execute them

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create a custom ThreadFactory
        ThreadFactory customThreadFactory = new ThreadFactory() {
            private int threadCount = 0;

            @Override
            public Thread newThread(Runnable r) {
                threadCount++;
                // Create a new thread with a custom name
                Thread thread = new Thread(r, "CustomThread-" + threadCount);
                // Optionally, set the thread priority
                thread.setPriority(Thread.NORM_PRIORITY);
                return thread;
            }
        };

        // Create a SingleThreadExecutor with the custom ThreadFactory
        ExecutorService executorService = Executors.newSingleThreadExecutor(customThreadFactory);

        ExecutorService x = Executors.newFixedThreadPool(5);
        ExecutorService y = Executors.newSingleThreadExecutor();
        // The newCachedThreadPool method creates a thread pool that can dynamically adjust the number of threads as needed.
        ExecutorService z = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + " is executing a task C.");
        },3, TimeUnit.SECONDS);


        // Submit tasks to the executor service
        // The Executors class is a utility class that provides factory methods for creating instances of ExecutorService
        // public class ThreadPoolExecutor extends AbstractExecutorService
        // public abstract class AbstractExecutorService implements ExecutorService
        // submit() in AbstractExecutorService
        // public Future<?> submit(Runnable task) {

        for (int i = 0; i < 10; i++) {
            // in submit we pass either Runnable or Callable refernce
            //this callabale/runnable task will be put in queue and and thread pool will fire on task .
            z.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is executing a task A.");
                    Thread.sleep(2000); // here task A will move to sleep not individual thread.
                }
                catch (InterruptedException e) {

                }
            });
        }

        // For public Future<?> submit(Runnable task)
        for (int i = 0; i < 10; i++) {
            // this lamba method will be assigned in q with 5 times and 5 thread at same time will pick and then it will sleep
            x.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is executing a task B.");
                    Thread.sleep(2000);
                }
                catch (InterruptedException e) {

                }
            });
        }
        //x.shutdown();
        //y.shutdown();

        // if we want main thread 2 wait for above task A and task b to complete for given below time.
        boolean t = x.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("The control will come here" + t); // false
        System.out.println("The control will come here");
    }
    // First task A will fully execute then task B. here task will added to queue then thread will pich and execute.
}

// Callable and runabale

public class Futureslearning {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future =  executorService.submit(() -> "AA");
        System.out.println("The value "+ future.get());

        // Callable is method which return some thing
        // Callable is functional interface with V call() throws Exception;
        Callable callable = () -> "nams";
        Future<?> future2 =  executorService.submit(callable);
        System.out.println("The value "+ future2.get());

        Runnable runnable = () -> System.out.println("jai ho");
        Future<?> future3 =  executorService.submit(runnable);
        System.out.println("The value "+ future3.get());

        Future<?> future4 =  executorService.submit(runnable,"sucess");
        System.out.println("The value "+ future4.get());

        List<Callable<String>> list = Arrays.asList(callable);
        List<Future<String>> futures = executorService.invokeAll(list);
        executorService.shutdown();

        //The get() method will block the calling thread until the computation is complete. This means that if the task is still executing, your current thread will wait (or block) until the task finishes executing, either successfully or exceptionally.

    }
}

*************************FUTURES ******************************

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // Create a thread pool

        // Array to hold Future objects
        Future<Integer>[] futures = new Future[5];

        // Submit tasks to the executor
        for (int i = 0; i < 5; i++) {
            final int taskId = i + 1;
            futures[i] = executorService.submit(() -> {
                // Simulate varying task execution time
                int sleepTime = taskId * 500; // Each task sleeps for 500ms * taskId
                Thread.sleep(sleepTime);
                return taskId; // Return the task ID
            });
        }

        // Main logic to demonstrate Future methods
        for (int i = 0; i < futures.length; i++) {
            try {
                Future<Integer> future = futures[i];

                // Check if the task is done
                System.out.println("Task " + (i + 1) + " done? " + future.isDone());

                // Get the result (will block until the result is available)
                Integer result = future.get(); // This will block if not done yet
                System.out.println("Task " + (i + 1) + " result: " + result);

                // Check if the task is done after getting the result
                System.out.println("Task " + (i + 1) + " done after get()? " + future.isDone());

            } catch (InterruptedException e) {
                System.out.println("Task " + (i + 1) + " was interrupted.");
                Thread.currentThread().interrupt(); // Restore interrupt status
            } catch (ExecutionException e) {
                System.out.println("Task " + (i + 1) + " failed with exception: " + e.getCause());
            }
        }

        // Attempt to cancel the last task
        try {
            boolean cancelSuccess = futures[4].cancel(true); // Attempt to cancel Task 5
            System.out.println("Cancellation of Task 5 was successful? " + cancelSuccess);
        } catch (Exception e) {
            System.out.println("Exception during cancellation: " + e.getMessage());
        }

        // Check if the last task is canceled
        System.out.println("Is Task 5 canceled? " + futures[4].isCancelled());

        // Shutdown the executor service
        executorService.shutdown();
        System.out.println("Executor service has been shut down.");
    }
}
//Task 1 done? false
//Task 1 result: 1
//Task 1 done after get()? true
//Task 2 done? false
//Task 2 result: 2
//Task 2 done after get()? true
//Task 3 done? false
//Task 3 result: 3
//Task 3 done after get()? true
//Task 4 done? false
//Task 4 result: 4
//Task 4 done after get()? true
//Task 5 done? false
//Task 5 result: 5
//Task 5 done after get()? true
//Cancellation of Task 5 was successful? false
//Is Task 5 canceled? false
//Executor service has been shut down.
