package com.modernjava.guide.java8.Q001_print_numbers;

import java.util.Arrays;
import java.util.List;

public class PrintNumbersTraditional {
    public static void main(String[] args) {
        System.out.println("Print all numbers in the list - Traditional way");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10,1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10);
        printAllNumbersInList(numbers);
    }

    private static void printAllNumbersInList(List<Integer> numbers) {
        //it covers how to do something in a traditional way
        for (Integer number : numbers) {
            System.out.print(number+",");
        }
        System.out.println();
    }
}
