package java11;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Java11CodingExercises_001 {

    public static void main(String[] args) throws IOException, InterruptedException {

        //Declaration and initialization
        List<String> stringList = Arrays.asList("Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "Raina");
        List<Integer> integerList = Arrays.asList(10, 44, 18, 12, 7, 3);

        //Use var for List, Map, Stream
        //Using var for List
        var list = new ArrayList<String>(Arrays.asList("Sachin", "Shewag", "Gambhir", "Virat", "", "Yuvraj", null, "Dhoni", "Raina"));
        System.out.println("Use var for list, list is : "+list);

        //Using var for Map, Stream
        var groupingByLength = list.stream()
                .filter(s->s!=null&&!s.isEmpty())
                .collect(Collectors.groupingBy(String::length, ()->new TreeMap<>(Comparator.reverseOrder()), Collectors.toList()));

        System.out.println("Use var for Map, Stream, groupingByLength is : "+groupingByLength);

        //var with diamond operator - <> is the diamond operator. As they are already Integer, we dont need to repeat inside the <>
        var list1 = new ArrayList<>(Arrays.asList(10, 44, 18, 12, 7, 3));
        System.out.println("var with diamond operator, list1 is : "+list1);

        //var limitations (no initializer).
        //var x; //Without Initilizer, compiler cannot infer the var type, it will give compilation error if uncommented
        //var y = null; //This is not allwed for var variables. Compiler cannot infer the var type, it will give compilation error if uncommented

        //String.lines() on multi-line → Stream<String>
        String str = "Line1\nLine2\nLine3\n  \nLine4\n";
        //Couting only the blank lines in the String using lines(), isBlank()
        var blankLinesCount = str.lines()
                .filter(String::isBlank)
                .count();

        System.out.println("blankLinesCount is "+blankLinesCount);

        //strip(), stripLeading(), stripTrailing().
        String str1 = "   \u2000 Hey Java11  \u2000 \n";

        //strip()
        System.out.println("str1.strip() is "+ str1.strip()); //output: str1.strip() is Hey Java11

        //stripTrailing()
        System.out.println("str1.stripTrailing() is "+ str1.stripTrailing()); //output : str1.stripTrailing() is      Hey Java11

        //stripLeading()
        System.out.println("str1.stripLeading() is "+ str1.stripLeading()); //output : str1.stripLeading() is Hey Java11

        //String.repeat(5) on "Hello".
        System.out.println("String.repeat(5) on \"Hello\": " + "Hello".repeat(5));

        //Synchronous HTTP GET (print body from https://jsonplaceholder.typicode.com/posts/1).

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("body : "+response.body());

        /*
        Best Practices Shown

        Use HttpClient.newHttpClient() (thread-safe, reusable).
        BodyHandlers.ofString() for text/JSON response.
        Print status code + body.
        */

        //Asynchronous HTTP GET with thenAccept.
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)//extracts body
                .thenAccept(System.out::println);

        /*
        sendAsync() returns CompletableFuture<HttpResponse<String>>.
        .thenApply(HttpResponse::body) → CompletableFuture<String> (extracts body).
        .thenAccept(System.out::println) → CompletableFuture<Void> (consumes String, returns nothing).
         You try to assign CompletableFuture<Void> to HttpResponse variable → type mismatch.
         */


        /*
        Correct Ways to Handle Async HTTP GET
            1. Fire-and-Forget (Print Async, No Waiting) – Most Common for Async
            JavahttpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                      .thenApply(HttpResponse::body)
                      .thenAccept(System.out::println);
            // Program continues — body prints when ready (async)

            No assignment to HttpResponse.
            No join() — non-blocking.

            2. Wait for Response (Blocking – If You Need the HttpResponse Object)
            JavaHttpResponse<String> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .join();  // Blocks until complete

            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: " + response.body());

            .join() waits and returns the actual HttpResponse<String>.
         */


    }

}
