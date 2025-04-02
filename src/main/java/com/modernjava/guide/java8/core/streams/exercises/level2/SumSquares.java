package com.modernjava.guide.java8.core.streams.exercises.level2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SumSquares {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> squaresSum = integers.stream().reduce((sum, x) -> sum + x * x);
        if(squaresSum.isPresent()){
            System.out.println("Elements square sum: "+squaresSum.get());
        }else{
            System.out.println("Elements square sum could not be computed");
        }
    }
}
