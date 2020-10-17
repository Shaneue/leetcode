package medium;

/**
 * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 * 用累加代替乘
 * pre数组记录两个odd间元素个数，遍历一次即可
 */
public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int[] gap = new int[nums.length];
        int odd = 0;
        int ret = 0;
        for (int i : nums) {
            gap[odd]++;
            if (i % 2 == 1) odd++;
            if (odd >= k) {
                ret += gap[odd - k];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        CountNumberOfNiceSubarrays c = new CountNumberOfNiceSubarrays();
        System.out.println(c.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
        System.out.println(c.numberOfSubarrays(new int[]{2, 4, 6}, 2));
        System.out.println(c.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        System.out.println(c.numberOfSubarrays(new int[]{1, 1, 1, 1, 1}, 1));
    }
}
