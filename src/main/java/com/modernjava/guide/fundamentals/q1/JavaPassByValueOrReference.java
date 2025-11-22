package com.modernjava.guide.fundamentals.q1;

public class JavaPassByValueOrReference {
    public static void conceptUnderstanding(){
        System.out.println("Working with Primitives");
        PrimitiveWork.workingWithPrimitives();
        System.out.println("\nWorking with Array");
        ArrayWork.workingWithArrays();
        System.out.println("\nReassigning the Objects");
        PojoWork.workingWithObjectState();
    }
}
