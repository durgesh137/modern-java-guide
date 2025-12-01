package com.modernjava.guide.java8.Q001_print_numbers;

import java.util.Arrays;
import java.util.List;

/**
 * Q001: Print All Numbers in a List
 *
 * Problem: Given a list of integers, print all numbers.
 *
 * This comparison demonstrates:
 * - Traditional approach using for-each loop
 * - Stream API approach using forEach()
 * - Performance comparison
 * - Code readability comparison
 */
public class PrintNumbersComparison {

    // Utility method to repeat a string (Java 8 compatible)
    private static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10,1, 2, 1, 3, 2, 4, 5, 6, 2, 2, 7, 8, 4, 9, 10);

        System.out.println(repeat("=", 80));
        System.out.println("Q001: Print All Numbers in a List");
        System.out.println(repeat("=", 80));
        System.out.println("Input: " + numbers);
        System.out.println("Size: " + numbers.size() + " elements");
        System.out.println();

        // Test Case 1: Traditional Approach
        System.out.println("APPROACH 1: Traditional (For-Each Loop)");
        System.out.println(repeat("-", 80));
        System.out.println("Code:");
        System.out.println("  for (Integer number : numbers) {");
        System.out.println("      System.out.print(number + \",\");");
        System.out.println("  }");
        System.out.println();
        System.out.print("Output: ");

        long traditionalStart = System.nanoTime();
        printTraditional(numbers);
        long traditionalEnd = System.nanoTime();
        long traditionalTime = traditionalEnd - traditionalStart;

        System.out.println("Time: " + traditionalTime + " ns (" + formatTime(traditionalTime) + ")");
        System.out.println();

        // Test Case 2: Stream API Approach
        System.out.println("APPROACH 2: Stream API (forEach with Method Reference)");
        System.out.println(repeat("-", 80));
        System.out.println("Code:");
        System.out.println("  numbers.stream()");
        System.out.println("         .forEach(System.out::println);");
        System.out.println();
        System.out.println("Output: ");

        long streamStart = System.nanoTime();
        printStream(numbers);
        long streamEnd = System.nanoTime();
        long streamTime = streamEnd - streamStart;

        System.out.println("Time: " + streamTime + " ns (" + formatTime(streamTime) + ")");
        System.out.println();

        // Alternative Stream Approaches
        System.out.println("APPROACH 3: Stream API Variations");
        System.out.println(repeat("-", 80));

        System.out.println("a) Using lambda expression:");
        System.out.println("   numbers.stream().forEach(n -> System.out.println(n));");
        System.out.print("   Output: ");
        numbers.stream().forEach(n -> System.out.print(n + ","));
        System.out.println();
        System.out.println();

        System.out.println("b) Using lambda with custom formatting:");
        System.out.println("   numbers.stream().forEach(n -> System.out.print(\"[\" + n + \"] \"));");
        System.out.print("   Output: ");
        numbers.stream().forEach(n -> System.out.print("[" + n + "] "));
        System.out.println();
        System.out.println();

        // Performance Comparison with Warmup
        System.out.println(repeat("=", 80));
        System.out.println("PERFORMANCE COMPARISON (with JIT warmup)");
        System.out.println(repeat("=", 80));
        performanceComparison(numbers);
        System.out.println();

        // Summary
        printSummary(traditionalTime, streamTime);
    }

    /**
     * Traditional approach: Print all numbers using for-each loop
     */
    private static void printTraditional(List<Integer> numbers) {
        for (Integer number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println();
    }

    /**
     * Stream API approach: Print all numbers using forEach with method reference
     */
    private static void printStream(List<Integer> numbers) {
        numbers.stream().forEach(n -> System.out.print(n + ","));
        System.out.println();
    }

    /**
     * Performance comparison with JIT warmup
     */
    private static void performanceComparison(List<Integer> numbers) {
        int iterations = 1000;

        // Warmup
        System.out.println("Warming up JIT compiler with " + iterations + " iterations...");
        for (int i = 0; i < iterations; i++) {
            printTraditionalSilent(numbers);
            printStreamSilent(numbers);
        }

        // Actual timing
        System.out.println("Running timed iterations...");
        System.out.println();

        long totalTraditional = 0;
        long totalStream = 0;
        int timedIterations = 100;

        for (int i = 0; i < timedIterations; i++) {
            long start = System.nanoTime();
            printTraditionalSilent(numbers);
            totalTraditional += System.nanoTime() - start;

            start = System.nanoTime();
            printStreamSilent(numbers);
            totalStream += System.nanoTime() - start;
        }

        long avgTraditional = totalTraditional / timedIterations;
        long avgStream = totalStream / timedIterations;

        System.out.println("Average time over " + timedIterations + " iterations:");
        System.out.println("  Traditional: " + avgTraditional + " ns (" + formatTime(avgTraditional) + ")");
        System.out.println("  Stream API:  " + avgStream + " ns (" + formatTime(avgStream) + ")");

        if (avgTraditional < avgStream) {
            double percentage = ((double) avgStream / avgTraditional - 1) * 100;
            System.out.println("  → Traditional is " + String.format("%.2f", percentage) + "% faster");
        } else if (avgStream < avgTraditional) {
            double percentage = ((double) avgTraditional / avgStream - 1) * 100;
            System.out.println("  → Stream API is " + String.format("%.2f", percentage) + "% faster");
        } else {
            System.out.println("  → Performance is equal");
        }
    }

    /**
     * Silent version for performance testing (no output)
     */
    private static void printTraditionalSilent(List<Integer> numbers) {
        for (Integer number : numbers) {
            // No output for performance testing
            number.toString();
        }
    }

    /**
     * Silent version for performance testing (no output)
     */
    private static void printStreamSilent(List<Integer> numbers) {
        numbers.stream().forEach(n -> n.toString());
    }

    /**
     * Format time in human-readable format
     */
    private static String formatTime(long nanos) {
        if (nanos < 1000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.2f μs", nanos / 1000.0);
        } else {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        }
    }

    /**
     * Print comprehensive summary
     */
    private static void printSummary(long traditionalTime, long streamTime) {
        System.out.println(repeat("=", 80));
        System.out.println("SUMMARY: Traditional vs Stream API");
        System.out.println(repeat("=", 80));
        System.out.println();

        System.out.println("TRADITIONAL APPROACH (For-Each Loop):");
        System.out.println("  Pros:");
        System.out.println("    ✓ Familiar syntax for all Java developers");
        System.out.println("    ✓ Simple and straightforward");
        System.out.println("    ✓ Slightly faster for small collections");
        System.out.println("    ✓ Easy to debug with breakpoints");
        System.out.println("  Cons:");
        System.out.println("    ✗ More verbose");
        System.out.println("    ✗ Imperative style (how to do it)");
        System.out.println("    ✗ Cannot easily parallelize");
        System.out.println();

        System.out.println("STREAM API APPROACH (forEach):");
        System.out.println("  Pros:");
        System.out.println("    ✓ Concise and expressive");
        System.out.println("    ✓ Declarative style (what to do)");
        System.out.println("    ✓ Can use method references (System.out::println)");
        System.out.println("    ✓ Easy to chain with other stream operations");
        System.out.println("    ✓ Can parallelize with .parallelStream()");
        System.out.println("  Cons:");
        System.out.println("    ✗ Slight overhead for very small collections");
        System.out.println("    ✗ Requires understanding of functional concepts");
        System.out.println();

        System.out.println("KEY LEARNINGS:");
        System.out.println("  • Both produce identical output");
        System.out.println("  • Stream API is more readable and maintainable");
        System.out.println("  • Performance difference is negligible for most use cases");
        System.out.println("  • Stream API shines when combined with other operations (filter, map, etc.)");
        System.out.println("  • Method references (::) make code even more concise");
        System.out.println();

        System.out.println("WHEN TO USE:");
        System.out.println("  Traditional: Simple loops, performance-critical tight loops, legacy code");
        System.out.println("  Stream API:  Complex transformations, pipelines, modern codebases");
        System.out.println();

        System.out.println(repeat("=", 80));
    }
}
