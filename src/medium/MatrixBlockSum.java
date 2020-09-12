package medium;

/**
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * Example 2:
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int lenX = mat.length;
        int lenY = mat[0].length;
        int[][] dp = new int[lenX + 1][lenY + 1];
        for (int x = 1; x <= lenX; x++) {
            for (int y = 1; y <= lenY; y++) {
                dp[x][y] = dp[x - 1][y] + dp[x][y - 1] + mat[x - 1][y - 1] - dp[x - 1][y - 1];
            }
        }
        int[][] ret = new int[lenX][lenY];
        for (int x = 0; x < lenX; x++) {
            for (int y = 0; y < lenY; y++) {
                int x1 = Math.max(0, x - K);
                int x2 = Math.min(lenX - 1, x + K);
                int y1 = Math.max(0, y - K);
                int y2 = Math.min(lenY - 1, y + K);
                ret[x][y] = dp[x2 + 1][y2 + 1] + dp[x1][y1] - dp[x1][y2 + 1] - dp[x2 + 1][y1];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        MatrixBlockSum m = new MatrixBlockSum();
        int[][] r = m.matrixBlockSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}}, 1);
        System.out.println();
    }
}
