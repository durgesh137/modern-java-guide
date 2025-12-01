package com.modernjava.guide.java8.util;

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
}

