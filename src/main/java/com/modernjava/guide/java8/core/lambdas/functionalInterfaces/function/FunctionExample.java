package com.modernjava.guide.java8.core.lambdas.functionalInterfaces.function;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String,Integer> parseInt = (String s) -> Integer.parseInt(s);
        Integer number = parseInt.apply("123");
        System.out.println(number);
    }
}
