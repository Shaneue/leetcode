package contest.easy;

import java.util.Arrays;

/**
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,2,2,3]
 * Output: [3,1,1,2,2,2]
 * Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
 * Example 2:
 *
 * Input: nums = [2,3,1,3,2]
 * Output: [1,3,3,2,2]
 * Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
 * Example 3:
 *
 * Input: nums = [-1,1,-6,4,5,-6,1,4,1]
 * Output: [5,-1,4,4,-6,-6,1,1,1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class SortArrayByIncreasingFrequency {
    public int[] frequencySort(int[] nums) {
        int[][] bucket = new int[201][2];
        for (int i = 0; i < 201; i++) bucket[i][1] = i;
        for (int num : nums) bucket[num + 100][0]++;
        Arrays.sort(bucket, (a, b) -> {
            if (a[0] == b[0]) return Integer.compare(b[1], a[1]);
            return Integer.compare(a[0], b[0]);
        });
        int[] ret = new int[nums.length];
        int c = 0;
        for (int i = 0; i < 201; i++) {
            for (int j = bucket[i][0]; j > 0; j--) ret[c++] = bucket[i][1] - 100;
        }
        return ret;
    }

    public static void main(String[] args) {
        SortArrayByIncreasingFrequency s = new SortArrayByIncreasingFrequency();
        System.out.println(Arrays.toString(s.frequencySort(new int[]{3, 1, 1, 2, 2, 2})));
    }
}
