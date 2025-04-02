package com.modernjava.guide.java8.core.streams.exercises.level2;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ClubStrings {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Let's", "Learn", "Lambdas", "and", "Stream", "Apis", "within", "Java 8");
        Optional<String> line = words.stream().filter(Objects::nonNull).reduce((a, b) -> a + " " + b);
        if(line.isPresent()){
            System.out.println(line.get());
        }else{
            System.out.println("Invalid list");
        }
    }
}
