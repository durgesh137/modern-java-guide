package com.modernjava.guide.java8.core.lambdas.basics.arguments;

import com.modernjava.guide.java8.core.lambdas.basics.custom.Person;
import com.modernjava.guide.java8.core.lambdas.basics.custom.Sum;

public class MultipleArgumentsLambda {
    public static void main(String[] args) {
        System.out.println("Two Arguments - lambda example");
        addNumbers();
        System.out.println("Two Arguments - lambda with braces example");
        System.out.println(printDetails());
    }

    private static String printDetails() {
        Person p = (name, age) -> {
            String info = name + " is " + age + " year old";
            return info;
        };
        return p.getDetails("Sam",21);
    }

    private static void addNumbers() {
        Sum s = (x, y) -> x + y;
        int add = s.add(10, 15);
        System.out.println(add);
    }
}
