package com.modernjava.guide.java8.core.lambdas.functionalInterfaces.predicates.level2;

import java.util.function.Predicate;

public class PositiveEvenNumber {
    public static void main(String[] args) {
        usingTwoPredicatesTogether();
//        way1();

    }

    private static void usingTwoPredicatesTogether() {
        Predicate<Integer> isPositive = (x) -> x>0;
        Predicate<Integer> isEven = (x) -> x % 2 ==0;
        Predicate<Integer> isPositiveEven = isPositive.and(isEven);
        int x = 10;
        System.out.println(x+" is evenPositive? "+  isPositiveEven.test(x));
        x = 1001;
        System.out.println(x+" is evenPositive? "+  isPositiveEven.test(x));
        x = -12;
        System.out.println(x+" is evenPositive? "+  isPositiveEven.test(x));
        x = 0;
        System.out.println(x+" is evenPositive? "+  isPositiveEven.test(x));

    }

    private static void way1() {
        Predicate<Integer> isEvenPositive = (num) -> {
            return num > 0 && num % 2 == 0;
        };
        int num = -101;
        System.out.println(num+ " is evenPositive? "+isEvenPositive.test(num));
        num = 120;
        System.out.println(num+ " is evenPositive? "+isEvenPositive.test(num));
        num = 0;
        System.out.println(num+ " is evenPositive? "+isEvenPositive.test(num));
    }
}
