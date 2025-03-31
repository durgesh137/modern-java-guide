package com.modernjava.guide.java8.core.lambdas.basics.arguments;

public class lambdaWithNoArgument {
    public static void main(String[] args) {
        lambdaWithNoParameter();
    }

    private static void lambdaWithNoParameter() {
        //needs to be assigned to some target type
        //() -> System.out.println("No arguments with lambda");//it can't be standalone expression
        Runnable r = () -> System.out.println("No arguments with lambda");
        r.run();
    }
}
