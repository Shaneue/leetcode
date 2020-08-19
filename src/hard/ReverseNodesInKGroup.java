package hard;

import common.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * Example:
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * Note:
 * <p>
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * 需要用两个指针记录每一次反转的头与尾，在下一个group需要反转的时候，需要将前一个group的tail指向当前group的头
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode ret = null;
        ListNode headCurrentGroup = null;
        ListNode tailLastGroup = null;
        ListNode t;
        while (true) {
            int c = 0;
            ListNode count = head;
            while (count != null && c < k) {
                c++;
                count = count.next;
            }
            if (c < k) break;
            ListNode tailCurrentGroup = head;
            for (int i = 0; i < k; i++) {
                t = head.next;
                head.next = headCurrentGroup;
                headCurrentGroup = head;
                head = t;
            }
            if (ret == null) ret = headCurrentGroup;
            if (tailLastGroup != null) tailLastGroup.next = headCurrentGroup;
            tailCurrentGroup.next = head;
            tailLastGroup = tailCurrentGroup;
        }
        return ret;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListNode n = new ReverseNodesInKGroup().reverseKGroup(n1, 5);
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }
}
