package com.modernjava.guide.java8.util;

import java.util.Random;

/**
 * Utility class for common methods used across comparison classes.
 * Provides helper methods for formatting and string operations.
 */
public class ComparisonUtils {

    // Private constructor to prevent instantiation
    private ComparisonUtils() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Repeat a string N times (Java 8 compatible).
     * Alternative to String.repeat() which was introduced in Java 11.
     *
     * @param str   the string to repeat
     * @param count number of times to repeat
     * @return repeated string
     */
    public static String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * Format time in human-readable format.
     * Converts nanoseconds to appropriate unit (ns, μs, ms).
     *
     * @param nanos time in nanoseconds
     * @return formatted time string
     */
    public static String formatTime(long nanos) {
        if (nanos < 1000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.2f μs", nanos / 1000.0);
        } else {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        }
    }

    /**
     * Print the elements of an integer array.
     * @param numbers
     */
    public static void printNumbers(int[] numbers) {
        for (Integer number : numbers) {
            System.out.print(number+",");
        }
        System.out.println("\n");
    }

    /**
     * method to generate an array of specified size with random integers.
     * @param size
     * @return
     */
    public static int[] getArrayOfSpecifiedSize( int size) {
        if (size <= 0) {
            return new int[0];
        }
        Random rand = new Random();
        final int MAX_ABS = 100; // range will be -MAX_ABS .. +MAX_ABS
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = rand.nextInt(MAX_ABS * 2 + 1) - MAX_ABS;
        }
        return result;
    }
}

