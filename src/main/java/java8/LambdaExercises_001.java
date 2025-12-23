package java8;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Supplier;

public class LambdaExercises_001 {

    public static void main(String[] args){

    //Declaration and initialization
    List<String> stringList = Arrays.asList("Sachin", "Shewag", "Gambhir","Virat", "", "Yuvraj", null,"Dhoni", "Raina");
    List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);

    //Use lambda with forEach to print a list of strings with "Hello " prefix.
    System.out.println("Using Lambda with forEach to print a List of String with Hello prefix: ");
    stringList.forEach(s -> System.out.print("Hello "+s+" "));
    System.out.println();

    //Sort a list of integers in descending order using lambda Comparator.
    List<Integer> descIntegerListUsingLambda = integerList.stream()
            .sorted((a,b)->b.compareTo(a))
            .collect(Collectors.toList());
    System.out.println("Sorting a list of integers in descending order using lambda Comparator :" +descIntegerListUsingLambda);

    //Sort a list of integers in ascending order using lambda Comparator.
    List<Integer> ascIntegerListUsingLambda = integerList.stream()
            .sorted((a,b)->a.compareTo(b))
            .collect(Collectors.toList());
    System.out.println("Sorting a list of integers in ascending order using lambda Comparator :" +ascIntegerListUsingLambda);

    //Sort a list of integers in descending order using Comparator method.
    List<Integer> descIntegerListUsingComparatorMethod = integerList.stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
    System.out.println("Sorting a list of integers in descending order using Comparator method :" +descIntegerListUsingComparatorMethod);

    //Sort a list of integers in descending order using Comparator method.
    List<Integer> ascIntegerListUsingComparatorMethod = integerList.stream()
            .sorted(Comparator.naturalOrder())
            .collect(Collectors.toList());
    System.out.println("Sorting a list of integers in ascending order using Comparator method :" +ascIntegerListUsingComparatorMethod);

    //Filter even numbers from a list using Predicate and lambda.
    List<Integer> evenNumbers = integerList.stream()
            .filter(n->n%2==0)
            .collect(Collectors.toList());
    System.out.println("Filtering even numbers from a list using Predicate and lambda :" +evenNumbers);

    //Use Consumer to print each element of a list with uppercase
    System.out.println("Using Consumer to print each element of a list with uppercase :");
    stringList.forEach(s-> System.out.println(s!=null ? s.toUpperCase() : null));

    //Create a Supplier that generates random numbers (1–100).
    Random random = new Random();
    Supplier<Integer> randomNumberSupplierFrom1To100 = () -> random.nextInt(100) +1;
    System.out.println("Generate Random number using the Supplier : "+randomNumberSupplierFrom1To100.get());

    /*
    What Does +1 Do in random.nextInt(100) + 1?

    random.nextInt(100) generates a random integer from 0 (inclusive) to 99 (inclusive) — total 100 possible values.
    By adding +1, you shift the range to 1 (inclusive) to 100 (inclusive).

    Without +1:

    Range: 0 to 99

    With +1:

    Range: 1 to 100 → exactly what the exercise asks ("random numbers 1–100").

    What Happens If You Use +2 Instead?

    random.nextInt(100) + 2 → Range: 2 to 101

    GENERIC FORMULA :
    random.nextInt(max - min + 1) + min
    In our case, random.nextInt(100 - 1 + 1) + 1, ie random.nextInt(100) + 1

    For 1 to 100:

    max = 100, min = 1
    nextInt(100 - 1 + 1) + 1 = nextInt(100) + 1 → correct!

    For 10 to 50:
    nextInt(50-10+1) + 10
    nextInt(41) + 10

     */

    //Use Function to convert a list of strings to uppercase
    System.out.println("Using Function to convert a list of non null and non empty strings to uppercase : "+randomNumberSupplierFrom1To100.get());
    stringList.stream()
            .filter(s -> s != null && !s.isEmpty())
            .map(String::toUpperCase)
            .forEach(System.out::println);

    //Chain Function: Convert string to uppercase, then to length.
    List<Integer> lengthOfStrings = stringList.stream()
            .filter(s->s!=null && !s.isEmpty())
            .map(String::toUpperCase) //s->s.toUpperCase()
            .map(String::length) //s->s.length()
            .collect(Collectors.toList());
    System.out.println("Chain Function: Converted string to uppercase, then to length, lengthOfStrings : "+lengthOfStrings);

    //Chain Function: Convert string to uppercase, then to length. (Combining 2 maps)
    List<Integer> lengthOfStrings1 = stringList.stream()
            .filter(s->s!=null && !s.isEmpty())
            .map(s->s.toUpperCase().length()) //s->s.toUpperCase()
            .collect(Collectors.toList());
    System.out.println("Chain Function: Converted string to uppercase, then to length, lengthOfStrings1 : "+lengthOfStrings1);

    //In interviews try to avoid storing stream and just print using forEach like the below code
    System.out.println("Chain Function: Converted string to uppercase, then to length : "+lengthOfStrings1);
    stringList.stream()
            .filter(s->s!=null && !s.isEmpty())
            .map(s->s.toUpperCase().length()) //s->s.toUpperCase()
            .forEach(System.out::println);

    //Use Predicate to filter names starting with "S" from a list.
    System.out.println("Using Predicate to filter names starting with \"S\" from a list : ");
    stringList.stream()
            .filter((s->s!=null && !s.isEmpty() && s.startsWith("S")))
            .forEach(System.out::println);

    //Implement a custom functional interface "Calculator" with add and subtract methods using lambda.
    Calculator add = (a,b) ->  a+b; //We can use Integer::sum also
    Calculator subtract = (a,b) ->  a-b;
    System.out.println("Using the custom functional interface implementation, adding two numbers 6, 4 is "+add.calc(6,4));
    System.out.println("Using the custom functional interface implementation, subtracting two numbers 6, 4 is "+subtract.calc(6,4));

    //Use lambda with Runnable to print "Hello from thread".
    /*
    What It Means

    Runnable is a functional interface with one abstract method: void run().
    You need to create a lambda that implements Runnable.
    Then run it in a Thread to print the message from a separate thread (not main thread).

    Why You Need a Thread

    If you just call run() directly, it executes in the main thread — no "from thread" effect.
    To see "Hello from thread" (and learn threading), you must create a Thread and start it.
     */

     Runnable runnable = () -> System.out.println("Hello from thread"); //Implementing the runnable interface
     runnable.run(); //Running from main thread

     Thread t1 = new Thread(runnable);
     t1.start(); //Running from Thread t1

     System.out.println("Just printing Hello from main class"); //Threads are non-deterministic, so printing orders may vary

     //Sort a list of employees by salary using Comparator lambda.



    }


}
