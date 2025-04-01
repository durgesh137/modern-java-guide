package com.modernjava.guide.java8.core.lambdas.functionalInterfaces.predicates.level1;

import java.util.function.Predicate;

public class EmptyString {
    public static void main(String[] args) {
        Predicate<String> isEmpty = (s) -> s==null || s.length()==0;
        String input = "";
        boolean test = isEmpty.test(input);
        System.out.println(input+" is empty? "+test);
        input = "asgad";
        test = isEmpty.test(input);
        System.out.println(input+" is empty? "+test);
        input = null;
        test = isEmpty.test(input);
        System.out.println(input+" is empty? "+test);

    }
}
