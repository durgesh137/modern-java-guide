package com.modernjava.guide.dsa.linkedlist;

public class LinkedListOperations {
    public static Node getNewNode(int data){
        return new Node(data);
    }

    public static Node getLinkedList(){
        Node head = getNewNode(10);
        head.next = getNewNode(12);
        head.next.next=getNewNode(15);
        head.next.next.next=getNewNode(20);
        return head;
    }

    public static void printLinkedList(Node head){
        System.out.print("Elements present in LinkedList are: ");
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data+" ->");
            temp = temp.next;
        }
        System.out.println();
    }

    public static int getLength(Node head){
        if(head == null)
            return 0;
        Node temp = head;
        int count =0;
        //1->2->3->4->5
        while(temp != null){//10,12,15,20
            ++count;//1,2,3,4
            temp = temp.next;//12,15,20, null
        }
        return count;
    }

    public static Node insertNode(int position, Node newNode, Node head){
        if(position < 0 || newNode == null){
            return head;
        }
        if(position == 0){
            return insertAtStart(head, newNode);
        }else{
            return insertAt(position,newNode,head);
        }
    }

    private static Node insertAt(int position, Node newNode, Node head){
        if(head == null ) {
            return position == 0 ? newNode : null;
        }
        else {
            //always execute when position >=1
            int index = 0;
            Node curr = head;
            Node prev = null;
            while (curr != null && index < position) {
                prev = curr;
                curr = curr.next;
                index++;
            }

            if (index < position) {
                // here end of linkedlist is reached
                return head;
            }
            //inserting between previous node and current node
            prev.next = newNode;
            newNode.next = curr;
            return head;
        }
    }

    private static Node insertAtStart(Node head, Node newNode) {
        if(head == null){
            return newNode;
        }
        //Node temp = head;
        newNode.next=head;
        head = newNode;
        return head;
    }

    public static Node deleteKthNode(int position, Node head){
        if(head == null || position <0){
            return head;
        }
        //head needs to be removed then
        if(position == 0){
            return head.next;
        }
        //now element other than head should be removed
        Node curr = head;
        Node prev = null;
        int count = 0;
        while(curr != null && count < position){
            prev = curr;
            curr = curr.next;
            count++;
        }
        //check if count is still less than position
        if(count < position){
            //here end of linkedlist is reached
            return head;
        }
        //Node next = curr.next;
        prev.next=curr.next;
        return head;
    }

    public static void simulateLinkedList(){
        Node head = LinkedListOperations.getLinkedList();
        LinkedListOperations.printLinkedList(head);
        System.out.println("\nLength of LinkedList: "+LinkedListOperations.getLength(head));

        Node newNode = LinkedListOperations.getNewNode(25);
        head = LinkedListOperations.insertNode(0,newNode,head);
        LinkedListOperations.printLinkedList(head);
        head = LinkedListOperations.deleteKthNode(3,head);
        LinkedListOperations.printLinkedList(head);

        Node headWithLoop = LinkedListOperations.generateLinkedListWithLoop();
        LinkedListProblems.detectCycleWithinLinkedList(head);
        LinkedListProblems.detectCycleWithinLinkedList(headWithLoop);
    }

    public static Node generateLinkedListWithLoop() {
        Node head = getNewNode(10);
        head.next = getNewNode(12);
        head.next.next = getNewNode(15);
        head.next.next.next = getNewNode(20);
        Node tail = head.next.next.next;
        tail.next = head;
        return head;
    }
}
