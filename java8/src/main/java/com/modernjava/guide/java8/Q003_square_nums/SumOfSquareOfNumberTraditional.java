package com.modernjava.guide.java8.Q003_square_nums;

public class SumOfSquareOfNumberTraditional {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        System.out.println("Q003: Sum of Square of Numbers");
        System.out.println("Input: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println("\n");

        // Traditional Approach
        System.out.println("APPROACH: Traditional (For-Each Loop)");
        System.out.println("Code:");
        System.out.println("  int sum = 0;");
        System.out.println("  for (int number : numbers) {");
        System.out.println("      sum += number * number;");
        System.out.println("  }");
        System.out.println();

        int sum = sumOfSquares(numbers);
        System.out.println("Output: Sum of squares = " + sum);
    }

    public static int sumOfSquares(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number * number;
        }
        return sum;
    }

}
