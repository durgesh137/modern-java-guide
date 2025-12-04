package com.modernjava.guide.java8.Q006_count_evens_odds;

import com.modernjava.guide.java8.util.ComparisonUtils;
import java.util.Arrays;

/**
 * Q006: Count Even and Odd Numbers
 * Demonstrates both Traditional and Stream approaches in a single class
 */
public class CountEvensOddsComparison {

    public static void main(String[] args) {
        System.out.println("Q006: Count Even and Odd Numbers using Both Approaches");
        System.out.println(ComparisonUtils.repeat("=", 80));

        // Test Case 1: Small dataset
        System.out.println("\n" + ComparisonUtils.repeat("-", 30) + " Test Case 1: Small Dataset " + ComparisonUtils.repeat("-", 30));
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Input (size=" + numbers.length + "): ");
        ComparisonUtils.printNumbers(numbers);
        compareApproaches(numbers);

        // Test Case 2: Medium dataset
        System.out.println("\n\n" + ComparisonUtils.repeat("-", 30) + " Test Case 2: Medium Dataset " + ComparisonUtils.repeat("-", 30));
        numbers = ComparisonUtils.getArrayOfSpecifiedSize(1_000);
        System.out.println("Input (size=" + numbers.length + "): [... 1,000 numbers ...]");
        compareApproaches(numbers);

        // Test Case 3: Large dataset
        System.out.println("\n\n" + ComparisonUtils.repeat("-", 30) + " Test Case 3: Large Dataset " + ComparisonUtils.repeat("-", 30));
        numbers = ComparisonUtils.getArrayOfSpecifiedSize(100_000);
        System.out.println("Input (size=" + numbers.length + "): [... 100,000 numbers ...]");
        compareApproaches(numbers);
    }

    /**
     * Traditional approach to count even and odd numbers
     */
    public static Result countTraditional(int[] numbers) {
        int evenCount = 0;
        int oddCount = 0;

        for (int number : numbers) {
            if (number % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        return new Result(evenCount, oddCount);
    }

    /**
     * Stream API approach to count even and odd numbers
     */
    public static Result countStream(int[] numbers) {
        long evenCount = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .count();

        long oddCount = Arrays.stream(numbers)
                .filter(n -> n % 2 != 0)
                .count();

        return new Result((int) evenCount, (int) oddCount);
    }

    /**
     * Compare both approaches with performance metrics
     */
    private static void compareApproaches(int[] numbers) {
        // Traditional Approach
        System.out.println("\nAPPROACH: Traditional");
        System.out.println(ComparisonUtils.repeat("-", 80));
        long startTime = System.nanoTime();
        Result traditionalResult = countTraditional(numbers);
        long endTime = System.nanoTime();
        long traditionalTime = endTime - startTime;
        System.out.println("Even count: " + traditionalResult.evenCount);
        System.out.println("Odd count: " + traditionalResult.oddCount);
        System.out.println("Time: " + traditionalTime + " ns (" + ComparisonUtils.formatTime(traditionalTime) + ")");

        // Stream API Approach
        System.out.println("\nAPPROACH: Stream API");
        System.out.println(ComparisonUtils.repeat("-", 80));
        long streamStartTime = System.nanoTime();
        Result streamResult = countStream(numbers);
        long streamEndTime = System.nanoTime();
        long streamTime = streamEndTime - streamStartTime;
        System.out.println("Even count: " + streamResult.evenCount);
        System.out.println("Odd count: " + streamResult.oddCount);
        System.out.println("Time: " + streamTime + " ns (" + ComparisonUtils.formatTime(streamTime) + ")");

        // Performance Comparison
        System.out.println("\n" + ComparisonUtils.repeat("-", 80));
        System.out.println("PERFORMANCE COMPARISON:");
        System.out.println("Traditional: " + ComparisonUtils.formatTime(traditionalTime));
        System.out.println("Stream API:  " + ComparisonUtils.formatTime(streamTime));
        if (traditionalTime < streamTime) {
            double ratio = (double) streamTime / traditionalTime;
            System.out.println("Traditional is " + String.format("%.2f", ratio) + "x faster");
        } else {
            double ratio = (double) traditionalTime / streamTime;
            System.out.println("Stream API is " + String.format("%.2f", ratio) + "x faster");
        }
    }

    /**
     * Helper class to hold the result of counting
     */
    public static class Result {
        final int evenCount;
        final int oddCount;

        Result(int evenCount, int oddCount) {
            this.evenCount = evenCount;
            this.oddCount = oddCount;
        }
    }
}
