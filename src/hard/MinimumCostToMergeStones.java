package hard;

/**
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 *
 * A move consists of merging exactly K consecutive piles into one pile, and the cost of this move is equal to the total number of stones in these K piles.
 *
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 * Example 2:
 *
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore.  So the task is impossible.
 * Example 3:
 *
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 *
 * 注意要加一遍区间和，核心在第二个+=
 * dp[i][j]=min(dp[i][j], dp[i][t] + dp[t + 1][j])
 * dp[i][j]+=preSum[j]-preSum[i-1] for (j - i) % (K - 1) == 0
 *
 * preSum第一个元素值为0方便计算
 * 注意跳步可以用K-1
 */
public class MinimumCostToMergeStones {
    public int mergeStones(int[] stones, int K) {
        if ((stones.length - 1) % (K - 1) != 0) return -1;
        int l = stones.length;
        int[] preSum = new int[l + 1];
        for (int i = 1; i <= l; i++) preSum[i] = preSum[i - 1] + stones[i - 1];
        int[][] dp = new int[l][l];
        for (int len = K - 1; len < l; ++len) {
            for (int i = 0; i + len < l; ++i) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int t = i; t < j; t += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][t] + dp[t + 1][j]);
                }
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += preSum[j + 1] - preSum[i];
                }
            }
        }
        return dp[0][l - 1];
    }

    public static void main(String[] args) {
        MinimumCostToMergeStones m = new MinimumCostToMergeStones();
        System.out.println(m.mergeStones(new int[]{3, 5, 1, 2, 6}, 3));
    }
}
