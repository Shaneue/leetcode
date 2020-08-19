package medium;

import common.ListNode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * Example:
 * <p>
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode prefix = new ListNode(0);
        ListNode l1 = prefix;
        ListNode suffix = new ListNode(0);
        ListNode l2 = suffix;
        while (head != null) {
            if (head.val < x) {
                l1.next = head;
                l1 = l1.next;
            } else {
                l2.next = head;
                l2 = l2.next;
            }
            head = head.next;
        }
        l2.next = null;
        l1.next = suffix.next;
        return prefix.next;
    }

    public static void main(String[] args) {
    }
}
