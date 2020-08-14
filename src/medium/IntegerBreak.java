package medium;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;

        int ret = n / 3;
        n %= 3;
        if (n == 1) {
            ret = ret - 1;
            n = n + 3;
        }
        ret = (int) Math.pow(3, ret);
        if (n != 0) {
            ret *= n;
        }
        return ret;
    }
}
