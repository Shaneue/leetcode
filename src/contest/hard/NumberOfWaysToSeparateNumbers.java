package contest.hard;

/**
 * You wrote down many positive integers in a string called num. However, you realized that you forgot to add commas to seperate the different numbers. You remember that the list of integers was non-decreasing and that no integer had leading zeros.
 *
 * Return the number of possible lists of integers that you could have written down to get the string num. Since the answer may be large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "327"
 * Output: 2
 * Explanation: You could have written down the numbers:
 * 3, 27
 * 327
 * Example 2:
 *
 * Input: num = "094"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be positive.
 * Example 3:
 *
 * Input: num = "0"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be positive.
 * Example 4:
 *
 * Input: num = "9999999999999"
 * Output: 101
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 3500
 * num consists of digits '0' through '9'.
 */
public class NumberOfWaysToSeparateNumbers {
    public int numberOfCombinations(String num) {
        int n = num.length(), mod = (int) (1e9 + 7);
        int[] dp1 = new int[n + 1], dp2 = new int[n + 1], pf = new int[n + 1];
        for (int l = 1; l <= n; ++l) {
            dp2[0] = 1;
            for (int i = n; i - l > 0; --i)
                pf[i - 1] = num.charAt(i - 1 - l) == num.charAt(i - 1) ? pf[i] + 1 : 0;
            for (int i = 0; i < n; ++i) {
                dp1[i + 1] = dp2[i + 1];
                if (l <= i + 1 && num.charAt(i + 1 - l) != '0') {
                    if (i + 1 - 2 * l >= 0 && (pf[i + 1 - l] >= l || num.charAt(i + 1 - l + pf[i + 1 - l]) > num.charAt(i + 1 - 2 * l + pf[i + 1 - l])))
                        dp1[i + 1] = (dp1[i + 1] + dp1[i + 1 - l]) % mod;
                    else
                        dp1[i + 1] = (dp1[i + 1] + dp2[i + 1 - l]) % mod;
                }
            }
            int[] t = dp1;
            dp1 = dp2;
            dp2 = t;
        }
        return dp2[n];
    }
}
