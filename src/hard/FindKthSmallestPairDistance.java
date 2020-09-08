package hard;

import java.util.Arrays;

/**
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.
 *
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 *
 * 也可以使用桶排序
 */
public class FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0;
        int r = nums[n - 1] - nums[0];
        while (l <= r) {
            int c = 0;
            int m = (l + r) / 2;
            int j = 0;
            for (int i = 0; i < n; i++) {
                while (j < n && nums[j] - nums[i] <= m) j++;
                c += j - i - 1;
            }
            if (c >= k) r = m - 1;
            else l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        FindKthSmallestPairDistance f = new FindKthSmallestPairDistance();
        System.out.println(f.smallestDistancePair(new int[]{1, 3, 1}, 1));
    }
}
