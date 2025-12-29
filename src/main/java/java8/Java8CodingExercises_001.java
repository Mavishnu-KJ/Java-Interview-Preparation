package java8;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Java8CodingExercises_001 {
    public static void main(String[] args){

        //Declaration and initialization
        List<String> stringList = Arrays.asList("Sachin", "Shewag", "Gambhir","Virat", "", "Yuvraj", null,"Dhoni", "Raina");
        List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);

        //Use lambda with forEach to print a list of strings with "Hello " prefix.
        System.out.println("Use lambda with forEach to print a list of strings with \"Hello\" prefix : ");
        stringList.forEach(s->{
            if(s!=null && !s.isEmpty()) {
                System.out.println("Hello "+s);
            }
        });

        //Sort a list of integers in descending order using lambda Comparator.
        List<Integer> integerListDesc = integerList.stream()
                .filter(Objects::nonNull)
                .sorted((a,b)->b.compareTo(a))
                .toList();

        System.out.println("Sort a list of integers in descending order using lambda Comparator :" +integerListDesc);

        //Filter even numbers from a list using Predicate and lambda.
        List<Integer> evenIntegerList = integerList.stream()
                .filter(Objects::nonNull)
                .filter(n->n%2==0)
                .toList();
        System.out.println("Filter even numbers from a list using Predicate and lambda :" +evenIntegerList);

        //Use Consumer to print each element of a list with uppercase.
        List<String> stringListUpperCase = stringList.stream()
                .filter(s -> s!=null && !s.isEmpty())
                .map(String::toUpperCase)
                .toList();
        System.out.println("Use Consumer to print each element of a list with uppercase :" +stringListUpperCase);

        //Create a Supplier that generates random numbers (1–100).
        Random random = new Random();
        //Supplier supplier = random.nextInt(100-1+1)+1;
        Supplier supplier = () -> random.nextInt(100-1+1)+1;
        System.out.println("Create a Supplier that generates random numbers (1–100) : "+supplier.get());
        System.out.println("Create a Supplier that generates random numbers (1–100) : "+supplier.get());

        //Use Function to convert a list of strings to uppercase.
        List<String> stringListUpperCase1 = stringList.stream()
                .filter(s -> s!=null && !s.isEmpty())
                .map(String::toUpperCase)
                .toList();
        System.out.println("Use Function to convert a list of strings to uppercase :" +stringListUpperCase1);

        //Chain Function: Convert string to uppercase, then to length.
        List<Integer> stringLength = stringList.stream()
                .filter(s-> s!=null && !s.isEmpty())
                .map(s->s.toUpperCase().length())
                .toList();
        System.out.println("Chain Function: Convert string to uppercase, then to length :" +stringLength);

        //Use Predicate to filter names starting with "S" from a list.
        List<String> stringContainsSList = stringList.stream()
                .filter(s->s!=null && !s.isEmpty() && s.startsWith("S"))
                .toList();
        System.out.println("Use Predicate to filter names starting with \"S\" from a list :" +stringContainsSList);

        //Implement a custom functional interface "Calculator" with add and subtract methods using lambda.
        Calculator add = (a,b) -> a+b; //Integer::Sum
        Calculator subtract = (a,b) -> a-b;
        System.out.println("Implement a custom functional interface \"Calculator\" with add and subtract methods using lambda, calc(6,4), addition :" +add.calc(6,4));
        System.out.println("Implement a custom functional interface \"Calculator\" with add and subtract methods using lambda, calc(6,4), subtraction :" +subtract.calc(6,4));

        //Use lambda with Runnable to print "Hello from thread"
        Runnable runnable = ()->System.out.println("Hello from thread");
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
        System.out.println("employeeList : "+employeeList);

        List<Employee> employeeListBySalary = employeeList.stream()
                .filter(Objects::nonNull)
                .sorted((a,b)->Integer.compare(a.getSalary(), b.getSalary()))
                .toList();
        System.out.println("Sort a list of employees by salary using Comparator lambda : "+employeeListBySalary);

        //Find highest paid employee
        Employee highestPaidEmployee = employeeList.stream()
                .max(Comparator.comparingInt(Employee ::getSalary))
                .orElse(null);
        System.out.println("Find highest paid employee : "+highestPaidEmployee);

        //Find lowest paid employee
        Employee lowestPaidEmployee = employeeList.stream()
                .min(Comparator.comparingInt(Employee ::getSalary))
                .orElse(null);
        System.out.println("Find lowest paid employee : "+lowestPaidEmployee);

        //Find second highest paid employee
        Employee secondHighestPaidEmployee = employeeList.stream()
                .sorted(Comparator.comparingInt(Employee ::getSalary).reversed())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("Find second Highest Paid Employee : "+secondHighestPaidEmployee);

        //Group a list of strings by length using Collectors.groupingBy with lambda.
        Map<Integer, List<String>> stringListByLength = stringList.stream()
                .filter(s -> s!=null && !s.isEmpty())
                .collect(Collectors.groupingBy (s->s.length())); // We can use String::length instead
        System.out.println("Group a list of strings by length using Collectors.groupingBy with lambda : "+stringListByLength);

        //Group a list of strings by length using Collectors.groupingBy with lambda, ascending order.
        Map<Integer, List<String>> stringListByLengthAsc = stringList.stream()
                .filter(s -> s!=null && !s.isEmpty())
                .collect(Collectors.groupingBy (String::length, TreeMap::new, Collectors.toList()));
        System.out.println("Group a list of strings by length using Collectors.groupingBy with lambda, ascending order : "+stringListByLengthAsc);


        //Group a list of strings by length using Collectors.groupingBy with lambda, descending order.
        Map<Integer, List<String>> stringListByLengthDesc = stringList.stream()
                .filter(s -> s!=null && !s.isEmpty())
                .collect(Collectors.groupingBy (String::length, ()->new TreeMap<>(Comparator.reverseOrder()),Collectors.toList()));
        System.out.println("Group a list of strings by length using Collectors.groupingBy with lambda, descending order : "+stringListByLengthDesc);

        //Use Optional with lambda: If value present, print it; else print default.



    }
}
