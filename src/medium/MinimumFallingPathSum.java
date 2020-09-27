package medium;

/**
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 *
 * A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2,3],
 *         [4,5,6],
 *         [7,8,9]]
 * Output: 12
 * Explanation:
 * The possible falling paths are:
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 *
 *
 *
 * Constraints:
 *
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 *
 * dp直接使用A即可
 */
public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] A) {
        int l = A.length;
        for (int i = 1; i < l; i++) {
            for (int j = 0; j < l; j++) {
                int a = j == 0 ? Integer.MAX_VALUE : A[i - 1][j - 1];
                int b = j == l - 1 ? Integer.MAX_VALUE : A[i - 1][j + 1];
                int c = A[i - 1][j];
                A[i][j] = A[i][j] + Math.min(Math.min(a, b), c);
            }
        }
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            ret = Math.min(ret, A[l - 1][i]);
        }
        return ret;
    }

    public static void main(String[] args) {
        MinimumFallingPathSum m = new MinimumFallingPathSum();
        System.out.println(m.minFallingPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }
}
