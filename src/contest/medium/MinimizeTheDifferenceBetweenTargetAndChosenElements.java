package contest.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an m x n integer matrix mat and an integer target.
 *
 * Choose one integer from each row in the matrix such that the absolute difference between target and the sum of the chosen elements is minimized.
 *
 * Return the minimum absolute difference.
 *
 * The absolute difference between two numbers a and b is the absolute value of a - b.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * Output: 0
 * Explanation: One possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 5 from the second row.
 * - Choose 7 from the third row.
 * The sum of the chosen elements is 13, which equals the target, so the absolute difference is 0.
 * Example 2:
 *
 *
 * Input: mat = [[1],[2],[3]], target = 100
 * Output: 94
 * Explanation: The best possible choice is to:
 * - Choose 1 from the first row.
 * - Choose 2 from the second row.
 * - Choose 3 from the third row.
 * The sum of the chosen elements is 6, and the absolute difference is 94.
 * Example 3:
 *
 *
 * Input: mat = [[1,2,9,8,7]], target = 6
 * Output: 1
 * Explanation: The best choice is to choose 7 from the first row.
 * The absolute difference is 1.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 *
 * 还可以优化很多，这个耗时1368ms
 */
public class MinimizeTheDifferenceBetweenTargetAndChosenElements {
    public int minimizeTheDifference(int[][] mat, int target) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 0; i < mat.length; i++) {
            Set<Integer> setCurrent = new HashSet<>();
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < mat[0].length; j++) {
                for (int k : set) {
                    int v = k + mat[i][j];
                    if (v > max && max > 800) continue;
                    else max = v;
                    setCurrent.add(v);
                }
            }
            set = setCurrent;
        }
        int ret = Integer.MAX_VALUE;
        for (int i : set) {
            ret = Math.min(ret, Math.abs(i - target));
        }
        return ret;
    }
}
