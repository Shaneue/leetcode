package medium;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 *
 * 空间复杂度可以优化，不需要dp数组
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.equals("0")) return 0;
        if (s.length() == 1) return 1;
        int[] dp = new int[s.length()];
        if (s.charAt(0) == '0') {
            return 0;
        } else {
            dp[0] = 1;
        }
        boolean b = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0') > 26;
        if (s.charAt(1) == '0' && b) {
            return 0;
        } else if (s.charAt(1) == '0' || b) {
            dp[1] = 1;
        } else {
            dp[1] = 2;
        }
        for (int i = 2; i < s.length(); i++) {
            boolean b1 = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0') > 26;
            if (s.charAt(i) == '0' && (b1 || s.charAt(i - 1) == '0')) {
                return 0;
            } else if (b1) {
                dp[i] = dp[i - 1];
            } else if (s.charAt(i) == '0') {
                dp[i] = dp[i - 2];
            } else if (s.charAt(i - 1) == '0') {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 2] + dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("227"));
    }
}
