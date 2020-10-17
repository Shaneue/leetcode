package medium;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * 属于dp
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int t = max;
            max = Math.max(min * nums[i], Math.max(nums[i], max * nums[i]));
            min = Math.min(t * nums[i], Math.min(nums[i], min * nums[i]));
            ret = Math.max(ret, max);
        }
        return ret;
    }

    public static void main(String[] args) {
        MaximumProductSubarray m = new MaximumProductSubarray();
        System.out.println(m.maxProduct(new int[]{-2, 0, -1}));
        System.out.println(m.maxProduct(new int[]{2, -5, -2, -4, 3}));
        System.out.println(m.maxProduct(new int[]{2, 3, -2, 4}));
    }
}
