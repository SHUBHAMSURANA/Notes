import java.util.stream.IntStream;

public class OverloadedMain {
    // Standard main method
    //Types of Streams we have
        IntStream.of()
        LongStream.range()
        names.parallelStream()

    //Predicate<T>
    boolean test(T t)
    Predicate<String> isNotEmpty = s -> !s.isEmpty();
     System.out.println(isNotEmpty.test("Hello")); // Output: true

    //Function<T, R>
    R apply(T t)
    Function<String, Integer> stringLength = s -> s.length();
    System.out.println(stringLength.apply("Hello")); // Output: 5

    //Supplier<T>
    T get()
    Supplier<String> supplier = () -> "Hello, World!";
    System.out.println(supplier.get()); // Output: Hello, World!

   // Consumer<T>
    void accept(T t)
    Consumer<String> print = s -> System.out.println(s);
    print.accept("Hello, World!"); // Output: Hello, World!

    //Lambda expression
    //Lambda expressions provide a concise way to implement functional interfaces.
    //They enable you to write instances of single-method interfaces (functional interfaces) in a more compact form than anonymous inner classes.

    //In the context of Java's Stream API, a terminal operation is an operation that produces a result or a side-effect and terminates the stream pipeline.
    //EX: collect,forEach,reduce,count,anyMatch, allMatch, noneMatch,findFirst,findAny

    //peek() method is a part of the Stream API and is primarily used for debugging purposes.
    // It allows you to inspect the elements of a stream as they pass through the pipeline


    Optional<Integer> max = numbers.stream()
            .min((a,b) -> Integer.compare(a,b));

    Optional<User> user = findUserById("123"); user.ifPresent(u -> System.out.println(u.getName()));

    String name = findUserById("123")
            .map(User::getName)
            .orElse("Unknown User");


    // Noraml things to keep in mind
    int[] numbers = {1, 2, 3, 4, 5};
    // Create an IntStream from the array
    IntStream intStream = IntStream.of(numbers);

    //For primitive arrays, use Arrays.stream() to correctly stream individual elements.
    int[] numbers = {1, 2, 3, 4, 5};
    Arrays.stream(numbers).forEach(System.out::println);

    // Object arrays (e.g., String[], Integer[]) work directly with Stream.of().
    String[] fruits = {"Apple", "Banana", "Cherry"};
        Stream.of(fruits).forEach(System.out::println);

    // To convert the string into Chararcter array
    String t = "SAUBHAM";
    // way 1
    IntStream.range(0,t.length()).mapToObj(x->t.charAt(x)).
    // way 2
    t.chars().mapToObj(x ->(char)x).filter(c -> c !=' ')

    IntStream.of(1, 2, 3, 4, 5)
    .filter(n -> n % 2 == 0)  // Keep only even numbers
    .forEach(System.out::println);  // Output: 2, 4

    IntStream intStream = IntStream.rangeClosed(1, 5);

    // Perform some operations: filter, map, sum
    int sum = intStream
            .filter(n -> n % 2 == 0)  // Keep only even numbers
            .map(n -> n * n)          // Square each even number
            .sum();                   // Sum the squared numbers

    System.out.println("Sum of squared even numbers: " + sum);

    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
         names.stream()
        .filter(name -> name.startsWith("A"))
            .forEach(System.out::println);  // Output: Alice

    // Do not modify in foreach
    list.stream().forEach(e -> {
            System.out.println("the value of e" +e);
            if (e == 3) {
                list.remove(e);  // Throws ConcurrentModificationException
            }
    });

    // If we have filter alone , then nothing will execute , we need terminal operations like collect(), forEach(), reduce() or count()
    to run the stream.

    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
stream.filter(i -> {
        System.out.println("Filtering: " + i);
        return i % 2 == 0;
    });

    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
stream.filter(i -> {
        System.out.println("Filtering: " + i);
        return i % 2 == 0;
    }).forEach(System.out::println);


    // If you need to reuse a stream, you need to recreate it.
    Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
stream.forEach(System.out::println);  // Terminal operation


}

// optional with stream
Optional<Integer> firstEven = Stream.of(1, 3, 5, 6).filter(i -> i % 2 == 0).findFirst();

firstEven.ifPresentOrElse(
    value -> System.out.println("First even number: " + value),
    () -> System.out.println("No even numbers found")
);

// Method References
//wheneer we use method reference then "()" should not be used
//1) Static Method Reference

public class StaticMethodReferenceExample {
    public static void printUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(StaticMethodReferenceExample::printUpperCase);
        names.forEach(x -> Main.printUpperCase(x) );

        // StaticMethodReferenceExample::printUpperCase it is equal to (s) ->  StaticMethodReferenceExample.printUpperCase(s)
    }
}

2) Instance Method Reference on a Specific Object
import java.util.Arrays;
import java.util.List;

public class InstanceMethodReferenceExample {
    public void printUpperCase(String s) {
        System.out.println(s.toUpperCase());
    }

    public static void main(String[] args) {
        InstanceMethodReferenceExample instance = new InstanceMethodReferenceExample();
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(instance::printUpperCase);
    }
}

// need to deep drive into it
3)  Instance Method Reference on an Arbitrary Object
import java.util.Arrays;
import java.util.List;

public class ArbitraryObjectMethodReferenceExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.sort(String::compareToIgnoreCase);
        //(s1, s2) -> s1.compareToIgnoreCase(s2)
        System.out.println(names);
    }
}

