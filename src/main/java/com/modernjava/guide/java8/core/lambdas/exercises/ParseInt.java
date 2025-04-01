package com.modernjava.guide.java8.core.lambdas.exercises;

import java.util.function.Function;

public class ParseInt {
    public static void main(String[] args) {
        lowerEnvironmentParseInt();
        Function<String, Integer> parseInt = s -> ParseInt.safeParseInt(s,0);
        System.out.println(parseInt.apply("sam"));
        System.out.println(parseInt.apply("123"));
    }

    private static void lowerEnvironmentParseInt() {
        Function<String,Integer> parseInt = (String s) -> Integer.parseInt(s);
        Integer number = parseInt.apply("123");
        System.out.println(number);
        //Exception
//        number = parseInt.apply("qw");
//        System.out.println(number);
    }

    public static Integer safeParseInt(String input, Integer defaultValue){
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }
}
