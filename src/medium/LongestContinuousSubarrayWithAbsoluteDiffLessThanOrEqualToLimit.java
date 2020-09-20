package medium;

import java.util.LinkedList;

/**
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4.
 * Therefore, the size of the longest subarray is 2.
 * Example 2:
 *
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 * Example 3:
 *
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    public int longestSubarray(int[] nums, int limit) {
        LinkedList<Integer> inc = new LinkedList<>();
        LinkedList<Integer> dec = new LinkedList<>();

        int l = 0, r = 0, ret = 0;
        for (int i : nums) {
            while (!inc.isEmpty() && inc.getLast() > i) inc.removeLast();
            inc.add(i);
            while (!dec.isEmpty() && dec.getLast() < i) dec.removeLast();
            dec.add(i);
            while (Math.abs(dec.peek() - inc.peek()) > limit) {
                if (nums[l] == dec.peek()) dec.remove();
                if (nums[l++] == inc.peek()) inc.remove();
            }
            ret = Math.max(r - l + 1, ret);
            r++;
        }
        return ret;
    }

    public static void main(String[] args) {
        LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit l = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit();
        System.out.println(l.longestSubarray(new int[]{2, 2, 2, 4, 4, 2, 5, 5, 5, 5, 5, 2}, 2));
        System.out.println(l.longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
    }
}
