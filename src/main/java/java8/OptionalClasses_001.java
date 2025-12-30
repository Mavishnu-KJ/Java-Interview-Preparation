package java8;

import java.util.Optional;

public class OptionalClasses_001 {
    public static void main() {

        String nonNullValueString = "Funkynshot";
        String nullValueString = null;

        //empty() method
        Optional<Object> optionalEmptyObj = Optional.empty();
        System.out.println("Object created using empty() method is " + optionalEmptyObj);

        //of() method with nonNullValueString
        Optional<String> optionalOfObjWithNonNullValueString = Optional.of(nonNullValueString);
        System.out.println("Object created using of() method with nonNullValueString is " + optionalOfObjWithNonNullValueString);

        //of() method with nullValueString
        //Optional<String> optionalOfObjWithNullValueString = Optional.of(nullValueString);
        //System.out.println("Object created using of() method with nullValueString is "+optionalOfObjWithNullValueString);
        //The above two lines will throw nullPointerException if uncommented

        //ofNullable() method with nonNullValueString
        Optional<String> optionalOfNullableWithNonNullValueString = Optional.ofNullable(nonNullValueString);
        System.out.println("Object created using ofNullable() method with nonNullValueString is " + optionalOfNullableWithNonNullValueString);

        //ofNullable() method with nullValueString
        Optional<String> optionalOfNullableWithNullValueString = Optional.ofNullable(nullValueString);
        System.out.println("Object created using ofNullable() method with nullValueString is " + optionalOfNullableWithNullValueString);

        //get() method with nonNullValue Optional
        System.out.println("get method with nonNullValue Optional, optionalOfNullableWithNonNullValueString.get() is " + optionalOfNullableWithNonNullValueString.get());

        //get() method with nullValue Optional
        //System.out.println("get method with nullValue Optional, optionalOfNullableWithNullValueString.get() is "+optionalOfNullableWithNullValueString.get());
        //The above line will throw NoSuchElementException if uncommented

        //isPresent method with nonNullValue Optional
        System.out.println("isPresent method with nonNullValue Optional, optionalOfNullableWithNonNullValueString.isPresent() is " + optionalOfNullableWithNonNullValueString.isPresent());

        //isPresent method with nullValue Optional
        System.out.println("isPresent method with nullValue Optional, optionalOfNullableWithNullValueString.isPresent() is " + optionalOfNullableWithNullValueString.isPresent());

        //isEmpty method with nonNullValue Optional
        System.out.println("isEmpty method with nonNullValue Optional, optionalOfNullableWithNonNullValueString.isEmpty() is " + optionalOfNullableWithNonNullValueString.isEmpty());

        //isEmpty method with nullValue Optional
        System.out.println("isEmpty method with nullValue Optional, optionalOfNullableWithNullValueString.isEmpty() is " + optionalOfNullableWithNullValueString.isEmpty());

        //ifPresent method with nonNullValue Optional
        optionalOfNullableWithNonNullValueString.ifPresent((value) -> System.out.println("ifPresent method with nonNullValue Optional, optional value is " + value));

        //ifPresent method with nullValue Optional
        optionalOfNullableWithNullValueString.ifPresent((value) -> System.out.println("ifPresent method with nullValue Optional, optional value is " + value));
        //The above statement does nothing because that optional has no non-null value

        //ifPresentOrElse method with nonNullValue Optional
        optionalOfNullableWithNonNullValueString.ifPresentOrElse(
                (value) -> System.out.println("ifPresentOrElse method with nonNullValue Optional, optional value is " + value),
                () -> System.out.println("ifPresentOrElse method with nonNullValue Optional, ELSE PART")
        );

        //ifPresentOrElse method with nullValue Optional
        optionalOfNullableWithNullValueString.ifPresentOrElse(
                (value) -> System.out.println("ifPresentOrElse method with nullValue Optional, optional value is " + value),
                () -> System.out.println("ifPresentOrElse method with nullValue Optional, ELSE PART")
        );

        //orElse method with nonNullValue Optional
        System.out.println(
        optionalOfNullableWithNonNullValueString.orElse("orElse method with nonNullValue Optional, Printing only if it has no value")
        );

        //orElse method with nullValue Optional
        System.out.println(
        optionalOfNullableWithNullValueString.orElse("orElse method with nullValue Optional, Printing only if it has no value")
        );

        //orElseGet method with nonNullValue Optional
        System.out.println(
            optionalOfNullableWithNonNullValueString.orElseGet(
                    ()->"orElseGet method with nonNullValue Optional, printing only if it has no value"
            )
        );

        //orElseGet method with nullValue Optional
        System.out.println(
            optionalOfNullableWithNullValueString.orElseGet(
                    ()->"orElseGet method with nullValue Optional, printing only if it has no value"
            )
        );

        //orElseThrow method nonNullValue Optional
        System.out.println(
            optionalOfNullableWithNonNullValueString.orElseThrow(
                    ()->new RuntimeException("orElseThrow method nonNullValue Optional, The Optional has no value")
            )
        );

        //orElseThrow method nullValue Optional
        /*
        System.out.println(
           optionalOfNullableWithNullValueString.orElseThrow(
                   ()->new RuntimeException("orElseThrow method nullValue Optional, The Optional has no value")
           )
        );
        */
        //The above sysout will throw exception mentioned if uncommented

        //or method with nonNullValue Optional
        //or() method returns the current Optional if it contains a value; otherwise, returns the Optional
        // produced by the supplier.
        System.out.println(
            optionalOfNullableWithNonNullValueString.or(()->Optional.of("or method with nonNullValue Optional, created new Optional Object if the given optional is empty or has no value"))
        );

        //or method with nullValue Optional
        //or() method returns the current Optional if it contains a value; otherwise, returns the Optional
        // produced by the supplier.
        System.out.println(
                optionalOfNullableWithNullValueString.or(()->Optional.of("or method with nullValue Optional, created new Optional Object if the given optional is empty or has no value"))
        );

        //filter and map method with nonNullValue Optional
        optionalOfNullableWithNonNullValueString.filter(value->value.startsWith("F"))
                .map(String::length)
                .ifPresent((value)->System.out.println("filter and map method with nonNullValue Optional, String length is "+value));

        //filter and map method with nullValue Optional
        optionalOfNullableWithNullValueString.filter(value->value.startsWith("F"))
                .map(String::length)
                .ifPresentOrElse(
                        (value)->System.out.println("filter and map method with nullValue Optional, String length is "+value),
                        ()->System.out.println("filter and map method with nullValue Optional, Printing if the Optional is empty or has no value")
                );

    }
}