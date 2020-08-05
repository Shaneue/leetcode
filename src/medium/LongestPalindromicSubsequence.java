package medium;


/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input:
 * <p>
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * <p>
 * <p>
 * Example 2:
 * Input:
 * <p>
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 * <p>
 * dp只要一维数据即可
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int l = s.length();
        int[] dp = new int[l];
        int ret = 0;
        for (int i = l - 1; i >= 0; i--) {
            dp[i] = 1;
            int preMax = 0;
            for (int j = i + 1; j < l; j++) {
                int tmp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = preMax + 2;
                }
                preMax = Math.max(tmp, preMax);
            }
        }

        for (int i : dp) {
            ret = Math.max(ret, i);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("bbbab"));
    }
}
