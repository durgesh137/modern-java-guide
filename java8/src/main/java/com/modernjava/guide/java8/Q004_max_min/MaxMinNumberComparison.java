package com.modernjava.guide.java8.Q004_max_min;

import com.modernjava.guide.java8.util.ComparisonUtils;

public class MaxMinNumberComparison {
    public static void main(String[] args) {
        int[] numbers = {45, 22, 89, 11, 67, 34, 90, 5,-121, 342, 0, 9999, -5000};

        System.out.println("Q004: Find Max and Min Number using Both Approaches");
        ComparisonUtils.repeat("-",80);

        //size 13, small input, traditional 166.33 microseconds, stream 1.01 milliseconds
        System.out.println(ComparisonUtils.repeat("-",30)+"Test Case 1: "+ComparisonUtils.repeat("-",30));
        System.out.print("Input: ");
        ComparisonUtils.printNumbers(numbers);
        compareApproaches(numbers);

        //size 10000, large input, traditional 105.54 microseconds ms, stream 615.42 microseconds
        System.out.println("\n\n"+ComparisonUtils.repeat("-",50)+"Test Case 2: "+ComparisonUtils.repeat("-",50));
        System.out.print("Input: ");
        numbers = ComparisonUtils.getArrayOfSpecifiedSize(10000);
        ComparisonUtils.printNumbers(numbers);
        compareApproaches(numbers);

        //size 100,000, large input, traditional 797.08 microseconds ms, stream 1.36 milliseconds
        System.out.println("\n\n"+ComparisonUtils.repeat("-",50)+"Test Case 3: "+ComparisonUtils.repeat("-",50));
        System.out.print("Input: ");
        numbers = ComparisonUtils.getArrayOfSpecifiedSize(100_000);
        //ComparisonUtils.printNumbers(numbers);//not printing for large input
        compareApproaches(numbers);

        //size 100,000, large input, traditional 2 milliseconds, stream 14 milliseconds
        System.out.println("\n\n"+ComparisonUtils.repeat("-",50)+"Test Case 4: "+ComparisonUtils.repeat("-",50));
        System.out.print("Input: ");
        numbers = ComparisonUtils.getArrayOfSpecifiedSize(10_00_000);
        //ComparisonUtils.printNumbers(numbers);//not printing for large input
        compareApproaches(numbers);

    }

    private static void compareApproaches(int[] numbers) {
        // Traditional Approach
        System.out.println("APPROACH: Traditional");
        ComparisonUtils.repeat("-",80);
        long startTime = System.nanoTime();
        Pair traditionalResult = MaxMinNumberTraditional.findMaxMinTraditional(numbers);
        long endTime = System.nanoTime();
        long traditionalTime = endTime - startTime;
        System.out.println("Time: " + traditionalTime + " ns (" + ComparisonUtils.formatTime(traditionalTime) + ")");
        System.out.println("Output: Max = " + traditionalResult.getMaxElement() + ", Min = " + traditionalResult.getMinElement());


        // Stream API Approach
        System.out.println("\nAPPROACH: Stream API");
        ComparisonUtils.repeat("-",80);
        long streamStartTime = System.nanoTime();
        Pair streamResult = MaxMinNumberStream.findMaxMinStream(numbers);
        long streamEndTime = System.nanoTime();
        long streamTime = streamEndTime - streamStartTime;
        System.out.println("Time: " + streamTime + " ns (" + ComparisonUtils.formatTime(streamTime) + ")");
        System.out.println("Output: Max = " + streamResult.getMaxElement() + ", Min = " + streamResult.getMinElement());

    }
}
