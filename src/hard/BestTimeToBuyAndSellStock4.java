package hard;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 *
 * Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= k <= 10^9
 * 0 <= prices.length <= 10^4
 * 0 <= prices[i] <= 1000
 *
 * dp[i,k]=max(dp[i-1,k),prices[i]+dp[j-1][k-1]-prices[j] for j in [0,i-1]
 */
public class BestTimeToBuyAndSellStock4 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k < 1) return 0;
        if (k > prices.length / 2) {
            int ret = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) ret += prices[i] - prices[i - 1];
            }
            return ret;
        }
        int[] dp1 = new int[prices.length];
        int[] dp2 = new int[prices.length];
        for (int j = 1; j <= k; j++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(prices[i] - dp1[i - 1], min);
                dp2[i] = Math.max(dp2[i - 1], prices[i] - min);
            }
            int[] t = dp1;
            dp1 = dp2;
            dp2 = t;
        }
        return dp1[prices.length - 1];
    }
}
