package com.modernjava.guide.java8.core.streams.exercises.level2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MaxMinNumber {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 20, -1, 234, 121332 - 1, 12312, 0, -2332, 10);
        findMax(nums);
        findMin(nums);

    }

    private static void findMin(List<Integer> nums) {
        Optional<Integer> minOne = nums.stream().reduce((x, y) -> x < y ? x : y);
        if(minOne.isPresent()){
            System.out.println(minOne.get());
        }else {
            System.out.println("minOne does not exist");
        }
    }

    private static void findMax(List<Integer> nums) {
        Optional<Integer> maxOne = nums.stream().reduce((x, y) -> x > y ? x : y);
        if(maxOne.isPresent()){
            System.out.println(maxOne.get());
        }else {
            System.out.println("maxOne does not exist");
        }
    }
}
