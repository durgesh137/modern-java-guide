package com.modernjava.guide.java8.core.lambdas.functionalInterfaces.predicates;

import java.util.function.Predicate;

public class EvenNumber {
    public static void main(String[] args) {
        Predicate<Integer> isEven = ( x) -> x%2==0;
        int num = 12;
        boolean test = isEven.test(num);
        System.out.println("Is "+num+" even? "+test);
        num=11;
        test = isEven.test(num);
        System.out.println("Is "+num+" even? "+test);

    }
}

