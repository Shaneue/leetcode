package hard;

/**
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 *
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 *
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.
 *
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 *
 * Your goal is to know with certainty what the value of F is.
 *
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 *
 *
 *
 * Example 1:
 *
 * Input: K = 1, N = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
 * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
 * If it didn't break, then we know with certainty F = 2.
 * Hence, we needed 2 moves in the worst case to know what F is with certainty.
 * Example 2:
 *
 * Input: K = 2, N = 6
 * Output: 3
 * Example 3:
 *
 * Input: K = 3, N = 14
 * Output: 4
 *
 *
 * Note:
 *
 * 1 <= K <= 100
 * 1 <= N <= 10000
 *
 * 不一定最中间最合算，所以目的是找到最均衡的那一楼
 * min(max())
 * 为什么选择broken合算？
 *
 * 最优解dp
 * dp[k][m]=dp[k][m-1]+dp[k-1][m-1] until dp[k][m]>=n
 */
public class SuperEggDrop {
    static int[][] cache = new int[101][10001];

    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        return m;
    }

    int dfs(int k, int n) {
        if (k == 1 || n <= 1) return n;
        if (cache[k][n] > 0) return cache[k][n];
        int l = 0, r = n;
        int ret = Integer.MAX_VALUE;
        while (l < r) {
            int m = l + (r - l) / 2;
            int brokenCost = dfs(k - 1, m - 1);
            int notBrokenCost = dfs(k, n - m);
            ret = Math.min(ret, Math.max(brokenCost, notBrokenCost + 1));
            if (brokenCost >= notBrokenCost) r = m;
            else l = m + 1;
        }
        return cache[k][n] = ret;
    }

    public static void main(String[] args) {
        SuperEggDrop s = new SuperEggDrop();
        System.out.println(s.superEggDrop(3, 25));
        System.out.println(s.superEggDrop(2, 3));
        System.out.println(s.superEggDrop(2, 2));
        System.out.println(s.superEggDrop(2, 5));
        System.out.println(s.superEggDrop(2, 9));
        System.out.println(s.superEggDrop(1, 2));
        System.out.println(s.superEggDrop(2, 6));
        System.out.println(s.superEggDrop(3, 14));
    }
}
