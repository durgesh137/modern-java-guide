package com.modernjava.guide.fundamentals.q1;

public class PojoWork {
    public static void workingWithObjectState(){
        Order order = new Order(10, "Pen");
        System.out.println("Before Modifying: ");
        System.out.println(order);
        modifyOrder(order);
        System.out.println("After modifying: ");
        System.out.println(order);

        System.out.println("\nReassigning the Object state ");
        reassignTheObject(order);
        System.out.println("Object data after reassignment outside the method: ");
        System.out.println(order);
    }

    private static void reassignTheObject(Order order) {
        order = new Order(100, "NoteBook");
    }

    private static void modifyOrder(Order order) {
        order.setCost(20);
    }


}
