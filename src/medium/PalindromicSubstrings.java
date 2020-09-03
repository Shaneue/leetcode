package medium;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 *
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 *
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 *
 * Example 2:
 *
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 *
 * Note:
 *
 * The input string length won't exceed 1000.
 *
 * 遍历一遍数组，从中间往两边累加，代码更简洁
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s.length() == 0) return 0;
        int l = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[l][l];
        int ret = l;
        for (int i = 0; i < l - 1; i++) {
            dp[i][i] = true;
            if (chars[i] == chars[i + 1]) {
                dp[i][i + 1] = true;
                ret++;
            } else {
                dp[i][i + 1] = false;
            }
        }
        dp[l - 1][l - 1] = true;
        for (int i = 2; i <= l; i++) {
            for (int j = 0; j + i < l; j++) {
                if (dp[j + 1][j + i - 1] && chars[j] == chars[j + i]) {
                    ret++;
                    dp[j][j + i] = true;
                } else {
                    dp[j][j + i] = false;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        PalindromicSubstrings p = new PalindromicSubstrings();
        System.out.println(p.countSubstrings("aaa"));
    }
}
