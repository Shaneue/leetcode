package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Example:
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * <p>
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * 一定要用l<=r,因为二分查到值的时候还是要往右移动一位
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        List<Integer> increasingSubsequence = new ArrayList<>();
        increasingSubsequence.add(nums[0]);
        for (int i : nums) {
            if (increasingSubsequence.get(0) > i) increasingSubsequence.set(0, i);
            else if (increasingSubsequence.get(increasingSubsequence.size() - 1) < i) {
                increasingSubsequence.add(i);
            } else {
                int l = 0, r = increasingSubsequence.size();
                while (l < r) {
                    int mid = l + (r - l) / 2;
                    if (increasingSubsequence.get(mid) < i) l = mid + 1;
                    else r = mid;
                }
                increasingSubsequence.set(r, i);
            }
        }
        return increasingSubsequence.size();
    }

    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int ret = -1;
        for (int v : nums) {
            int c = binarySearch(dp, ret, v);
            dp[c] = v;
            if (c > ret) ret = c;
        }
        return ret + 1;
    }

    int binarySearch(int[] dp, int n, int v) {
        int l = 0, r = n;
        while (l <= r) {
            int m = (l + r) / 2;
            if (dp[m] >= v) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        int[] array = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(new LongestIncreasingSubsequence().lengthOfLIS(array));
    }
}
