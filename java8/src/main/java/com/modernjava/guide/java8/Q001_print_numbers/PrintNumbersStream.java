package com.modernjava.guide.java8.Q001_print_numbers;

import java.util.Arrays;
import java.util.List;

public class PrintNumbersStream {
    public static void main(String[] args) {
        System.out.println("Print all numbers in the list - Functional way");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10,1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10);
        printAllNumbersUsingStream(numbers);
    }

    private static void printAllNumbersUsingStream(List<Integer> numbers) {
        //what to do is more important than how to do in functional approach
        printNumberWithMethodReference(numbers);
        printNumbersWithLambda(numbers);
    }

    private static void printNumbersWithLambda(List<Integer> numbers) {
        /**
         * Steps involved are-
         * 1. Convert the collection to a stream
         * 2. For each element in stream, perform the action of printing the element
         * stream() method converts the collection to a stream
         * forEach() method performs the action for each element in the stream
         * n -> System.out.println(n) is a lambda expression that defines the action to be performed for each element
         */
        numbers.stream().forEach(n -> System.out.println(n));
    }

    private static void printNumberWithMethodReference(List<Integer> numbers) {
        /**
         * Steps involved are-
         * 1. Convert the collection to a stream
         * 2. For each element in stream, perform the action of printing the element
         * stream() method converts the collection to a stream
         * forEach() method performs the action for each element in the stream
         * System.out::println is a method reference to the println method of System.out
         */
        numbers.stream().forEach(System.out::println);
    }
}
