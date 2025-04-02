package com.modernjava.guide.java8.core.streams.exercises.level1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MakeStringsUppercase {
    public static void main(String[] args) {
        List<String> places = Arrays.asList("Australia", "usa", "New York", "Africa", "Uk");
        //maping and printing with forEach
        places.stream().map(s->s.toUpperCase()).forEach(System.out::println);

        System.out.println(places);
        List<String> uppercases = places.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(uppercases);

    }
}
