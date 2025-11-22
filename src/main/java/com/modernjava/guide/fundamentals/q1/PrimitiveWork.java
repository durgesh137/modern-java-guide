package com.modernjava.guide.fundamentals.q1;

public class PrimitiveWork {
    public static void workingWithPrimitives(){
        int x = 10;
        System.out.println("Initial value of x: "+x);
        modifyPrimitive(x);
        System.out.println("After modification, x: "+x);
    }

    private static void modifyPrimitive(int x) {
        x = 20;
    }
}
