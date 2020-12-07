package contest.hard;

import java.util.*;

/**
 * You are given an integer array nums​​​ and an integer k. You are asked to distribute this array into k subsets of equal size such that there are no two equal elements in the same subset.
 *
 * A subset's incompatibility is the difference between the maximum and minimum elements in that array.
 *
 * Return the minimum possible sum of incompatibilities of the k subsets after distributing the array optimally, or return -1 if it is not possible.
 *
 * A subset is a group integers that appear in the array with no particular order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,4], k = 2
 * Output: 4
 * Explanation: The optimal distribution of subsets is [1,2] and [1,4].
 * The incompatibility is (2-1) + (4-1) = 4.
 * Note that [1,1] and [2,4] would result in a smaller sum, but the first subset contains 2 equal elements.
 * Example 2:
 *
 * Input: nums = [6,3,8,1,3,1,2,2], k = 4
 * Output: 6
 * Explanation: The optimal distribution of subsets is [1,2], [2,3], [6,8], and [1,3].
 * The incompatibility is (2-1) + (3-2) + (8-6) + (3-1) = 6.
 * Example 3:
 *
 * Input: nums = [5,3,3,6,3,3], k = 3
 * Output: -1
 * Explanation: It is impossible to distribute nums into 3 subsets where no two elements are equal in the same subset.
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 16
 * nums.length is divisible by k
 * 1 <= nums[i] <= nums.length
 *
 * 还有其他优化方法，有空再改
 */
public class MinimumIncompatibility {
    public int minimumIncompatibility(int[] nums, int k) {
        if (nums.length == k) return 0;
        int d = nums.length / k;
        int ret = dfs(new HashMap<>(), nums, d);
        return ret == 8888 ? -1 : ret;
    }

    int dfs(Map<Integer, Integer> memo, int[] nums, int d) {
        int cached = Arrays.hashCode(nums);
        if (memo.containsKey(cached)) return memo.get(cached);
        int ret = 8888;
        if (nums.length == 0) return 0;
        for (int i = (1 << nums.length) - 1; i >= 1; i--) {
            if (Integer.bitCount(i) == d) {
                List<Integer> list = getList(nums, i);
                if (list.size() != d) continue;
                int[] newNums = new int[nums.length - d];
                for (int k = 0, j = 0; k < nums.length; k++) {
                    if (((1 << k) & i) == 0) newNums[j++] = nums[k];
                }
                ret = Math.min(ret, Collections.max(list) - Collections.min(list) + dfs(memo, newNums, d));
            }
        }
        memo.put(cached, ret);
        return ret;
    }

    List<Integer> getList(int[] nums, int i) {
        Set<Integer> set = new HashSet<>();
        for (int k = 0; k < nums.length; k++) {
            if (((1 << k) & i) != 0) set.add(nums[k]);
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        MinimumIncompatibility m = new MinimumIncompatibility();
        System.out.println(m.minimumIncompatibility(new int[]{13, 4, 7, 3, 3, 1, 12, 9, 11, 10, 13, 3, 12, 7, 6, 7}, 8));
        System.out.println(m.minimumIncompatibility(new int[]{5, 3, 2, 11, 5, 8, 7, 7, 6, 2, 4, 5}, 6));
        System.out.println(m.minimumIncompatibility(new int[]{5, 3, 3, 6, 2, 4}, 3));
        System.out.println(m.minimumIncompatibility(new int[]{6, 3, 8, 1, 3, 1, 2, 2}, 4));
        System.out.println(m.minimumIncompatibility(new int[]{5, 3, 3, 6, 3, 3}, 3));
    }
}
