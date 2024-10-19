public class Main {
    public static void main(String[] args) {
        // Foreach
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> {
            if (name.startsWith("A")) {
                System.out.println(name + " starts with A");
            } else {
                System.out.println(name + " does not start with A");
            }
        });

        //or
        names.stream()
            .filter(name -> name.startsWith("A"))
            .forEach(name -> System.out.println(name + " starts with A"));

        HashMap<String, Integer> map = new HashMap<>();
        map.forEach((key, value) ->
                System.out.println(key + " is " + value + " years old")
        );

        map.keySet().forEach(key ->
                System.out.println("Key: " + key)
        );

        map.values().forEach(value ->
                System.out.println("Value: " + value)
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " is " + entry.getValue() + " years old");
        }

// To print the 2D array
        int[][] array = new int[rows][columns];
        System.out.println(Arrays.deepToString(array));

// return 1d array
        return new int[]{4,5};

// return empty 2d array
        return new int[0][];


// Sort 2D array intervals
        int[][] intervals = {3, 5},{1, 2},{4, 6},{2, 3}};
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

//    Original intervals:
//            [3, 5] [1, 2] [4, 6] [2, 3]
//    Sorted intervals:
//            [1, 2] [2, 3] [3, 5] [4, 6]

    // To covert ArrayList to array
        String[] array = list.toArray(new String[0]);

// Convert list to 2D array and return
        return merged.toArray(new int[merged.size()][]);

//Convert 1d array to array list
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
    }
    //If we need min heap then use :
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    //If we need max heap
    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

    //Leet code learning
    int sum = Integer.MIN_VALUE;
    sum = Math.max(sum,t);
}


Checked Exceptions: [Complie time exception]
Must be caught or declared in the method signature.[like Throws IOException] 
Represent conditions that a program should handle explicitly.
Example: IOException, SQLException.

Unchecked Exceptions: [Run Time exception]
Do not need to be caught or declared.
Represent programming errors or bugs that are often not anticipated.
Example: NullPointerException, ArrayIndexOutOfBoundsException.


1. Try-With-Resources (try () { ... })
When to Use:
1) When working with resources that implement the AutoCloseable or java.io.Closeable interface.
2) To ensure automatic resource management and avoid potential resource leaks.

