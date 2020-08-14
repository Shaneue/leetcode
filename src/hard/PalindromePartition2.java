package hard;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * Example:
 * <p>
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * <p>
 * 需要一维dp即可
 * 用数组记录重复计算的值
 */
public class PalindromePartition2 {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n + 1][n + 1];
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = n;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && ((i - j < 2) || isPal[j + 1][i - 1])) {
                    isPal[j][i] = true;
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartition2().minCut("abab"));
    }
}
