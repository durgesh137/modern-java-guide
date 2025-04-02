package com.modernjava.guide.java8.core.streams.exercises.level2;

import java.util.Arrays;
import java.util.List;

public class OddEvenCount {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 45, 2, 12, 34, 56, 1, 234, 0);
        System.out.println("Original List: "+numbers);
        countEvensInList(numbers);
        countOddsInList(numbers);
    }

    private static void countOddsInList(List<Integer> numbers) {
        //count++ will not work, since count value is used, count++ will increment later that will be lost
        Integer odds = numbers.stream().reduce(0, (count, num) -> num % 2 != 0 ? ++count : count);
        System.out.println("Odd elements: "+odds);
    }

    private static void countEvensInList(List<Integer> numbers) {
        Integer evens = numbers.stream().reduce(0, (count, num) -> num % 2 == 0 ? ++count : count);
        System.out.println("Even elements: "+evens);
    }

}
