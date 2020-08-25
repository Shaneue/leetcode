package hard;

/**
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).
 *
 * Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * Example 2:
 *
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * Example 3:
 *
 * Input: steps = 4, arrLen = 2
 * Output: 8
 *
 *
 * Constraints:
 *
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 *
 * dp[i][j]=dp[i-1][j-1]+dp[i-1][j]+dp[i-1][j+1]
 * arrLen可以只有steps的一半
 */
public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    public int numWays(int steps, int arrLen) {
        arrLen = Math.min(arrLen, steps / 2 + 1);
        int[][] dp = new int[2][arrLen];
        int c = 0;
        dp[1][0] = 1;
        int mod = (int) (1e9 + 7);
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                dp[c][j] = dp[1 - c][j];
                if (j > 0) dp[c][j] = (dp[1 - c][j - 1] + dp[c][j]) % mod;
                if (j < arrLen - 1) dp[c][j] = (dp[c][j] + dp[1 - c][j + 1]) % mod;
            }
            c = 1 - c;
        }
        return dp[1 - c][0];
    }

    public static void main(String[] args) {
        NumberOfWaysToStayInTheSamePlaceAfterSomeSteps n = new NumberOfWaysToStayInTheSamePlaceAfterSomeSteps();
        System.out.println(n.numWays(3, 2));
        System.out.println(n.numWays(2, 4));
        System.out.println(n.numWays(0, 1));
    }
}
