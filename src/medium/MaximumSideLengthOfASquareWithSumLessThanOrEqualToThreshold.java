package medium;

/**
 * Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * Output: 2
 * Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
 * Example 2:
 *
 * Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * Output: 0
 * Example 3:
 *
 * Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * Output: 3
 * Example 4:
 *
 * Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 *
 * 不需要binary search，在求sum的时候可以顺便求square的和
 */
public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
            }
        }
        int l = 0, r = Math.min(m, n) + 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (existSquare(sum, mid, m, n, threshold)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;
    }

    private boolean existSquare(int[][] sum, int length, int m, int n, int threshold) {
        for (int i = 0; i + length - 1 < m; i++) {
            for (int j = 0; j + length - 1 < n; j++) {
                if (sum[i + length][j + length] - sum[i][j + length] - sum[i + length][j] + sum[i][j] <= threshold) {
                    return true;
                }
            }
        }
        return false;
    }
}
