package java8;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Java8CodingExercises_001 {
    public static void main(String[] args) {

        //Declaration and initialization
        List<String> stringList = Arrays.asList("Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "Raina");
        List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);
        String nonNullValueString = "Funkynshot";
        String nullValueString = null;

        //Use lambda with forEach to print a list of strings with "Hello " prefix.
        System.out.println("Use lambda with forEach to print a list of strings with \"Hello\" prefix : ");
        stringList.forEach(s -> {
            if (s != null && !s.isEmpty()) {
                System.out.println("Hello " + s);
            }
        });

        //Sort a list of integers in descending order using lambda Comparator.
        List<Integer> integerListDesc = integerList.stream()
                .filter(Objects::nonNull)
                .sorted((a, b) -> b.compareTo(a))
                .toList();

        System.out.println("Sort a list of integers in descending order using lambda Comparator :" + integerListDesc);

        //Filter even numbers from a list using Predicate and lambda.
        List<Integer> evenIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Filter even numbers from a list using Predicate and lambda :" + evenIntegerList);

        //Use Consumer to print each element of a list with uppercase.
        List<String> stringListUpperCase = stringList.stream()
                .filter(s -> s != null && !s.isEmpty())
                .map(String::toUpperCase)
                .toList();
        System.out.println("Use Consumer to print each element of a list with uppercase :" + stringListUpperCase);

        //Create a Supplier that generates random numbers (1–100).
        Random random = new Random();
        //Supplier supplier = random.nextInt(100-1+1)+1;
        Supplier supplier = () -> random.nextInt(100 - 1 + 1) + 1;
        System.out.println("Create a Supplier that generates random numbers (1–100) : " + supplier.get());
        System.out.println("Create a Supplier that generates random numbers (1–100) : " + supplier.get());

        //Use Function to convert a list of strings to uppercase.
        List<String> stringListUpperCase1 = stringList.stream()
                .filter(s -> s != null && !s.isEmpty())
                .map(String::toUpperCase)
                .toList();
        System.out.println("Use Function to convert a list of strings to uppercase :" + stringListUpperCase1);

        //Chain Function: Convert string to uppercase, then to length.
        List<Integer> stringLength = stringList.stream()
                .filter(s -> s != null && !s.isEmpty())
                .map(s -> s.toUpperCase().length())
                .toList();
        System.out.println("Chain Function: Convert string to uppercase, then to length :" + stringLength);

        //Use Predicate to filter names starting with "S" from a list.
        List<String> stringContainsSList = stringList.stream()
                .filter(s -> s != null && !s.isEmpty() && s.startsWith("S"))
                .toList();
        System.out.println("Use Predicate to filter names starting with \"S\" from a list :" + stringContainsSList);

        //Implement a custom functional interface "Calculator" with add and subtract methods using lambda.
        Calculator add = (a, b) -> a + b; //Integer::Sum
        Calculator subtract = (a, b) -> a - b;
        System.out.println("Implement a custom functional interface \"Calculator\" with add and subtract methods using lambda, calc(6,4), addition :" + add.calc(6, 4));
        System.out.println("Implement a custom functional interface \"Calculator\" with add and subtract methods using lambda, calc(6,4), subtraction :" + subtract.calc(6, 4));

        //Use lambda with Runnable to print "Hello from thread"
        Runnable runnable = () -> System.out.println("Hello from thread");
        runnable.run(); //Running from main thread
        Thread t1 = new Thread(runnable);
        t1.start(); //Running from thread t1
        System.out.println("Printing Hello from main"); //Just to differentiate the thread and straight printing from main

        //Sort a list of employees by salary using Comparator lambda.
        List<Employee> employeeList = new ArrayList<Employee>();
        Employee e1 = new Employee(10, "Sachin", 600000);
        Employee e2 = new Employee(18, "Virat", 400000);
        Employee e3 = new Employee(7, "Dhoni", 500000);
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);
        System.out.println("employeeList : " + employeeList);

        List<Employee> employeeListBySalary = employeeList.stream()
                .filter(Objects::nonNull)
                .sorted((a, b) -> Integer.compare(a.getSalary(), b.getSalary()))
                .toList();
        System.out.println("Sort a list of employees by salary using Comparator lambda : " + employeeListBySalary);

        //Find highest paid employee
        Employee highestPaidEmployee = employeeList.stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
        System.out.println("Find highest paid employee : " + highestPaidEmployee);

        //Find lowest paid employee
        Employee lowestPaidEmployee = employeeList.stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
        System.out.println("Find lowest paid employee : " + lowestPaidEmployee);

        //Find second highest paid employee
        Employee secondHighestPaidEmployee = employeeList.stream()
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("Find second Highest Paid Employee : " + secondHighestPaidEmployee);

        //Group a list of strings by length using Collectors.groupingBy with lambda.
        Map<Integer, List<String>> stringListByLength = stringList.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.groupingBy(s -> s.length())); // We can use String::length instead
        System.out.println("Group a list of strings by length using Collectors.groupingBy with lambda : " + stringListByLength);

        //Group a list of strings by length using Collectors.groupingBy with lambda, ascending order.
        Map<Integer, List<String>> stringListByLengthAsc = stringList.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()));
        System.out.println("Group a list of strings by length using Collectors.groupingBy with lambda, ascending order : " + stringListByLengthAsc);


        //Group a list of strings by length using Collectors.groupingBy with lambda, descending order.
        Map<Integer, List<String>> stringListByLengthDesc = stringList.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.groupingBy(String::length, () -> new TreeMap<>(Comparator.reverseOrder()), Collectors.toList()));
        System.out.println("Group a list of strings by length using Collectors.groupingBy with lambda, descending order : " + stringListByLengthDesc);

        //Use Optional with lambda: If value present, print it; else print default.
        Optional<String> optionalWithNonNullValueString = Optional.of(nonNullValueString);
        Optional<String> optionalWithNullValueString = Optional.ofNullable(nullValueString);
        //Case 1 : if value present
        optionalWithNonNullValueString.ifPresentOrElse(
                (value) -> System.out.println(value),
                () -> System.out.println("default")
        );
        //Case 2 if value not present
        optionalWithNullValueString.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("default")
        );

        //Create a Predicate that checks if a number is prime (using lambda).
        //A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
        Predicate<Integer> isPrime = (n) -> {
            if (n <= 1) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
                if (n % i == 0) return false;
            }
            return true;
        };

        // Test the Predicate (Predicate<Integer> has test(T t) method returning boolean)
        System.out.println("Is 17 prime? " + isPrime.test(17));  // true
        System.out.println("Is 15 prime? " + isPrime.test(15));  // false
        System.out.println("Is 2 prime? " + isPrime.test(2));    // true
        System.out.println("Is 1 prime? " + isPrime.test(1));    // false
        System.out.println("Is 29 prime? " + isPrime.test(29));  // true

        //Use lambda with Stream: Filter names starting with "S", map to uppercase, collect to list.
        List<String> stringStartsWithSInUpperCase = stringList.stream()
                .filter(s -> s != null && !s.isEmpty() && s.startsWith("S"))
                .map(String::toUpperCase)
                .toList();

        System.out.println("Use lambda with Stream: Filter names starting with \"S\", map to uppercase, collect to list " + stringStartsWithSInUpperCase);

        //Implement a method reference for static method (e.g., String::toUpperCase).
        Function<String, String> toUpperCaseMethod = String::toUpperCase; //It matches Function<String, String> takes String input and returns String output
        String input = "Funkynshot";
        String output = toUpperCaseMethod.apply(input); //We use apply method to call functional interface method

        System.out.println("input : " + input);
        System.out.println("Uppercase using static method reference, output: " + output);

        //Implement a method reference for static method (e.g., Integer::valueOf).
        Function<String, Integer> stringToInteger = Integer::valueOf; //It matches Function<String, Integer>, takes String as input, returns Integer as output
        String integerInString = "123";
        Integer integerValueFromString = stringToInteger.apply(integerInString);
        System.out.println("input : " + integerInString);
        System.out.println("Parsing as Integer using static method reference, output: " + integerValueFromString);

        //Implement a method reference for static method (e.g., Math::abs).
        Function<Integer,Integer> mathAbs = Math::abs ; //It matches Function<Integer, Integer> takes Integer as input and returns Integer as output
        System.out.println("Absolute number of -10 using static method reference is " + mathAbs.apply(-10));

        //Use constructor reference to create a new ArrayList.
        Supplier<ArrayList<Integer>> supplyNewArrayList = ArrayList::new ;
        ArrayList<Integer> newArrayList = supplyNewArrayList.get();
        newArrayList.add(10);
        newArrayList.add(7);
        newArrayList.add(18);
        System.out.println("new ArrayList created using constructor reference is "+newArrayList);

        //Handle checked exception in lambda (wrap in try-catch or custom functional interface).
        /*
        Standard functional interfaces don't declare checked exceptions.
        nullPointerException is unchecked exception (because its thrown at runtime)
        Example for CheckedExceptions are IOException, ParseException

        Two ways:
        Wrap in try-catch inside lambda.
        Create custom functional interface that throws the exception, then handle it outside.
        */

        //Using try-catch inside lambda.
        /*
        stringList.forEach(
                s->{
                    try{
                        if(s.startsWith("S")){
                            throw new IOException("String starts with S");
                        }
                        System.out.println("String not starting with S : "+s);
                    }catch (IOException e){
                        System.out.println("We caught checked exception : "+e);
                    }
                }
        );

         */

        /*
        //Using custom Functional Interface
        ThrowCheckedException<String> tce = s->{

                if(s.startsWith("S")){
                    throw new IOException("String starts with S");
                }

        };

        stringList.forEach(
                s->{
                    try {
                        tce.throwIOException(s);
                        System.out.println("String not starting with S : "+s);
                    } catch (IOException e) {
                        System.out.println("We caught checked exception using custom Interface: "+e);
                    }

                }
        );

        */

        //Use BiFunction to add two numbers with lambda
        BiFunction<Integer, Integer, Integer> addition = Integer::sum;
        System.out.println("Addition of two numbers using BiFunction, addition.apply(10,20) is "+addition.apply(10,20));

        //Count elements in list using count
        int numberOfStringsInStringList = (int) stringList.stream()
                .filter(s->s!=null && !s.isEmpty())
                .count();

        System.out.println("Count elements in list using count, numberOfStringsInStringList is "+numberOfStringsInStringList);

        //NOTE : count() returns long — use long variable, best practice

        long numberOfStringsInStringList1 = (int) stringList.stream()
                .filter(s->s!=null && !s.isEmpty())
                .count();

        System.out.println("Count elements in list using count, numberOfStringsInStringList1 is "+numberOfStringsInStringList1);

        //Reduce to sum of list using reduce.
        long sumOfIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .reduce((a, b) -> a+b)
                .orElse(null);

        System.out.println("Reduce to sum of list using reduce, sumOfIntegerList is "+sumOfIntegerList);






    }
}
