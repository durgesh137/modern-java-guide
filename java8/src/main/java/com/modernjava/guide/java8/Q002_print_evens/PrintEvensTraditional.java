package com.modernjava.guide.java8.Q002_print_evens;
import java.util.Arrays;
import java.util.List;

public class PrintEvensTraditional {
    public static void main(String[] args) {
        System.out.println("Print Even numbers in the list - Traditional way");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10,1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10);
        printAllEvensInList(numbers);
    }

    private static void printAllEvensInList(List<Integer> numbers) {
        //it covers how to do something in a traditional way
        for (Integer number : numbers) {
            if(number % 2 == 0)
                System.out.print(number+",");
        }
        System.out.println();
    }
}
