package com.modernjava.guide.fundamentals.q1;

public class ArrayWork {
    public static void workingWithArrays(){
        int a[] = {10,20,30,40};
        System.out.println("Contents of Array prior to modification: ");
        printArray(a);
        modifyArray(a);
        System.out.println("Array content now within original method: ");
        printArray(a);

    }

    private static void modifyArray(int[] a) {
        a[2] = -1;
        System.out.println("Array within modifyArray method: ");
        printArray(a);
    }

    private static void printArray(int array[]){
        for(int x: array){
            System.out.print(x+", ");
        }
        System.out.println();
    }
}
