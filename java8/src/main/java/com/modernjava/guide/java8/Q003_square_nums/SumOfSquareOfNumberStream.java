package com.modernjava.guide.java8.Q003_square_nums;

public class SumOfSquareOfNumberStream {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        System.out.println("Q003: Sum of Square of Numbers using Stream API");
        System.out.println("Input: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println("\n");

        // Stream API Approach
        System.out.println("APPROACH: Stream API");
        System.out.println("Code:");
        System.out.println("  int sum = Arrays.stream(numbers)");
        System.out.println("                   .map(n -> n * n)");
        System.out.println("                   .sum();");
        System.out.println();

        int sum = sumOfSquaresStream(numbers);
        System.out.println("Output: Sum of squares = " + sum);
    }

    public static int sumOfSquaresStream(int[] numbers) {
        /**
         * Using Java 8 Stream API to calculate the sum of squares of the numbers.
         * Steps:
         * 1. Convert the array to a stream using Arrays.stream().
         * 2. Use the map() function to square each number.
         * 3. Use the sum() function to calculate the total sum of the squared numbers.
         */
        return java.util.Arrays.stream(numbers)
                .map(n -> n * n)
                .sum();
    }
}
