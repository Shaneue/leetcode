package medium;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 * <p>
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * <p>
 * 将这两个数放到两个小组里
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int[] ret = new int[2];
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        xor = -xor & xor;
        for (int n : nums) {
            if ((xor & n) == 0) {
                ret[0] ^= n;
            } else {
                ret[1] ^= n;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] a = new SingleNumber3().singleNumber(new int[]{1, 2, 1, 3, 2, 5});
        System.out.println(a[0]);
        System.out.println(a[1]);
    }
}
