package com.modernjava.guide.java8.Q003_square_nums;

import com.modernjava.guide.java8.util.ComparisonUtils;

public class SumOfSquareOfNumberComparison {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        System.out.println("Q003: Sum of Square of Numbers Comparison");
        System.out.println("Input: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println("\n");

        // Traditional Approach
        System.out.println("APPROACH 1: Traditional (For-Each Loop)");
        ComparisonUtils.repeat("-", 80);
        System.out.println("Code:");
        System.out.println("  int sum = 0;");
        System.out.println("  for (int number : numbers) {");
        System.out.println("      sum += number * number;");
        System.out.println("  }");
        System.out.println();

        long traditionalStart = System.nanoTime();
        int traditionalSum = SumOfSquareOfNumberTraditional.sumOfSquares(numbers);
        long traditionalEnd = System.nanoTime();
        long traditionalTime = traditionalEnd - traditionalStart;
        System.out.println("Output: Sum of squares = " + traditionalSum);

        System.out.println("Time: " + traditionalTime + " ns (" + ComparisonUtils.formatTime(traditionalTime) + ")");
        System.out.println();


        // Stream API Approach
        System.out.println("APPROACH 2: Stream API");
        ComparisonUtils.repeat("-", 80);
        System.out.println("Code:");
        System.out.println("  int sum = Arrays.stream(numbers)");
        System.out.println("                   .map(n -> n * n)");
        System.out.println("                   .sum();");
        System.out.println();
        long streamStart = System.nanoTime();
        int streamSum = SumOfSquareOfNumberStream.sumOfSquaresStream(numbers);
        long streamEnd = System.nanoTime();
        long streamTime = streamEnd - streamStart;
        System.out.println("Output: Sum of squares = " + streamSum);
        System.out.println("Time: " + streamTime + " ns (" + ComparisonUtils.formatTime(streamTime) + ")");
        System.out.println();

    }
}
