package contest.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * There is a strange printer with the following two special requirements:
 *
 * On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover up the existing colors in the rectangle.
 * Once the printer has used a color for the above operation, the same color cannot be used again.
 * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.
 *
 * Return true if it is possible to print the matrix targetGrid, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
 * Output: true
 * Example 2:
 *
 *
 *
 * Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
 * Output: true
 * Example 3:
 *
 * Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
 * Output: false
 * Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.
 * Example 4:
 *
 * Input: targetGrid = [[1,1,1],[3,1,3]]
 * Output: false
 *
 *
 * Constraints:
 *
 * m == targetGrid.length
 * n == targetGrid[i].length
 * 1 <= m, n <= 60
 * 1 <= targetGrid[row][col] <= 60
 */
public class StrangePrinter2 {
    public boolean isPrintable(int[][] targetGrid) {
        int[][] colors = new int[61][4];
        for (int i = 1; i < 61; i++) colors[i][2] = colors[i][3] = 60;
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int c = targetGrid[i][j];
                colors[c][0] = Math.max(colors[c][0], i);
                colors[c][1] = Math.max(colors[c][1], j);
                colors[c][2] = Math.min(colors[c][2], i);
                colors[c][3] = Math.min(colors[c][3], j);
                set.add(c);
            }
        }
        while (!set.isEmpty()) {
            boolean flag = false;
            for (int c : set) {
                if (canPrint(targetGrid, colors, c)) {
                    flag = true;
                    set.remove(c);
                    break;
                }
            }
            if (!flag) return false;
        }
        return true;
    }

    boolean canPrint(int[][] grid, int[][] colors, int c) {
        for (int i = colors[c][2]; i <= colors[c][0]; i++) {
            for (int j = colors[c][3]; j <= colors[c][1]; j++) {
                if (grid[i][j] != 0 && grid[i][j] != c) return false;
            }
        }
        for (int i = colors[c][2]; i <= colors[c][0]; i++) {
            for (int j = colors[c][3]; j <= colors[c][1]; j++) {
                grid[i][j] = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StrangePrinter2 s = new StrangePrinter2();
        System.out.println(s.isPrintable(new int[][]{
                {1, 1, 1},
                {3, 1, 3}}));
        System.out.println(s.isPrintable(new int[][]{
                {1, 1, 1, 1},
                {1, 1, 3, 3},
                {1, 1, 3, 4},
                {5, 5, 1, 4}}));
    }
}
