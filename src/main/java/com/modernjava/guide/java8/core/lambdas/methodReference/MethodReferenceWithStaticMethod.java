package com.modernjava.guide.java8.core.lambdas.methodReference;

import com.modernjava.guide.java8.core.lambdas.Java8Utils;

import java.util.function.Function;

public class MethodReferenceWithStaticMethod {
    public static void main(String[] args) {
        /*
         * alternative to lambda approach
         * Function<String, Integer> parse = s -> Java8Utils.parseNumber(s);
         */
        Function<String,Integer> numParser = Java8Utils::parseNumber;

        Integer num = numParser.apply("123 ");
        System.out.println("Parsed Number: "+num);
    }

}
