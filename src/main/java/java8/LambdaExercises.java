package java8;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaExercises {
    public static void main(String[] args){

        //Use lambda with forEach to print a list of strings with "Hello " prefix.
        List<String> strList = Arrays.asList("Sachin", "Virat", "Dhoni");
        strList.forEach(s->System.out.println("Hello "+s));

        //Sort a list of integers in descending order using Comparator
        List<Integer> integerList = Arrays.asList(10,18,7,33,15,60);
        List<Integer> sortedDesc = integerList.stream()
                                                .sorted(Comparator.reverseOrder())
                                                .collect(Collectors.toList());
        System.out.println(sortedDesc);

        //Sort a list of integers in descending order using lambda Comparator
        List<Integer> sortedDesc1 = integerList.stream()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
        System.out.println(sortedDesc1);

        //Sort a list of integers in ascending order using Comparator
        List<Integer> sortedAsc = integerList.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(sortedAsc);

        //Sort a list of integers in ascending order using lambda Comparator
        List<Integer> sortedAsc1 = integerList.stream()
                .sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.toList());
        System.out.println(sortedAsc1);

        /*
            What It Does
            This lambda is a Comparator<Integer> that compares two integers a and b and returns a value to decide their order.

            compareTo is a method from Integer (implements Comparable).
            a.compareTo(b) returns:
            Negative if a < b
            Zero if a == b
            Positive if a > b

            For ascending order (default): Use (a, b) -> a.compareTo(b)
            (smaller first)
            For descending order (reverse): Use (a, b) -> b.compareTo(a)
            (larger first — by swapping a and b)
        */

        //Filter even numbers from a list using Predicate and lambda.
        List<Integer> evenNumbers = integerList.stream().filter(n->n != null && n % 2 == 0).collect(Collectors.toList());
        System.out.println("evenNumbers " +evenNumbers);

        //Alternative Without Stream (Direct List forEach - for Comparison)
        List<Integer> evens = new ArrayList<>();
        integerList.forEach(n -> {
            if (n % 2 == 0) evens.add(n);
        });
        System.out.println("Even numbers (forEach): " + evens);

        /*
        Edge Cases to Practice (Add These for Depth):
        Empty list: Arrays.asList() → returns empty.
        Null check: Add .filter(n -> n != null && n % 2 == 0) if needed.
        */

        /*
        //Alternative: Use Objects.nonNull(n) for cleaner code (Java 7+)
        .filter(Objects::nonNull)
                .filter(n -> n % 2 == 0)
        */

        //Use Consumer to print each element of a list with uppercase.
        System.out.println("Using Consumer to print each element of a list with uppercase : ");
        strList.forEach(p->System.out.println(p.toUpperCase()));

        //Use Function to convert a list of strings to uppercase.
        //The cleanest way is method reference
        List<String> upperList = strList.stream()
                .map(String :: toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Using Function to print each element of a list with uppercase : "+upperList);

        //Chain Function: Convert string to uppercase, then to length.
        List<Integer> length = strList.stream()
                .map(s->s!=null ? s.toUpperCase().length() : null)
                //.map(String::length)
                .collect(Collectors.toList());

        System.out.println("Chain Function: Convert string to uppercase, then to length : "+length);

        //Chain Function: Convert string to uppercase, then to length.
        List<Integer> length1 = strList.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println("Chain Function: Convert string to uppercase, then to length : "+length1);

        //Use Predicate to filter names starting with "A" from a list.
        List<String> strListStartsWithA = strList.stream()
                .filter(Objects::nonNull)
                .filter(s->s.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println("Using Predicate to filter names starting with A from a list : " +strListStartsWithA);

    }
}