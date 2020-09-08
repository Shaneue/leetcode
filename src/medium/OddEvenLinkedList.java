package medium;

import common.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 *
 *
 * Constraints:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * The length of the linked list is between [0, 10^4].
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode even = new ListNode();
        ListNode evenHead = even;
        ListNode odd = new ListNode();
        ListNode oddHead = odd;
        int c = 0;
        while (head != null) {
            if ((++c) % 2 == 1) {
                odd.next = head;
                odd = odd.next;
            } else {
                even.next = head;
                even = even.next;
            }
            head = head.next;
        }
        odd.next = evenHead.next;
        even.next = null;
        return oddHead.next;
    }

    public static void main(String[] args) {
        OddEvenLinkedList o = new OddEvenLinkedList();
        ListNode n3 = new ListNode(3);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        System.out.println(o.oddEvenList(n1));
    }
}
