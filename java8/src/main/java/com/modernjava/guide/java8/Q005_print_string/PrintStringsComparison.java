package com.modernjava.guide.java8.Q005_print_string;

import com.modernjava.guide.java8.util.ComparisonUtils;

/**
 * Q005: Print all strings containing a specific substring
 * Compares Traditional approach vs Stream API approach
 * Tests with small, medium, and large datasets
 */
public class PrintStringsComparison {
    public static void main(String[] args) {
        String substring = "_test";

        System.out.println("Q005: Print All Strings containing substring '_test' using Both Approaches");
        System.out.println(ComparisonUtils.repeat("=", 80));

        // Test Case 1: Small dataset (20 strings)
        System.out.println("\n" + ComparisonUtils.repeat("-", 30) + " Test Case 1: Small Dataset " + ComparisonUtils.repeat("-", 30));
        String[] strings = ComparisonUtils.getStringArrayWithPatternAtEnd(20, substring);
        System.out.println("Input (size=" + strings.length + "): ");
        ComparisonUtils.printStrings(strings);
        compareApproaches(strings, substring, 20);

        // Test Case 2: Medium dataset (1,000 strings)
        System.out.println("\n\n" + ComparisonUtils.repeat("-", 30) + " Test Case 2: Medium Dataset " + ComparisonUtils.repeat("-", 30));
        strings = ComparisonUtils.getStringArrayWithPatternAtEnd(1_000, substring);
        System.out.println("Input (size=" + strings.length + "): ");
        System.out.println("[... 1,000 strings ...]");
        compareApproaches(strings, substring, 1_000);

        // Test Case 3: Large dataset (100,000 strings)
        System.out.println("\n\n" + ComparisonUtils.repeat("-", 30) + " Test Case 3: Large Dataset " + ComparisonUtils.repeat("-", 30));
        strings = ComparisonUtils.getStringArrayWithPatternAtEnd(100_000, substring);
        System.out.println("Input (size=" + strings.length + "): ");
        System.out.println("[... 100,000 strings ...]");
        compareApproaches(strings, substring, 100_000);

        // Test Case 4: Very Large dataset (1,000,000 strings)
        System.out.println("\n\n" + ComparisonUtils.repeat("-", 30) + " Test Case 4: Very Large Dataset " + ComparisonUtils.repeat("-", 30));
        strings = ComparisonUtils.getStringArrayWithPatternAtEnd(1_000_000, substring);
        System.out.println("Input (size=" + strings.length + "): ");
        System.out.println("[... 1,000,000 strings ...]");
        compareApproaches(strings, substring, 1_000_000);
    }

    private static void compareApproaches(String[] strings, String substring, int size) {
        // Traditional Approach
        System.out.println("\nAPPROACH: Traditional");
        System.out.println(ComparisonUtils.repeat("-", 80));
        long startTime = System.nanoTime();

        if (size > 50) {
            System.out.println("[Skipped printing output for large array of size " + size + "]");
        } else {
            PrintStringsTraditional.printStrings(strings, substring);
        }

        long endTime = System.nanoTime();
        long traditionalTime = endTime - startTime;
        System.out.println("Time: " + traditionalTime + " ns (" + ComparisonUtils.formatTime(traditionalTime) + ")");

        // Stream API Approach
        System.out.println("\nAPPROACH: Stream API");
        System.out.println(ComparisonUtils.repeat("-", 80));
        long streamStartTime = System.nanoTime();
        PrintStringsStream.searchStrings(strings, substring);


        long streamEndTime = System.nanoTime();
        long streamTime = streamEndTime - streamStartTime;
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
}
