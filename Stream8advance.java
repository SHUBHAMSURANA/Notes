import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class fun {
    public static void main(String[] args) {

        //To find the first unique character
        String t = "Shubham Surana";
        Character f = IntStream.range(0,t.length()).mapToObj(x->t.charAt(x)).filter(c -> c !=' ')
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(z -> z.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().
                orElse(null);

        Character fg = t.chars().mapToObj(x ->(char)x).filter(c -> c !=' ')
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(z -> z.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst().
                orElse(null);

        System.out.println(fg);

        String input = "Java articles are Awesome";
        input.chars().mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) == input.lastIndexOf(ch))
                .findFirst().orElse(null);

        // to find elemnt with heigest frequency by
    List<String> names = Arrays.asList("AA", "BB", "AA", "CC", "BB","AA","CC");
    names.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
            .entrySet()
            .stream().sorted(Map.Entry.comparingByValue((a,b) -> b.compareTo(a)))
            .map(x->x.getKey())
            .forEach((x)-> System.out.println(x));

    // To find the reverse of String t = "Shubham Surana";

    // To check palindrome

    String tt = "Madeam";
    boolean isPalindrome = IntStream.range(0, tt.length()/2)  // Create a stream of indices
            .allMatch(i -> tt.toLowerCase().charAt(i) == tt.toLowerCase().charAt(tt.length() - i - 1));
    // Compare characters from both ends

        System.out.println("Is the string a palindrome? " + isPalindrome);



    //Find 3rd heigest
    int[] arr = new int[] {1,4,5,6};
        Optional<Integer> xc = Arrays.stream(arr).boxed().sorted((a, b)-> b.compareTo(a))
                .skip(2)
                .limit(1).findFirst();

    //List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
    //2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
        Set<Integer> myList1 =  myList.stream().map(x -> String.valueOf(x)).filter(t->t.startsWith("1"))
                .map(x->Integer.valueOf(x)).collect(Collectors.toSet());

        System.out.println(myList1.toString());
    //3. How to find duplicate elements in a given integers list in java using Stream functions?

    //6. Given a list of integers, find the maximum value element present in it using Stream functions?
        Integer x =  myList.stream().max((a,b)-> a.compareTo(b) ).orElse(null);



    }
}

