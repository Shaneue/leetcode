package medium;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Return the linked list sorted as well.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 *
 * 先处理头部节点，再处理中间。这个是sorted的，不需要先遍历一边，暂且这样吧。
 */
public class RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode h = head;
        Set<Integer> set = new HashSet<>();
        Set<Integer> removeSet = new HashSet<>();
        while (head != null) {
            if (set.contains(head.val)) removeSet.add(head.val);
            set.add(head.val);
            head = head.next;
        }
        while (h != null && removeSet.contains(h.val)) {
            h = h.next;
        }
        ListNode ret = h;
        while (h != null) {
            if (h.next != null && removeSet.contains(h.next.val)) {
                h.next = h.next.next;
            } else {
                h = h.next;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        ListNode n4 = new ListNode(3, null);
        ListNode n3 = new ListNode(1, n4);
        ListNode n2 = new ListNode(1, n3);
        ListNode n1 = new ListNode(2, n2);
        RemoveDuplicatesFromSortedList2 r = new RemoveDuplicatesFromSortedList2();
        ListNode node = r.deleteDuplicates(n1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
