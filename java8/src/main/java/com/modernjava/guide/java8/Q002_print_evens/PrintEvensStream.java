package com.modernjava.guide.java8.Q002_print_evens;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PrintEvensStream {
    public static void main(String[] args) {
        System.out.println("Print all Even numbers in the list - Functional way");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10,1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10);
        printAllEvensUsingStream(numbers);
    }

    private static void printAllEvensUsingStream(List<Integer> numbers) {
        //it covers how to do something in a functional way
        /**
         * 1. numbers.stream(): Converts the list of numbers into a Stream, enabling functional-style operations.
         * 2. filter(n -> n % 2 == 0): Filters the stream to include only even numbers.
         */
        Stream<Integer> integerStream = numbers.stream()
                .filter(n -> n % 2 == 0);//filter even numbers

        //filter even numbers stream is acquired, now can be printed either using method reference or lambda
        printNumbersWithLambda(integerStream);
        printNumsWithMethodReference(integerStream);

    }

    private static void printNumsWithMethodReference(Stream<Integer> integerStream) {
        integerStream.forEach(System.out::println);
    }

    private static void printNumbersWithLambda(Stream<Integer> integerStream) {
        integerStream.forEach(n->   System.out.println(n + ","));
        System.out.println();
    }
}
