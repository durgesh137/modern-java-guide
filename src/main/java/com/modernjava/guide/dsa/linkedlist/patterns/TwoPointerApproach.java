package com.modernjava.guide.dsa.linkedlist.patterns;

import com.modernjava.guide.dsa.linkedlist.Node;

/**
  * Slow and Fast Pointer Approach (Floyd's Tortoise and Hare).
  *
  * <p>Description:
  * Two pointers traverse the list at different speeds (slow = 1 step, fast = 2 steps).
  * If the list contains a cycle the fast pointer will eventually meet the slow pointer;
  * otherwise the fast pointer reaches the end (null).</p>
  *
  * <p>Time complexity: O(n). Space complexity: O(1).</p>
  *
  * <p>Common operations implemented with this pattern:
  * - Detect cycle presence (hasCycle).
  * - Locate start of cycle (findCycleStart) after detection.
  * - Compute cycle length (cycleLength).
  * - Find middle node of the list (middle).
  * - Find k-th node from end (kthFromEnd) by offsetting pointers.</p>
  *
  * <p>Usage notes and caveats:
  * - Always guard the loop with: <code>fast != null && fast.next != null</code> to avoid NPEs.
  * - Compare nodes by reference (using <code>==</code>), not by their stored values.
  * - Be consistent about pointer initialization (starting <code>fast</code> at <code>head</code>
  *   or <code>head.next</code>) since it affects behavior for even/odd lengths.
  * - After slow==fast (meeting point), reset one pointer to <code>head</code> and advance both
  *   one step at a time to find the cycle start.
  * - To measure cycle length: keep one pointer fixed at meeting point and advance the other
  *   until it returns, counting steps.
  * - This approach applies to singly-linked lists with a well-defined <code>next</code> reference.
  * - Avoid using it when node traversal has side effects or when nodes are not stable references.</p>
  *
  * <p>Example (conceptual):
  * <pre>
  * Node slow = head, fast = head;
  * while (fast != null && fast.next != null) {
  *     slow = slow.next;
  *     fast = fast.next.next;
  *     if (slow == fast) { // cycle detected
  *         // find cycle start by resetting one pointer to head...
  *     }
  * }
  * </pre>
  * </p>
  *
  * @see java.util.Set for an alternative detection strategy that uses extra space
  * @since 1.0
  */
public class TwoPointerApproach {

    public static Node detectCycle(Node head){
        if(null == head){
            return head;
        }
        Node slow = head, fast = head;
        // we will move fast pointer 2 steps at a time, hence check null for both fast and fast.next
        while(null != fast && null != fast.next){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                System.out.println("cycle detected within linkedlist");
                return slow;
            }
        }
        return null;
    }

    public static Node findStartOfCycle(Node head, Node slow, Node fast){
        // here we know already cycle exists within linkedlist head
        if(null == slow || null == fast){
            return slow;
        }
        //right now slow and head met at some point, so we know cycle exists within linkedlist
        //reset slow to head
        slow = head;
        //fast will also move one step now only
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;//slow will point the node where cycle started
    }
}
