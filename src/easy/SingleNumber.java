package easy;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,1]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [4,1,2,1,2]
 * Output: 4
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int i : nums) {
            ret ^= i;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[]{1, 1, 2, 3, 2}));
    }
}
