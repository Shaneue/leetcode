package contest.medium;

/**
 * You are given an n x n integer matrix. You can do the following operation any number of times:
 *
 * Choose any two adjacent elements of matrix and multiply each of them by -1.
 * Two elements are considered adjacent if and only if they share a border.
 *
 * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 * Example 2:
 *
 *
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can follow the following step to reach sum equals 16:
 * - Multiply the 2 last elements in the second row by -1.
 *
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 */
public class MaximumMatrixSum {
    public long maxMatrixSum(int[][] matrix) {
        long ret = 0;
        int min = Integer.MAX_VALUE;
        int oddCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int abs = Math.abs(matrix[i][j]);
                min = Math.min(abs, min);
                ret += abs;
                if (matrix[i][j] < 0) {
                    oddCount++;
                }
            }
        }
        return oddCount % 2 == 1 ? ret - 2 * min : ret;
    }
}
