package com.modernjava.guide.fundamentals.q2;

import java.util.List;

final public class Person {
    private final String name;
    private final int age;
    private final List<String> contacts;

    public Person(String name, int age, List<String> contacts) {
        this.name = name;
        this.age = age;
        this.contacts = contacts;
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
