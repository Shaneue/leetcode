package medium;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 * <p>
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
 * You can return the answer in any order.
 * <p>
 * pq默认是最小堆
 */
public class TopkFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (pq.size() < k) {
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            } else if (pq.peek()[1] < entry.getValue()) {
                pq.remove();
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = pq.poll()[0];
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new TopkFrequentElements().topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2)));
    }
}
