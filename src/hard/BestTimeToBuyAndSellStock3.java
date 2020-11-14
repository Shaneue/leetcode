package hard;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * Example 4:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 *
 * dp[i,k]=max(dp[i-1,k),prices[i]+dp[j-1][k-1]-prices[j] for j in [0,i-1]
 */
public class BestTimeToBuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][3];
        for (int k = 1; k <= 2; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(prices[i] - dp[i - 1][k - 1], min);
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[prices.length - 1][2];
    }
}
