package com.modernjava.guide.java8.core.streams.terminals;


import java.util.Arrays;
import java.util.List;

public class ReduceTerminalOperation {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 90, 500, 400, 1, 9, 1000);
        /**
         * Understanding below statement
         * 1. start value for sum is 0, which is the Identity value.
         * 2. Integer::sum is equivalent to (a,b)-> a+b
         * 3. reduce operation iteratively adds each element in this format
         * 0 + 10 = 10
         * 10 + 90 = 100
         * 100 + 500 = 600
         * 600 + 400 = 1000
         * 1000 + 1 = 1001
         * 1001 + 9 = 1010
         * 1010 + 1000 = 2010 (Final Result)
         */
        Integer result = nums.stream().reduce(0, Integer::sum);
        System.out.println(result);

    }
}
