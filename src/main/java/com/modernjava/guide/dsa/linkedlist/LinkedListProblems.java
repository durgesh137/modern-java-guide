package com.modernjava.guide.dsa.linkedlist;

import com.modernjava.guide.dsa.linkedlist.patterns.TwoPointerApproach;

public class LinkedListProblems {
    public static void detectCycleWithinLinkedList(Node head){
        System.out.println("==========Working on LinkedList===========");
        Node cycle = TwoPointerApproach.detectCycle(head);
        if(null != cycle){
            System.out.println("Cycle exists within Linkedlist 2");
            Node cycleStartingNode = TwoPointerApproach.findStartOfCycle(head,cycle,cycle);
            System.out.println("Cycle starts within Linkedlist at "+cycleStartingNode.data);
        }else{
            System.out.println("Cycle does not exist within linkedlist 2");
            LinkedListOperations.printLinkedList(head);
        }
    }

}