4. Constructor Reference
Supplier<StringBuilder> stringBuilderSupplier = StringBuilder::new;
				or 
Supplier<StringBuilder> stringBuilderSupplier = () -> new StringBuilder();

// Various ways to use reduce

//it is a terminal operator mean TO reduce the stream by some operations

T reduce(T identity, BinaryOperator<T> accumulator)

identity: An initial value
accumulator: A function that takes two arguments

int sum = numbers.stream()
           .reduce(0, (a, b) -> a + b);

if we do not want to pass identity the use below
Optional<T> reduce(BinaryOperator<T> accumulator)
Optional<Integer> sum = numbers.stream()
        .reduce((a, b) -> a + b);
System.out.println("Sum: " + sum.orElse(0)); // Output: Sum: 15

Optional<Integer> max = numbers.stream()
        .min((a,b) -> Integer.compare(a,b));

// Various ways to use collect
// Collecting to a List
List<String> upperCaseNames = names.stream()
       .map(String::toUpperCase)
       .collect(Collectors.toList());

// Collecting to a Set (removes duplicates)
Set<String> nameSet = names.stream()
	.collect(Collectors.toSet());

// Collecting to a Map with the name as key and its length as value
Map<String, Integer> map = new HashMap<>();
map.put("apple", 100);
map.put("banana", 150);
map.put("cherry", 200);
Map<String, Integer> updatedMap = map.entrySet()
    .stream()
    .collect(Collectors.toMap(
            Map.Entry::getKey, //entry -> entry.getKey(),
            entry -> entry.getValue() + (entry.getValue() / 10)
    ));

// to sort by keys
// when we are arrabgubg the key then we need pass additionl 2 args (e1, e2) -> e1 and LinkedHashMap::new
Map<String, Integer> sortedByKey = map.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
        ));
// expanding the (e1, e2) -> e1, and LinkedHashMap::new
new BinaryOperator<Integer>() {
    @Override
    public Integer apply(Integer e1, Integer e2) {
        return e1;
    }
}
new Supplier<LinkedHashMap<String, Integer>>() {
    @Override
    public LinkedHashMap<String, Integer> get() {
        return new LinkedHashMap<>();
    }
};

// Joining strings with a delimiter
String joinedNames = names.stream()
	.collect(Collectors.joining(", "));                                      

IntSummaryStatistics stats = numbers.stream()
	.collect(Collectors.summarizingInt(Integer::intValue));

System.out.println("Count: " + stats.getCount());   // Output: Count: 5
System.out.println("Sum: " + stats.getSum());       // Output: Sum: 15

//Flat Map : 2d list ko 1d list mai karna
List<List<String>> listOfLists = Arrays.asList(
        Arrays.asList("A", "B", "C"),
        Arrays.asList("D", "E", "F"),
        Arrays.asList("G", "H", "I")
);

// Use flatMap with Collection::stream to flatten the lists
List<String> flattenedList = listOfLists.stream()
    .flatMap(Collection::stream)  // Flatten each inner list
    .collect(Collectors.toList()); // Collect into a List

    System.out.println(flattenedList);

List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Amanda");
// Grouping by can we used in 4 ways:
//classifier: A function that defines how to group the elements.
//mapFactory: A supplier that creates the Map instance.
//downstream: A collector that defines how the grouped elements should be accumulated (e.g., Collectors.toList(), Collectors.counting()).

a) when we only pass on arrgumnet , groupingBy(classifier)
Map<Character, List<String>> groupedByFirstLetter = names.stream()
        .collect(Collectors.groupingBy(name -> name.charAt(0)));

b) groupingBy(classifier,mapFactory)
to control the type of Map returned (e.g., TreeMap, LinkedHashMap, or HashMap).
Map<Character, List<String>> groupedByFirstLetter = names.stream()
        .collect(Collectors.groupingBy(name -> name.charAt(0), TreeMap::new));

c) groupingBy(classifier, downstream)
Map<Character, Long> groupedByFirstLetterCount = names.stream()
        .collect(Collectors.groupingBy(name -> name.charAt(0), Collectors.counting()));

d)groupingBy(classifier, mapFactory, downstream)
Map<Character, Long> groupedByFirstLetterCount = names.stream()
        .collect(Collectors.groupingBy(name -> name.charAt(0), LinkedHashMap::new, Collectors.counting()));

Integer[] numbers1 = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
Map<Integer,Long> d = Stream.of(numbers1).collect(Collectors.groupingBy(n-> n, Collectors.counting()));

int a[] = new int[] {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
Map<Integer,Long> c= IntStream.of(a).
        boxed(). // Convert each int to Integer (boxing)
        collect(Collectors.groupingBy(n-> n, Collectors.counting()));

char x[] = new char[]{'a','a','c','a','a','a','a','s','a'};
int a[] = new int[] {1, 3, 5, 6};
Map<Integer, Long> NM= IntStream.range(0,a.length).mapToObj(i->a[i])// Convert to Stream<Character>
        .collect(Collectors.groupingBy(c->c,Collectors.counting()));


 // Partitioning by the length of the names (longer than 4 characters)
 List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


// Partition numbers into even and odd
Map<Boolean, List<Integer>> partitioned = numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("Even numbers: " + partitioned.get(true));  // Output: [2, 4, 6, 8, 10]
        System.out.println("Odd numbers: " + partitioned.get(false));  // Output: [1, 3, 5, 7, 9]




