package contest.medium;

/**
 * Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.
 *
 * An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.
 *
 * The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1]
 * Output: 2
 * Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
 * - [3]
 * - [3,1]
 * Example 2:
 *
 * Input: nums = [2,2,2]
 * Output: 7
 * Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
 * Example 3:
 *
 * Input: nums = [3,2,1,5]
 * Output: 6
 * Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 10^5
 */
public class CountNumberOfMaximumBitwiseORSubsets {
    public int countMaxOrSubsets(int[] nums) {
        int size = (1 << nums.length) - 1;
        int ret = 0;
        int max = 0;
        for (int i = 1; i <= size; i++) {
            int c = compute(i, nums);
            if (c > max) {
                ret = 1;
                max = c;
            } else if (c == max) {
                ret++;
            }
        }
        return ret;
    }

    private int compute(int mask, int[] nums) {
        int sum = 0;
        int i = 0;
        while (mask != 0) {
            if ((mask & 1) == 1) {
                sum |= nums[i];
            }
            mask >>= 1;
            i++;
        }
        return sum;
    }

    public static void main(String[] args) {
        CountNumberOfMaximumBitwiseORSubsets c = new CountNumberOfMaximumBitwiseORSubsets();
        System.out.println(c.countMaxOrSubsets(new int[]{3, 1}));
    }
}
