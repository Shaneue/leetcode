package medium;

/**
 * You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
 *
 * 2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
 * 1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
 * 0, if none of the previous conditions holds.
 * Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 2.
 * Example 2:
 *
 * Input: nums = [2,4,6,4]
 * Output: 1
 * Explanation: For each index i in the range 1 <= i <= 2:
 * - The beauty of nums[1] equals 1.
 * - The beauty of nums[2] equals 0.
 * Example 3:
 *
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: For each index i in the range 1 <= i <= 1:
 * - The beauty of nums[1] equals 0.
 *
 *
 * Constraints:
 *
 * 3 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class SumOfBeautyInTheArray {
    public int sumOfBeauties(int[] nums) {
        int[] c = new int[nums.length];
        int[] maxLeftToRight = new int[nums.length];
        int[] minRightToLeft = new int[nums.length];
        for (int i = 1, max = nums[0], min = nums[nums.length - 1]; i < nums.length - 1; i++) {
            maxLeftToRight[i] = max;
            minRightToLeft[nums.length - 1 - i] = min;
            max = Math.max(nums[i], max);
            min = Math.min(nums[nums.length - 1 - i], min);
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > maxLeftToRight[i] && nums[i] < minRightToLeft[i]) c[i] = 2;
            else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) c[i] = 1;
        }
        int ret = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            ret += c[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        SumOfBeautyInTheArray s = new SumOfBeautyInTheArray();
        System.out.println(s.sumOfBeauties(new int[]{2, 4, 6, 4}));
        System.out.println(s.sumOfBeauties(new int[]{1, 2, 3}));
    }
}
