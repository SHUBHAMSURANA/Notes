public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int d = Integer.parseInt(firstMultipleInput[1]);

    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

    bufferedReader.close();
}

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

// Array Concept
int[][] array = new int[rows][columns]; 
System.out.println(Arrays.deepToString(array)); 

// return 1d array
return new int[]{4,5};

// return empty 2d array
 return new int[0][];

// Sort intervals by the starting value of each interval
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));  


// To covert ArrayList to array
String[] array = list.toArray(new String[0]);

// Convert list to 2D array and return
return merged.toArray(new int[merged.size()][]);

//Convert 1d array list
int[] array = {1, 2, 3, 4, 5};
ArrayList<Integer> arrayList = new ArrayList<>();
arrayList = new ArrayList<>(Arrays.asList(array));

//Convert 2d array to array list
int[][] array2D = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
ArrayList<ArrayList<Integer>> arrayList2D = new ArrayList<>();
for (int[] row : array2D) {
    ArrayList<Integer> innerList = new ArrayList<>(Arrays.asList(row));
    arrayList2D.add(innerList);
}



@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}

// use of Comparator for the class object
List<Person> personList = new ArrayList<>();
personList.add(new Person("Alice", 25));
personList.add(new Person("Bob", 30));
personList.add(new Person("Charlie", 20));

// Create a Comparator to sort Person objects based on age
Comparator<Person> ageComparator = new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
};

Comparator<Person> reversedAgeComparator = Comparator.comparingInt(Person::getAge).reversed();

// Sort the personList using the ageComparator
Collections.sort(personList, ageComparator);





If we need min heap then use : 
PriorityQueue<Integer> pq = new PriorityQueue<>(); 

If we need max heap 
PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); 

//Leet code learning
int sum = Integer.MIN_VALUE;
sum = Math.max(sum,t);














