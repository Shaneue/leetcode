package medium;

import java.util.*;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
 * Return True if its possible otherwise return False.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 * <p>
 * Input: nums = [3,3,2,2,1,1], k = 3
 * Output: true
 * Example 4:
 * <p>
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 * <p>
 * 需要先排序
 */
public class DivideArrayInSetsOfKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] nums, int k) {
        int l = nums.length;
        if (l % k != 0) return false;
        int size = l / k;
        Arrays.sort(nums);
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n - 1)) {
                Queue<Integer> q = map.get(n - 1);
                int len = q.poll();
                if (q.isEmpty()) map.remove(n - 1);
                if (++len == k) {
                    size--;
                } else {
                    q = map.getOrDefault(n, new LinkedList<>());
                    q.add(len);
                    map.put(n, q);
                }
            } else {
                if (map.size() == size) return false;
                Queue<Integer> q = map.getOrDefault(n, new LinkedList<>());
                q.add(1);
                map.put(n, q);
            }
        }
        return size == 0;
    }

    public static void main(String[] args) {
        DivideArrayInSetsOfKConsecutiveNumbers d = new DivideArrayInSetsOfKConsecutiveNumbers();
        System.out.println(d.isPossibleDivide(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(d.isPossibleDivide(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(d.isPossibleDivide(new int[]{3, 3, 2, 2, 1, 1}, 3));
    }
}
