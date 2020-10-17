package medium;

import common.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 *
 * After doing so, return the head of the final linked list.  You may return any such answer.
 *
 *
 *
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * Example 1:
 *
 * Input: head = [1,2,-3,3,1]
 * Output: [3,1]
 * Note: The answer [1,2,1] would also be accepted.
 * Example 2:
 *
 * Input: head = [1,2,3,-3,4]
 * Output: [1,2,4]
 * Example 3:
 *
 * Input: head = [1,2,3,-3,-2]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The given linked list will contain between 1 and 1000 nodes.
 * Each node in the linked list has -1000 <= node.val <= 1000.
 *
 * 懒得维护preSum的话，直接从头开始数即可
 */
public class RemoveZeroSumConsecutiveNodesFromLinkedList {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode ret = dummy;
        Map<Integer, ListNode> preSum = new HashMap<>();
        int sum = 0;
        while (dummy != null) {
            sum += dummy.val;
            if (preSum.containsKey(sum)) {
                dummy = preSum.get(sum).next;
                int key = sum + dummy.val;
                while (key != sum) {
                    preSum.remove(key);
                    dummy = dummy.next;
                    key += dummy.val;
                }
                preSum.get(sum).next = dummy.next;
            } else
                preSum.put(sum, dummy);
            dummy = dummy.next;
        }
        return ret.next;
    }

    public static void main(String[] args) {
        ListNode n5 = new ListNode(4);
        ListNode n4 = new ListNode(-3, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        RemoveZeroSumConsecutiveNodesFromLinkedList r = new RemoveZeroSumConsecutiveNodesFromLinkedList();
        ListNode n = r.removeZeroSumSublists(n1);
        System.out.println();
    }
}
