package medium;

import common.ListNode;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 * <p>
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        int len;
        if (head == null || head.next == null || k == 0) return head;
        ListNode h = head;
        for (len = 1; head.next != null; len++, head = head.next) ;
        head.next = h;
        for (int i = 0; i < len - k % len; i++) head = head.next;
        h = head;
        head = head.next;
        h.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode test1 = new ListNode(0);
        ListNode next1 = new ListNode(1);
        ListNode next2 = new ListNode(2);
        test1.next = next1;
        next1.next = next2;

        //test1
        test1 = new RotateList().rotateRight(test1, 4);
        assert test1.val == 2;
        assert test1.next.val == 0;
        assert test1.next.next.val == 1;

        ListNode test2 = new ListNode(1);
        next1 = new ListNode(2);
        next2 = new ListNode(3);

        ListNode next3 = new ListNode(4);
        ListNode next4 = new ListNode(5);
        test2.next = next1;
        next1.next = next2;
        next2.next = next3;
        next3.next = next4;

        //test2
        test2 = new RotateList().rotateRight(test2, 2);
        assert test2.val == 4;
        assert test2.next.val == 5;
        assert test2.next.next.val == 1;
        assert test2.next.next.next.val == 2;
        assert test2.next.next.next.next.val == 3;

        //test3
        ListNode test3 = new RotateList().rotateRight(null, 0);
        assert test3 == null;

        ListNode test4 = new ListNode(1);

        //test3
        test4 = new RotateList().rotateRight(test4, 0);
        assert test4.val == 1;
    }
}
