package medium;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * solution:
 * Find the largest index k such that nums[k] < nums[k + 1]. If no such index exists, just reverse nums and done.
 * Find the largest index l > k such that nums[k] < nums[l].
 * Swap nums[k] and nums[l].
 * Reverse the sub-array nums[k + 1:].
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int k, l, n = nums.length;
        for (k = n - 2; k >= 0; k--) if (nums[k] < nums[k + 1]) break;
        if (k < 0) {
            for (int i = 0; i < n / 2; i++) {
                int t = nums[i];
                nums[i] = nums[n - i - 1];
                nums[n - 1 - i] = t;
            }
        } else {
            for (l = n - 1; l > k; l--) if (nums[l] > nums[k]) break;
            int t = nums[l];
            nums[l] = nums[k];
            nums[k] = t;
            for (int i = 0; i < (n - k - 1) / 2; i++) {
                t = nums[i + k + 1];
                nums[i + k + 1] = nums[n - i - 1];
                nums[n - 1 - i] = t;
            }
        }
    }

    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();
        int[] nums = new int[]{1, 2, 3};
        n.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 3, 1};
        n.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
