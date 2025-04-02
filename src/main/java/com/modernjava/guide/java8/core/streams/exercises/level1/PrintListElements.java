package com.modernjava.guide.java8.core.streams.exercises.level1;

import java.util.Arrays;
import java.util.List;

public class PrintListElements {
    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("Apple", "Mango", "Strawberry", "Dragon Fruit");
        fruits.stream().forEach(System.out::println);
    }
}
