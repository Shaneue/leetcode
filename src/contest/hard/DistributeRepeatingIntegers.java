package contest.hard;

import java.util.*;

/**
 * You are given an array of n integers, nums, where there are at most 50 unique values in the array. You are also given an array of m customer order quantities, quantity, where quantity[i] is the amount of integers the ith customer ordered. Determine if it is possible to distribute nums such that:
 *
 * The ith customer gets exactly quantity[i] integers,
 * The integers the ith customer gets are all equal, and
 * Every customer is satisfied.
 * Return true if it is possible to distribute nums according to the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], quantity = [2]
 * Output: false
 * Explanation: The 0th customer cannot be given two different integers.
 * Example 2:
 *
 * Input: nums = [1,2,3,3], quantity = [2]
 * Output: true
 * Explanation: The 0th customer is given [3,3]. The integers [1,2] are not used.
 * Example 3:
 *
 * Input: nums = [1,1,2,2], quantity = [2,2]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given [2,2].
 * Example 4:
 *
 * Input: nums = [1,1,2,3], quantity = [2,2]
 * Output: false
 * Explanation: Although the 0th customer could be given [1,1], the 1st customer cannot be satisfied.
 * Example 5:
 *
 * Input: nums = [1,1,1,1,1], quantity = [2,3]
 * Output: true
 * Explanation: The 0th customer is given [1,1], and the 1st customer is given [1,1,1].
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= 1000
 * m == quantity.length
 * 1 <= m <= 10
 * 1 <= quantity[i] <= 10^5
 * There are at most 50 unique values in nums.
 */
public class DistributeRepeatingIntegers {
    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) count.put(i, count.getOrDefault(i, 0) + 1);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            list.add(entry.getValue());
        }
        quantity = Arrays.stream(quantity).boxed().sorted((a, b) -> Integer.compare(b, a)).mapToInt(Integer::intValue).toArray();
        return dfs(list, quantity, 0);
    }

    boolean dfs(List<Integer> list, int[] quantity, int idx) {
        if (idx >= quantity.length) return true;
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j) >= quantity[idx]) {
                int t = list.get(j);
                list.set(j, t - quantity[idx]);
                if (dfs(list, quantity, idx + 1)) return true;
                list.set(j, t);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DistributeRepeatingIntegers d = new DistributeRepeatingIntegers();
        System.out.println(d.canDistribute(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31, 31, 32, 32, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 39, 39, 40, 40, 41, 41, 42, 42, 43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48, 48, 49, 49, 50, 50}, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 3}));
        System.out.println(d.canDistribute(new int[]{154, 533, 533, 533, 154, 154, 533, 154, 154}, new int[]{3, 2, 2, 2}));
        System.out.println(d.canDistribute(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, new int[]{3, 3, 3, 4}));
        System.out.println(d.canDistribute(new int[]{1, 1, 2, 2}, new int[]{2, 2}));
        System.out.println(d.canDistribute(new int[]{1, 1, 2, 3}, new int[]{2, 2}));
        System.out.println(d.canDistribute(new int[]{1, 1, 1, 1, 1}, new int[]{2, 3}));
    }
}
