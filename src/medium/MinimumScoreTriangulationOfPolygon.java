package medium;

/**
 * Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
 *
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
 *
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 * Example 2:
 *
 *
 *
 * Input: [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
 * Example 3:
 *
 * Input: [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 *
 *
 * Note:
 *
 * 3 <= A.length <= 50
 * 1 <= A[i] <= 100
 *
 * dp[i][i + k] = Math.min(dp[i][j] + dp[j][i + k] + A[i] * A[i + k] * A[j], dp[i][i + k]);
 */
public class MinimumScoreTriangulationOfPolygon {
    public int minScoreTriangulation(int[] A) {
        int[][] dp = new int[A.length][A.length];
        for (int k = 2; k < A.length; k++) {
            for (int i = 0; i + k < A.length; i++) {
                dp[i][i + k] = Integer.MAX_VALUE;
                for (int j = i + 1; j < i + k; j++) {
                    dp[i][i + k] = Math.min(dp[i][j] + dp[j][i + k] + A[i] * A[i + k] * A[j], dp[i][i + k]);
                }
            }
        }
        return dp[0][A.length - 1];
    }

    public static void main(String[] args) {
        MinimumScoreTriangulationOfPolygon m = new MinimumScoreTriangulationOfPolygon();
        System.out.println(m.minScoreTriangulation(new int[]{1, 2, 4}));
        System.out.println(m.minScoreTriangulation(new int[]{3, 7, 4, 5}));
        System.out.println(m.minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5}));
    }
}
