package com.modernjava.guide.collection;

import java.util.Arrays;
import java.util.List;

public class ListsWorking {
    public static void main(String[] args) {
        workingWithArraysListMethod();
    }

    private static void workingWithArraysListMethod() {
        List<Integer> nums = Arrays.asList(101, 901,5001, 400, 1, 9, 1000);

        // addition not allowed, since nums is fixed-size list backed by original array
        nums.add(null);//UnsupportedOperationException thrown
        System.out.println("List: "+nums);

    }
}
