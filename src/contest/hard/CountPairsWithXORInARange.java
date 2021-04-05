package contest.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a (0-indexed) integer array nums and two integers low and high, return the number of nice pairs.
 *
 * A nice pair is a pair (i, j) where 0 <= i < j < nums.length and low <= (nums[i] XOR nums[j]) <= high.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,2,7], low = 2, high = 6
 * Output: 6
 * Explanation: All nice pairs (i, j) are as follows:
 *     - (0, 1): nums[0] XOR nums[1] = 5
 *     - (0, 2): nums[0] XOR nums[2] = 3
 *     - (0, 3): nums[0] XOR nums[3] = 6
 *     - (1, 2): nums[1] XOR nums[2] = 6
 *     - (1, 3): nums[1] XOR nums[3] = 3
 *     - (2, 3): nums[2] XOR nums[3] = 5
 * Example 2:
 *
 * Input: nums = [9,8,4,2,1], low = 5, high = 14
 * Output: 8
 * Explanation: All nice pairs (i, j) are as follows:
 * ​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
 *     - (0, 3): nums[0] XOR nums[3] = 11
 *     - (0, 4): nums[0] XOR nums[4] = 8
 *     - (1, 2): nums[1] XOR nums[2] = 12
 *     - (1, 3): nums[1] XOR nums[3] = 10
 *     - (1, 4): nums[1] XOR nums[4] = 9
 *     - (2, 3): nums[2] XOR nums[3] = 6
 *     - (2, 4): nums[2] XOR nums[4] = 5
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 2 * 10^4
 * 1 <= low <= high <= 2 * 10^4
 */
public class CountPairsWithXORInARange {
    public int countPairs(int[] nums, int low, int high) {
        return countPairs(nums, high + 1) - countPairs(nums, low);
    }

    private int countPairs(int[] nums, int x) {
        int ret = 0;
        Map<Integer, Integer> count1 = new HashMap<>(), count2;
        for (int i : nums) {
            count1.put(i, count1.getOrDefault(i, 0) + 1);
        }
        while (x > 0) {
            count2 = new HashMap<>();
            for (int k : count1.keySet()) {
                int v = count1.get(k);
                count2.put(k >> 1, count2.getOrDefault(k >> 1, 0) + v);
                if ((x & 1) == 1) {
                    ret += v * count1.getOrDefault((x - 1) ^ k, 0);
                }
            }
            count1 = count2;
            x >>= 1;
        }
        return ret / 2;
    }
}
