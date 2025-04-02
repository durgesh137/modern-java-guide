package com.modernjava.guide.collection.java9;

import java.util.List;

public class ListsWorking {
    public static void main(String[] args) {
        workingWithListOfMethod();
    }

    private static void workingWithListOfMethod() {
        List<Integer> nums = List.of(101, 901,5001, 400, 1, 9, 1000);

        // this is immutable list, ImmutableCollection
        nums.add(null);//UnsupportedOperationException thrown
        System.out.println("List: "+nums);

    }
}
