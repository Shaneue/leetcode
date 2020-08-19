package medium;

import common.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * <p>
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * <p>
 * 前面补足一个节点逻辑会比较简单
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0), pre = dummy;
        dummy.next = head;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode current = pre.next;
        for (int i = m; i < n; i++) {
            ListNode t = current.next;
            current.next = t.next;
            t.next = pre.next;
            pre.next = t;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n4 = new ListNode(4);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        ListNode ret = new ReverseLinkedList2().reverseBetween(n1, 3, 4);
        while (ret != null) {
            System.out.println(ret.val);
            ret = ret.next;
        }
    }
}
