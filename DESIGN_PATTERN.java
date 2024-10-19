abstract class Vehicle {
//    Creational Pattern
//1. Singleton Pattern
//2. Factory Method Pattern
//3.Builder Pattern
//4. Prototype Pattern
//
//1)Factory
//    It helps in creating the object without exposing the OBJECT creation logic to the client, we will have a class and method and which class object you want it will give.
//    it help us to achieve dependency inversion principle and open/closed principle
// Factory class responsible for creating objects

class VehicleFactory {
    // Factory method to create objects
    // Vehicle can be interafce
    public static Vehicle getVehicle(String vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        if (vehicleType.equalsIgnoreCase("CAR")) {
            return new Car();
        } else if (vehicleType.equalsIgnoreCase("TRUCK")) {
            return new Truck();
        } else if (vehicleType.equalsIgnoreCase("MOTORCYCLE")) {
            return new Motorcycle();
        }
        return null;
    }
}

    //    Immuatble Class:
//1) class should be declared as final to prevent subclassing
//2) All fields should be private to ensure they cannot be accessed or modified directly from outside the class.
// 3) All fields should be marked as final so that they can only be assigned once
//4)No Setters: method
//5)All fields should be initialized via the constructor
    // Immutable Class in Java
    public final class Person {

        // Fields are private and final
        private final String name;
        private final int age;

        // Constructor to initialize fields
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Only getters, no setters
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        // Overriding toString() for better readability
        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    //6)Deep Copy for Mutable Objects: If the class has fields that are mutable objects (like arrays or other classes)
    // Constructor initializes all fields
    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        // Use deep copy to ensure immutability
        this.address = new Address(address.getStreet(), address.getCity());
    }
}



2)Builder
Builder: A helper that lets you set values one by one.
Product: The object you're creating.
Build: After setting everything, you "build" the final object.

class Car {
    private String engine;
    private String color;
    private int wheels;
    private boolean hasGPS;

    // Private constructor to prevent direct instantiation
    private Car(SportCarBuilder builder) {
        this.engine = builder.engine;
        this.color = builder.color;
        this.wheels = builder.wheels;
        this.hasGPS = builder.hasGPS;
    }

    private Car(TruckBuilder builder) {
        this.engine = builder.engine;
        this.color = builder.color;
        this.wheels = builder.wheels;
        this.hasGPS = builder.hasGPS;
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", color=" + color + ", wheels=" + wheels + ", hasGPS=" + hasGPS + "]";
    }

    // Builder for sport cars
    //the inner class is often made static to facilitate clean and flexible object construction without needing an instance of the outer class. Here's why the Builder inner class is usually static
    public static class SportCarBuilder {
        private String engine;
        private String color;
        private int wheels;
        private boolean hasGPS;

        public SportCarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public SportCarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public SportCarBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public SportCarBuilder setGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        public Car build() {
            this.wheels = 4; // Default for sport cars
            return new Car(this);
        }
    }

    // Builder for trucks
    public static class TruckBuilder {
        private String engine;
        private String color;
        private int wheels;
        private boolean hasGPS;

        public TruckBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public TruckBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public TruckBuilder setWheels(int wheels) {
            this.wheels = wheels;
            return this;
        }

        public TruckBuilder setGPS(boolean hasGPS) {
            this.hasGPS = hasGPS;
            return this;
        }

        public Car build() {
            this.wheels = 6; // Default for trucks
            return new Car(this);
        }
    }
}

public class TestMultipleBuilders {
    public static void main(String[] args) {
        // Build a sport car
        Car sportCar = new Car.SportCarBuilder()
                .setEngine("V8")
                .setColor("Red")
                //.setGPS(true)
                .build();

        System.out.println(sportCar);

        // Build a truck
        Car truck = new Car.TruckBuilder()
                .setEngine("Diesel")
                .setColor("Blue")
                //.setGPS(false)
                .build();

        System.out.println(truck);
    }
}



3)Singleton: we need to follow 3 things
// Object will be created once
class DatabaseConnection {
    // Step 1 Ensures that there is only one instance at the class level and that it is shared across all parts of the application.
    private static DatabaseConnection instance;
    private String connectionString; // Simulate a database connection string

    // Step 2 Private constructor to prevent instantiation from outer class.
    private DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        // Simulate initializing the database connection
        System.out.println("Database connection established: " + connectionString);
    }

    // Step 3 Public method to provide access to the instance
    public static DatabaseConnection getInstance(String connectionString) {
        if (instance == null) { // Lazy initialization
            instance = new DatabaseConnection(connectionString);
        }
        return instance;
    }

    public void executeQuery(String query) {
        // Simulate executing a query
        System.out.println("Executing query: " + query);
    }

    protected Object readResolve() {
        return instance;
    }
}

