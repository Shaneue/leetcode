package hard;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 * <p>
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 * Example 2:
 * <p>
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 * Example 3:
 * <p>
 * Input: s = "leetcode", k = 8
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 * <p>
 * dp[i][1]=cost(0,i)
 * dp[i][k]=min(dp[j][k-1]+cost[j+1,i)) 0<=j<i
 * <p>
 * 用二维数组记录重复计算的值
 */
public class PalindromePartition3 {
    public int palindromePartition(String s, int k) {
        int l = s.length();
        int[][] dp = new int[l][k];
        int[][] cost = new int[l + 1][l + 1];
        for (int i = 0; i < l; i++) {
            for (int j = i; j < l; j++) {
                cost[i][j] = cost(s, i, j);
            }
        }
        for (int i = 0; i < l; i++) {
            dp[i][0] = cost[0][i];
        }
        for (int i = 1; i < k; i++) {
            for (int j = i; j < l; j++) {
                dp[j][i] = l;
                for (int m = 0; m < j; m++) {
                    dp[j][i] = Math.min(dp[j][i], dp[m][i - 1] + cost[m + 1][j]);
                }
            }
        }
        return dp[l - 1][k - 1];
    }

    int cost(String s, int b, int e) {
        int c = 0;
        for (int i = b, j = e; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) c++;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromePartition3().palindromePartition("aabbc", 3));
        System.out.println(new PalindromePartition3().palindromePartition("abcdef", 2));
    }
}
