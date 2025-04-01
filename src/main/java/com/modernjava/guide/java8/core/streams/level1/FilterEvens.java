package com.modernjava.guide.java8.core.streams.level1;

import java.util.Arrays;
import java.util.List;

public class FilterEvens {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10,11, 23, 1001, 2, 36);
        nums.stream().filter(num -> num % 2 == 0).forEach(System.out::println);
    }
}