public class BufferedReaderWriterExample {
    public static void main(String[] args) {
        // Writing to a file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("example.txt"))) {
            bw.write("BufferedWriter example");
            bw.newLine();
            bw.write("BufferedWriter is efficient for writing large data.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading from a file
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

2. Traditional Try-Catch-Finally (try { ... })
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TraditionalTryCatchFinallyExample {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("example.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // here we have to write close logoic
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//Throws Clause: The main method declares that it throws IOException.
//This means that if an IOException occurs, it will propagate up to the JVM, which will terminate the program if not handled.
//In Java, the throws keyword is used to declare that a method can throw certain exceptions. It can only be used at the method level, not at the class level.

public class BufferedReaderWriterExample  {
    public static void main(String[] args) throws IOException {
        // Reading from a file
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("exampsle.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("ghjgj");
        }

        // Writing to a file
        BufferedWriter bw = null;
       try {
            bw = new BufferedWriter(new FileWriter("example.txt"));
           bw.write("BufferedWritevvbnbnbr example");
           bw.newLine();
           bw.write("BufferedWriter is efficient for writing large data.");
       }
       catch (Exception e) {
           e.printStackTrace();
       }

       finally {
           // Ensure that the BufferedWriter is closed properly
           if (bw != null) {
               bw.close();
           }
       }
    }
}

public class Traditionaltrycatchfinallyexample {
    public static void main(String[] args) throws IOException{
        BufferedReader br = null;
        // here filerEDADER THROW THE exception so we have already declared using throws
        br = new BufferedReader(new FileReader("example.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close(); 
    }
}

Question 4: Exception Handling with return in finally
public class FinallyReturn {
    public static void main(String[] args) {
        System.out.println(method());
    }

    public static int method() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }
}

//Output 
2

//The ASCII value of uppercase letters ('A' to 'Z') is in the range of 65 to 90, while the corresponding lowercase letters ('a' to 'z') are in the range of 97 to 122. The difference between the ASCII values of an uppercase letter and its lowercase equivalent is 32.
public class LowercaseConverter {
    public static String convertToLowercase(String input) {
        for (char c : chars) {
            // Check if the character is uppercase
            if (c >= 'A' && c <= 'Z') {
                // Convert to lowercase by adding 32 to the ASCII value
                c = (char) (c + 32);
            }
            result.append(c); // Append the character to the result
        }

        int[] count = new int[26]; // Assuming only lowercase letters

        // Count character frequencies using an integer array.
        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s2.toCharArray()) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) {
                return false;
            }
        }
    }
}

// all use case for Comapareble and Comporator in depth:
//1. Comparable Interface : It requires the class to implement a compareTo() method, it is use full for natural ordering.[For example, for numbers, the natural order is ascending (1, 2, 3, ...), and for strings, it is typically lexicographical (alphabetical) order.]

// Comparable has compareTo method
// Comporator has compare method ,
//You can call compareTo() directly in a custom Comparator to compare objects based on their natural ordering.

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Implement the compareTo() method to define natural order (by age)
    @Override
    public int compareTo(Person other) {
        return this.age - other.age;  // Sort by age (natural order)
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        Collections.sort(people);  // Sorts using the compareTo() method
        System.out.println(people);
    }
}
// Using Comparator Without Modifying the Class

Comparator<Person> ageComparator = new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.age - p2.age;  // Sort by age
    }
};

Collections.sort(people, ageComparator);  // Sort using Comparator
Comparator<Person> nameComparator = (p1, p2) -> p1.name.compareTo(p2.name);
Comparator<Person> ageComparator = (p1, p2) -> p1.age - p2.age;

Collections.sort(numbers, (o1, o2) -> o2.compareTo(o1));

List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 2, 7);
 numbers.sort((o1, o2) -> o2.compareTo(o1));

        import java.util.*;

//Question:
//Consider a scenario where you have a Student class with name and score fields. You want to sort a list of Student objects primarily by score in descending order and, if two students have the same score, by name in ascending order. Write a Comparator that achieves this and explain how it handles the tie-breaking scenario. Also, discuss any potential pitfalls with this approach.

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", 90),
                new Student("Bob", 85),
                new Student("Charlie", 90),
                new Student("David", 85),
                new Student("Eve", 95)
        );

        Comparator<Student> l = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int scoreComparison = Integer.compare(o2.getScore(), o1.getScore());
//                if s2.getScore() is greater than s1.getScore(), scoreComparison will be positive (1).
//                If s2.getScore() is less than s1.getScore(), scoreComparison will be negative (-1).
//               If the scores are equal, scoreComparison will be 0.

                if (scoreComparison == 0) {
                    return o1.getName().compareTo(o2.getName());
                }
                return scoreComparison;
            }
        };

        students.forEach(x -> System.out.println("NAME  " +x.getName() + "   " + "score  "+x.getScore()));

        //****** way number 1*********
        //Collections.sort(students,l);
        //****** way number 2*********
        //students.sort(l);
        //****** way number 3*********
        students.sort((o1,o2) -> {
            int scoreComparison = Integer.compare(o2.getScore(), o1.getScore());
            if (scoreComparison == 0) {
                return o1.getName().compareTo(o2.getName());
            }
            return scoreComparison;
        });

        List<Integer> numbers = Arrays.asList(5, 3, 8, 1, 2, 7);
        numbers.sort((o1, o2) -> o2.compareTo(o1));

        System.out.println("*********after**************");
        students.forEach(x -> System.out.println("NAME  " +x.getName() + "   " + "score  "+x.getScore()));
    }
}






