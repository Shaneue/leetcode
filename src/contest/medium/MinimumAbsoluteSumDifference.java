package contest.medium;

import java.util.TreeSet;

/**
 * You are given two positive integer arrays nums1 and nums2, both of length n.
 *
 * The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).
 *
 * You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.
 *
 * Return the minimum absolute sum difference after replacing at most one element in the array nums1. Since the answer may be large, return it modulo 109 + 7.
 *
 * |x| is defined as:
 *
 * x if x >= 0, or
 * -x if x < 0.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,5], nums2 = [2,3,5]
 * Output: 3
 * Explanation: There are two possible optimal solutions:
 * - Replace the second element with the first: [1,7,5] => [1,1,5], or
 * - Replace the second element with the third: [1,7,5] => [1,5,5].
 * Both will yield an absolute sum difference of |1-2| + (|1-3| or |5-3|) + |5-5| = 3.
 * Example 2:
 *
 * Input: nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * Output: 0
 * Explanation: nums1 is equal to nums2 so no replacement is needed. This will result in an
 * absolute sum difference of 0.
 * Example 3:
 *
 * Input: nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * Output: 20
 * Explanation: Replace the first element with the second: [1,10,4,4,2,7] => [10,10,4,4,2,7].
 * This yields an absolute sum difference of |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 *
 *
 * Constraints:
 *
 * n == nums1.length
 * n == nums2.length
 * 1 <= n <= 10^5
 * 1 <= nums1[i], nums2[i] <= 10^5
 */
public class MinimumAbsoluteSumDifference {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        int maxDiff = 0;
        long ret = 0;
        for (int i : nums1) set.add(i);
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            ret += diff;
            if (set.contains(nums2[i])) maxDiff = Math.max(maxDiff, diff);
            else {
                Integer lower = set.lower(nums2[i]);
                if (lower != null)
                    maxDiff = Math.max(maxDiff, diff - (nums2[i] - lower));
                Integer higher = set.higher(nums2[i]);
                if (higher != null)
                    maxDiff = Math.max(maxDiff, diff - (higher - nums2[i]));
            }
        }
        return (int) ((ret - maxDiff) % (1e9 + 7));
    }

    public static void main(String[] args) {
        MinimumAbsoluteSumDifference m = new MinimumAbsoluteSumDifference();
        System.out.println(m.minAbsoluteSumDiff(
                new int[]{38, 48, 73, 55, 25, 47, 45, 62, 15, 34, 51, 20, 76, 78, 38, 91, 69, 69, 73, 38, 74, 75, 86, 63, 73, 12, 100, 59, 29, 28, 94, 43, 100, 2, 53, 31, 73, 82, 70, 94, 2, 38, 50, 67, 8, 40, 88, 87, 62, 90, 86, 33, 86, 26, 84, 52, 63, 80, 56, 56, 56, 47, 12, 50, 12, 59, 52, 7, 40, 16, 53, 61, 76, 22, 87, 75, 14, 63, 96, 56, 65, 16, 70, 83, 51, 44, 13, 14, 80, 28, 82, 2, 5, 57, 77, 64, 58, 85, 33, 24},
                new int[]{90, 62, 8, 56, 33, 22, 9, 58, 29, 88, 10, 66, 48, 79, 44, 50, 71, 2, 3, 100, 88, 16, 24, 28, 50, 41, 65, 59, 83, 79, 80, 91, 1, 62, 13, 37, 86, 53, 43, 49, 17, 82, 27, 17, 10, 89, 40, 82, 41, 2, 48, 98, 16, 43, 62, 33, 72, 35, 10, 24, 80, 29, 49, 5, 14, 38, 30, 48, 93, 86, 62, 23, 17, 39, 40, 96, 10, 75, 6, 38, 1, 5, 54, 91, 29, 36, 62, 73, 51, 92, 89, 88, 74, 91, 87, 34, 49, 56, 33, 67}));
    }
}
