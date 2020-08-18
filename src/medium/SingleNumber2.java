package medium;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 * <p>
 * 对每一个bit累加对3去模
 */
public class SingleNumber2 {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int n : nums) {
            b = (b ^ n) & ~a;
            a = (a ^ n) & ~b;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber2().singleNumber(new int[]{1, 1, 1, 2}));
    }
}