class UserService {
    public void getUser() {
        DatabaseConnection dbConnection = DatabaseConnection.getInstance("jdbc:mysql://localhost:3306/mydb");
        dbConnection.executeQuery("SELECT * FROM users");
    }
}

public class Main {
    public static void main(String[] args) {
        // Create service instances
        UserService userService = new UserService();

        // Call methods that use the singleton
        userService.getUser();

        // Verify that both services use the same database connection instance
        DatabaseConnection dbConnection1 = DatabaseConnection.getInstance("jdbc:mysql://localhost:3306/mydb");
        DatabaseConnection dbConnection2 = DatabaseConnection.getInstance("jdbc:mysql://localhost:3306/mydbTEST***");
        // If you pass different connection strings, the existing Singleton instance will not change to accommodate the new connection string.

        // This will print true, confirming both references point to the same instance
        System.out.println(dbConnection1 == dbConnection2);
    }
}

// How we can break the sigelton design pattern
// 1. Serialization can break the Singleton Design Pattern in Java by creating multiple instances of the Singleton class when an object is serialized and deserialized
public class SingletonTest {
    public static void main(String[] args) throws Exception {
        Singleton instance1 = Singleton.getInstance();

        // Serialize the instance
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        oos.writeObject(instance1);
        oos.close();

        // Deserialize the instance
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
        Singleton instance2 = (Singleton) ois.readObject();
        ois.close();

        // Check if both instances are the same
        System.out.println("instance1 hashCode: " + instance1.hashCode());
        System.out.println("instance2 hashCode: " + instance2.hashCode());
    }
}
//output
//instance1 hashCode: 12345678
//instance2 hashCode: 87654321

// Fix : put in signeleon class
protected Object readResolve() {
    return instance;
}
// using the reflection api we can breake but uing enum we can fix



4)Prototype
The concept is to copy the existing object rather than creating new object from scratch, always creating new object is costly, mean creating object to connect to db or network again and again is not nice.

Shallow Clone: if an object as some inner object , then when we clone and new object will have refrence for inner object.[inner object will not copied]
Deep clone : entire object is copied and new object is created.

package learnProtype;

// Cloneable is markup intetface , that does not have any method it just used to give hint
// to compiler and provide metadata at runtime
public class NetworkConnection implements Cloneable{
    private String ip;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void data() throws InterruptedException {
        this.data = "very very impoartaim teime";
        Thread.sleep(2000);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // To have deep cloning we need add below code
        // NetworkConnection networkConnection = new NetworkConnection();
        // networkConnection.setIp(this.getIp());
        //networkConnection.setData(this.getData());
        //return networkConnection;
        // this will give us Shallow copying
        return super.clone();
        //you invoke the clone() method of the Object class, which handles creating a copy of the current object.
    }

    @Override
    public String toString() {
        return "NetworkConnection{" +
                "ip='" + ip + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

package learnProtype;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("creating new object");
        NetworkConnection networkConnection = new NetworkConnection();
        networkConnection.setIp("192:23:232");
        networkConnection.data();

        System.out.println(networkConnection);

        // we need new object
        try {
            //This is example for swallow copy if the we have some inner objects
            NetworkConnection networkConnection1 = (NetworkConnection) networkConnection.clone();
            System.out.println(networkConnection);
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

Structural Pattern
1)Facade
2)Adapter
3)Decorator


Behavioral Pattern
1)Strategy
2)Observer
3)State

*****************Facade**********************
It is used when we need to hide the system complexity from the client.
Step 1: Subsystem Classes
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is on.");
    }

    public void play(String movie) {
        System.out.println("Playing " + movie);
    }

    public void stop() {
        System.out.println("Stopping the DVD Player.");
    }

    public void off() {
        System.out.println("DVD Player is off.");
    }
}

class Projector {
    public void on() {
        System.out.println("Projector is on.");
    }

    public void setInput(DVDPlayer dvdPlayer) {
        System.out.println("Projector input set to DVD Player.");
    }

    public void off() {
        System.out.println("Projector is off.");
    }
}

class SoundSystem {
    public void on() {
        System.out.println("Sound system is on.");
    }

    public void setVolume(int level) {
        System.out.println("Setting volume to " + level);
    }

    public void off() {
        System.out.println("Sound system is off.");
    }
}

class Lights {
    public void dim(int level) {
        System.out.println("Dimming lights to " + level + "%");
    }

    public void on() {
        System.out.println("Lights are on.");
    }
}

Step 2: Facade Class
class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Lights lights;

    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem, Lights lights) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(50);
        projector.on();
        projector.setInput(dvdPlayer);
        soundSystem.on();
        soundSystem.setVolume(10);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down...");
        dvdPlayer.stop();
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
        lights.on();
    }
}
public class FacadePatternDemo {
    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Lights lights = new Lights();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvdPlayer, projector, soundSystem, lights);

        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}

        ********Observer Desin Patter**************
It is used to notify all the subscriber in one go.