package medium;

/**
 * Given a binary string s (a string consisting only of '0's and '1's), we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).
 *
 * Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
 * "1|010|1"
 * "1|01|01"
 * "10|10|1"
 * "10|1|01"
 * Example 2:
 *
 * Input: s = "1001"
 * Output: 0
 * Example 3:
 *
 * Input: s = "0000"
 * Output: 3
 * Explanation: There are three ways to split s in 3 parts.
 * "0|0|00"
 * "0|00|0"
 * "00|0|0"
 * Example 4:
 *
 * Input: s = "100100010100110"
 * Output: 12
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 10^5
 * s[i] is '0' or '1'.
 */
public class NumberOfWaysToSplitAString {
    public int numWays(String s) {
        int c = 0;
        int mod = (int) (1e9 + 7);
        int n = s.length();
        char[] chars = s.toCharArray();
        for (char ch : chars) if (ch == '1') c++;
        if (c % 3 != 0) return 0;
        if (c == 0) return (int) ((long) (n - 1) * (n - 2) / 2 % mod);
        int a = 0, b = 0;
        int count = 0, target = c / 3;
        for (char ch : chars) {
            if (ch == '1') count++;
            else {
                if (count == target) a++;
                else if (count == 2 * target) b++;
            }
        }
        return (int) ((long) (a + 1) * (b + 1) % mod);
    }
}
