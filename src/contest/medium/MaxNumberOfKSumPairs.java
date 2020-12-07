package contest.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 *
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 */
public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            int v = map.getOrDefault(i, 0);
            map.put(i, v + 1);
        }
        int ret = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int target = k - entry.getKey();
            if (target == entry.getKey()) {
                ret += entry.getValue() / 2 * 2;
            } else if (map.containsKey(k - entry.getKey())) {
                ret += Math.min(entry.getValue(), map.get(k - entry.getKey()));
            }
        }
        return ret / 2;
    }

    public static void main(String[] args) {
        MaxNumberOfKSumPairs m = new MaxNumberOfKSumPairs();
        System.out.println(m.maxOperations(new int[]{2, 2, 2, 3, 1, 1, 4, 1}, 4));
    }
}
