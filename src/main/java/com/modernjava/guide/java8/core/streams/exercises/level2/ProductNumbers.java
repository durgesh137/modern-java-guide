package com.modernjava.guide.java8.core.streams.exercises.level2;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductNumbers {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,5,10,1, 0, null);
        System.out.println("Original List: "+nums);
        multipleListNumbersVersion1(nums);
        nums = Arrays.asList(null);
        multipleListNumbersVersion2WithOptional(nums);
        multipleListNumbersVersion3(Arrays.asList(1,5,10,1, 0, null));
    }

    private static void multipleListNumbersVersion3(List<Integer> list) {
        Optional<Integer> reduce = list.stream().filter(Objects::nonNull)
                .filter(n -> n != 0)
                .reduce((a, b) -> a * b);
    }

    private static void multipleListNumbersVersion2WithOptional(List<Integer> nums) {
       Optional<Integer> result = nums.stream().filter(Objects::nonNull).reduce((a, b)-> a*b);
       if(result.isPresent()){
           System.out.println("Product: "+result.get());
       }else{
           System.out.println("Product can't be done "+nums);
       }
    }

    private static void multipleListNumbersVersion1(List<Integer> nums) {
        // issue with below code, if only null value is present then, it will return 1
        Integer product = nums.stream().filter(Objects::nonNull).reduce(1, (a, b) -> a * b);
        System.out.println("Final Product: "+product);
    }
}
