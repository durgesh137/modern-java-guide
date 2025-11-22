package com.modernjava.guide.fundamentals.q2;

import java.util.ArrayList;
import java.util.List;

public class ImmutableClass {
    public static void workingWithImmutables(){
        Person sam = new Person("Sam", 25, getContacts());
        System.out.println(sam);
        // lets try to modify contacts by adding one more contact
        sam.getContacts().add("NEW_PERSON_ADDED");
        System.out.println("After adding new contact for sam:"+sam);
        // even though it immutable class, but its field is modified, how to prevent it
        System.out.println("\nHandling this issue with PersonV2");
        workingWithPersonV2();
    }

    private static void workingWithPersonV2() {
        PersonV2 samV2 = new PersonV2("SamV2", 30, getContacts());
        System.out.println("New Object of PersonV2 created...");
        System.out.println(samV2);
        // lets try to modify contacts by adding one more contact
        samV2.getContacts().add("NEW_PERSON_V2");
        System.out.println("After adding new contact for samV2:"+ samV2);

    }

    private static List<String> getContacts() {
        ArrayList<String> contacts = new ArrayList<>();
        contacts.add("Tim");
        contacts.add("Howie");
        return contacts;
    }
}
