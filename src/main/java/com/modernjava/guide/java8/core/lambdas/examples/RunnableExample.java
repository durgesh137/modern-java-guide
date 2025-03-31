package com.modernjava.guide.java8.core.lambdas.examples;

public class RunnableExample {
    public static void main(String[] args) {
        System.out.println("======Runnable before Java 8======");
        runnablePriorJava8();

        System.out.println("======Runnable with Java 8======");
        runnableWithJava8();

    }

    private static void runnableWithJava8() {
        Runnable runnable = () -> System.out.println("Runnable with Java 8");
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static void runnablePriorJava8() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable example prior java 8");
            }
        };
        Thread t1 = new Thread(runnable);
        t1.start();
    }
}
