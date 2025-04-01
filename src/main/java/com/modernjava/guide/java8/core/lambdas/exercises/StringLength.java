package com.modernjava.guide.java8.core.lambdas.exercises;

import java.util.function.Function;

/**
 * Lambda expression which takes string as input and
 * return its length
 *
 * Here Function interface is used from util package
 */
public class StringLength {
    public static void main(String[] args) {
        Function<String, Integer> getLength = (String s) -> s != null? s.length():0;
        System.out.println(getLength.apply("sam"));
        System.out.println(getLength.apply(null));

    }
}
