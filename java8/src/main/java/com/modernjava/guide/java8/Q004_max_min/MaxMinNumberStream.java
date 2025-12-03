package com.modernjava.guide.java8.Q004_max_min;

import com.modernjava.guide.java8.util.ComparisonUtils;

public class MaxMinNumberStream {
    public static void main(String[] args) {
        int[] numbers = {45, 22, 89, 11, 67, 34, 90, 5,-121, 342, 0, 9999, -5000};

        System.out.println("Q004: Find Max and Min Number using Stream API");
        ComparisonUtils.repeat("-",80);
        System.out.println("Input: ");
        ComparisonUtils.printNumbers(numbers);

        // Stream API Approach
        System.out.println("APPROACH: Stream API");
        System.out.println("Code:");
        System.out.println("  int max = Arrays.stream(numbers).max().orElse(0);");
        System.out.println("  int min = Arrays.stream(numbers).min().orElse(0);");
        System.out.println();

        Pair result = findMaxMinStream(numbers);
        System.out.println("Output: Max = " + result.getMaxElement() + ", Min = " + result.getMinElement());
    }

    public static Pair findMaxMinStream(int[] numbers) {
        /**
         * Using Java 8 Stream API to find the maximum and minimum numbers in an array.
         * Steps:
         * 1. Convert the array to a stream using Arrays.stream().
         * 2. Use the max() function to find the maximum number.
         * 3. Use the min() function to find the minimum number.
         * 4. Use orElse(0) to handle the case of an empty array.
         * 5. Return both values encapsulated in a Pair object.
         */
        int max = java.util.Arrays.stream(numbers).max().orElse(0);
        int min = java.util.Arrays.stream(numbers).min().orElse(0);

        return new Pair(min, max);
    }
}
