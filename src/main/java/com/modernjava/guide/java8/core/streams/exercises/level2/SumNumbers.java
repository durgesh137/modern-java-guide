package com.modernjava.guide.java8.core.streams.exercises.level2;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SumNumbers {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(101, 901,5001, 400, 1, 9, 1000);
        Integer result = nums.stream().reduce(0, Integer::sum);
        System.out.println(result);

        System.out.println("Handling scenario when nulls are present within list");
        nums = Arrays.asList(101, 901,null,5001, 400, 1, 9, 1000);
        System.out.println("List: "+nums);
        // we can handle null with filter
        result = nums.stream().filter(Objects::nonNull).reduce(0,Integer::sum);
        System.out.println(result);
    }
}
