package contest.medium;

/**
 * You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step, you can only move right or down in the matrix.
 *
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (rows - 1, cols - 1), find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
 *
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.
 *
 * Notice that the modulo is performed after getting the maximum product.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[-1,-2,-3],
 *                [-2,-3,-3],
 *                [-3,-3,-2]]
 * Output: -1
 * Explanation: It's not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
 * Example 2:
 *
 * Input: grid = [[1,-2,1],
 *                [1,-2,1],
 *                [3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
 * Example 3:
 *
 * Input: grid = [[1, 3],
 *                [0,-4]]
 * Output: 0
 * Explanation: Maximum non-negative product is in bold (1 * 0 * -4 = 0).
 * Example 4:
 *
 * Input: grid = [[ 1, 4,4,0],
 *                [-2, 0,0,1],
 *                [ 1,-1,1,1]]
 * Output: 2
 * Explanation: Maximum non-negative product is in bold (1 * -2 * 1 * -1 * 1 * 1 = 2).
 *
 *
 * Constraints:
 *
 * 1 <= rows, cols <= 15
 * -4 <= grid[i][j] <= 4
 */
public class MaximumNonNegativeProductInAMatrix {
    public int maxProductPath(int[][] grid) {
        int xLen = grid.length;
        int yLen = grid[0].length;
        long[][][] dp = new long[xLen][yLen][2];
        dp[0][0][1] = dp[0][0][0] = grid[0][0];
        for (int i = 1; i < xLen; i++) dp[i][0][0] = dp[i][0][1] = dp[i - 1][0][0] * grid[i][0];
        for (int i = 1; i < yLen; i++) dp[0][i][0] = dp[0][i][1] = dp[0][i - 1][0] * grid[0][i];
        for (int i = 1; i < xLen; i++) {
            for (int j = 1; j < yLen; j++) {
                dp[i][j][0] = Math.min(dp[i - 1][j][0] * grid[i][j], Math.min(dp[i - 1][j][1] * grid[i][j],
                        Math.min(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j])));
                dp[i][j][1] = Math.max(dp[i - 1][j][0] * grid[i][j], Math.max(dp[i - 1][j][1] * grid[i][j],
                        Math.max(dp[i][j - 1][0] * grid[i][j], dp[i][j - 1][1] * grid[i][j])));
            }
        }
        return dp[xLen - 1][yLen - 1][1] < 0 ? -1 : (int) (dp[xLen - 1][yLen - 1][1] % (1e9 + 7));
    }

    public static void main(String[] args) {
        MaximumNonNegativeProductInAMatrix m = new MaximumNonNegativeProductInAMatrix();
        System.out.println(m.maxProductPath(new int[][]{{2, 1, 3, 0, -3, 3, -4, 4, 0, -4}, {-4, -3, 2, 2, 3, -3, 1, -1, 1, -2},
                {-2, 0, -4, 2, 4, -3, -4, -1, 3, 4}, {-1, 0, 1, 0, -3, 3, -2, -3, 1, 0}, {0, -1, -2, 0, -3, -4, 0, 3, -2, -2},
                {-4, -2, 0, -1, 0, -3, 0, 4, 0, -3}, {-3, -4, 2, 1, 0, -4, 2, -4, -1, -3}, {3, -2, 0, -4, 1, 0, 1, -3, -1, -1},
                {3, -4, 0, 2, 0, -2, 2, -4, -2, 4}, {0, 4, 0, -3, -4, 3, 3, -1, -2, -2}}
        ));
    }
}
