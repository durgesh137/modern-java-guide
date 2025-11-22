package com.modernjava.guide.fundamentals.q2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

final public class PersonV2 {
    private final String name;
    private final int age;
    private final List<String> contacts;

    public PersonV2(String name, int age, List<String> contacts) {
        this.name = name;
        this.age = age;
        this.contacts = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(contacts)));
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", contacts=" + contacts +
                '}';
    }
}