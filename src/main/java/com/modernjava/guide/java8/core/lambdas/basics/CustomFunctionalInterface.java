package com.modernjava.guide.java8.core.lambdas.basics;

import com.modernjava.guide.java8.core.lambdas.basics.custom.Printer;

public class CustomFunctionalInterface {
    public static void main(String[] args) {
        System.out.println("No argument with lambda example");
        Printer p = () -> System.out.println("I am printer");
        p.print();
    }
}
