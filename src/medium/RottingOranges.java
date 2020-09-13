package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 *
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: [[0,2]]
 * Output: 0
 * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Note:
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] is only 0, 1, or 2.
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int start = 0;
        int target = 0;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    start++;
                    target++;
                    q.add(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    target++;
                }
            }
        }
        if (start == target) return 0;
        int ret = -1;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] c = q.remove();
                if (c[0] - 1 >= 0 && grid[c[0] - 1][c[1]] == 1) {
                    grid[c[0] - 1][c[1]] = 2;
                    q.add(new int[]{c[0] - 1, c[1]});
                    start++;
                }
                if (c[1] - 1 >= 0 && grid[c[0]][c[1] - 1] == 1) {
                    grid[c[0]][c[1] - 1] = 2;
                    q.add(new int[]{c[0], c[1] - 1});
                    start++;
                }
                if (c[0] + 1 < grid.length && grid[c[0] + 1][c[1]] == 1) {
                    grid[c[0] + 1][c[1]] = 2;
                    q.add(new int[]{c[0] + 1, c[1]});
                    start++;
                }
                if (c[1] + 1 < grid[0].length && grid[c[0]][c[1] + 1] == 1) {
                    grid[c[0]][c[1] + 1] = 2;
                    q.add(new int[]{c[0], c[1] + 1});
                    start++;
                }
            }
            ret++;
        }
        return start == target ? ret : -1;
    }

    public static void main(String[] args) {
        RottingOranges r = new RottingOranges();
        System.out.println(r.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
    }
}
