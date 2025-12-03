package com.modernjava.guide.java8.Q004_max_min;

import com.modernjava.guide.java8.util.ComparisonUtils;

public class MaxMinNumberTraditional {
    public static void main(String[] args) {
        int[] numbers = {45, 22, 89, 11, 67, 34, 90, 5};

        System.out.println("Q004: Find Max and Min Number using Traditional Approach");
        System.out.println("Input: ");
        ComparisonUtils.printNumbers(numbers);

        // Traditional Approach
        System.out.println("APPROACH: Traditional");
        Pair result = findMaxMinTraditional(numbers);
        System.out.println("Output: Max = " + result.getMaxElement() + ", Min = " + result.getMinElement());
    }

    public static Pair findMaxMinTraditional(int[] numbers) {
        /**
         * Traditional approach to find the maximum and minimum numbers in an array.
         * Steps:
         * 1. Initialize max and min with the first element of the array.
         * 2. Iterate through the array starting from the second element.
         * 3. Update max if the current element is greater than max.
         * 4. Update min if the current element is less than min.
         */
        int max = numbers[0];
        int min = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }

        return new Pair(min, max);
    }
}
