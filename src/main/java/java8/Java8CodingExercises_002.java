package java8;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Java8CodingExercises_002 {

    public static void main(String[] args){

        //Declaration and initialization
        List<String> stringList = Arrays.asList("Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "Raina");
        List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);
        String nonNullValueString = "Funkynshot";
        String nullValueString = null;

        //Use lambda with forEach to print a list of strings with "Hello " prefix.
        System.out.println("Use lambda with forEach to print a list of strings with \"Hello \" prefix : ");
        stringList.forEach(s->{
            if(s!=null && !s.isEmpty()){
                System.out.println("Hello "+s);
            }
        });

        //Sort a list of integers in descending order using lambda Comparator.
        List<Integer> integerListDesc = integerList.stream()
                .filter(Objects::nonNull)
                .sorted((a,b)->b.compareTo(a))
                .toList();

        System.out.println("Sort a list of integers in descending order using lambda Comparator, integerListDesc : "+integerListDesc);

        //Filter even numbers from a list using Predicate and lambda.
        List<Integer> evenIntegerList = integerList.stream()
                .filter(n-> n!=null && n%2==0)
                .toList();
        System.out.println("Filter even numbers from a list using Predicate and lambda, evenIntegerList : "+evenIntegerList);

        //Use Consumer to print each element of a list with uppercase.
        System.out.println("Use Consumer to print each element of a list with uppercase : ");
        stringList.forEach(s->{
            if(s!=null && !s.isEmpty()){
                System.out.println(s.toUpperCase());
            }
        });

        //Create a Supplier that generates random numbers (1–100)
        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(100-1+1)+1;
        System.out.println("Supplier that generated random numbers (1-100), random number : "+supplier.get());
        System.out.println("Supplier that generated random numbers (1-100), random number : "+supplier.get());

        //Use Function to convert a list of strings to uppercase.
        List<String> upperCaseStringList = stringList.stream()
                .filter(s-> s!=null && !s.isEmpty())
                .map(String::toUpperCase)
                .toList();
        System.out.println("Use Function to convert a list of strings to uppercase, upperCaseStringList : "+upperCaseStringList);

        //Chain Function: Convert string to uppercase, then to length.
        List<Integer> LengthOfStringsInStringList = stringList.stream()
                .filter(s->s!=null && !s.isEmpty())
                .map(s->s.toUpperCase().length())
                .toList();
        System.out.println("Chain Function: Convert string to uppercase, then to length, LengthOfStringsInStringList : "+LengthOfStringsInStringList);

        //Use Predicate to filter names starting with "S" from a list.
        List<String> stringStartsWithS = stringList.stream()
                .filter(s-> s!=null && !s.isEmpty() && s.startsWith("S"))
                .toList();
        System.out.println("Use Predicate to filter names starting with \"S\" from a list, stringStartsWithS : "+stringStartsWithS);

        //Implement a custom functional interface "Calculator" with add and subtract methods using lambda.
        Calculator add = Integer::sum;
        Calculator subtract = (a,b) -> a-b;

        System.out.println("Using the custom functional interface Calculator, addition of 6,4 is "+add.calc(6,4));
        System.out.println("Using the custom functional interface Calculator, subtraction of 6,4 is "+subtract.calc(6,4));

        //Use lambda with Runnable to print "Hello from thread".
        Runnable runnable = ()->System.out.println("Hello from thread "+Thread.currentThread().getName());
        Thread t1 = new Thread(runnable,"t1");
        t1.start(); //Calling runnable from the thread t1
        runnable.run(); //Calling runnable from the main thread
        System.out.println("Printing Hello from main"); // Just to show the thread running orders may vary because threads are non-deterministic

        //Sort a list of employees by salary using Comparator lambda.
        List<Employee> employeeList = Arrays.asList(
            new Employee(10,"Sachin",800000),
            new Employee(18,"Virat",500000),
            new Employee(7,"Dhoni",600000)
        );

        List<Employee> employeesListBySalary =  employeeList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Employee::getSalary))
                .toList();
        System.out.println("Sort a list of employees by salary using Comparator lambda, employeesListBySalary : "+employeesListBySalary);

        //Find Highest paid employee
        Employee highestPaidEmployee = employeeList.stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);

        System.out.println("Sort a list of employees by salary using Comparator lambda, highestPaidEmployee : "+highestPaidEmployee);

        //Find Second Highest paid employee
        Employee secondHighestPaidEmployee = employeeList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                .limit(2)
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("Sort a list of employees by salary using Comparator lambda, secondHighestPaidEmployee : "+secondHighestPaidEmployee);

        //Group a list of strings by length descending using Collectors.groupingBy with lambda.
        Map<Integer, List<String>> stringListGroupingByLength = stringList.stream()
                .filter(s-> s!=null && !s.isEmpty())
                .collect(Collectors.groupingBy(
                            String::length, () -> new TreeMap<>((a,b)->b.compareTo(a)), Collectors.toList()
                            ));

        System.out.println("Group a list of strings by length descending using Collectors.groupingBy with lambda : "+stringListGroupingByLength);

        //Use Optional with lambda: If value present, print it; else print default.
        Optional<String> optionalWithNonNullValueString = Optional.ofNullable(nonNullValueString);
        Optional<String> optionalWithNullValueString = Optional.ofNullable(nullValueString);

        //Case 1 : Value present
        optionalWithNonNullValueString.ifPresentOrElse(
                (value) -> System.out.println("Value present : "+value),
                () -> System.out.println("default")
        );

        //Case 2 : Value not present
        optionalWithNullValueString.ifPresentOrElse(
                (value) -> System.out.println("Value present : "+value),
                () -> System.out.println("default")
        );

        //Create a Predicate that checks if a number is prime (using lambda).
        Predicate<Integer> isPrime = n -> {
            if(n<=1) return false;
            if(n==2) return true;
            if (n % 2 == 0) return false;
            for(int i=3; i<=Math.sqrt(n); i=i+2){ // We can use i * i <= n instead of Math.sqrt(n), slightly faster
                if(n%i==0) return false;
            }
            return true;
        };

        System.out.println("Is the number 6 prime ? "+isPrime.test(6));
        System.out.println("Is the number 7 prime ? "+isPrime.test(7));
        System.out.println("Is the number 3 prime ? "+isPrime.test(3));

        //Implement a method reference for static method (e.g., String::toUpperCase)
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, Integer> parseInt = Integer::parseInt;
        Function<Integer, Integer> mathAbs = Math::abs;

        System.out.println("Method Reference using Function, toUpperCase(\"Funkynshot\") "+toUpperCase.apply("Funkynshot"));
        System.out.println("Method Reference using Function, parseInt(\"500\") "+parseInt.apply("500"));
        System.out.println("Method Reference using Function, mathAbs(-12) "+mathAbs.apply(-12));

        //Use constructor reference to create a new ArrayList.
        Supplier<ArrayList<Integer>> supplierToCreateArrayList = ArrayList::new;
        ArrayList<Integer> newArrayListFromSupplier = supplierToCreateArrayList.get();
        newArrayListFromSupplier.add(10);
        newArrayListFromSupplier.add(18);
        newArrayListFromSupplier.add(7);
        System.out.println("New arrayList generated using constructor reference Supplier : "+newArrayListFromSupplier);





    }
}
