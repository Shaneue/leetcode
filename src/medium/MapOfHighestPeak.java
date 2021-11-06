package medium;

import java.util.LinkedList;

/**
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 *
 * If isWater[i][j] == 0, cell (i, j) is a land cell.
 * If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 *
 * The height of each cell must be non-negative.
 * If the cell is a water cell, its height must be 0.
 * Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 *
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: isWater = [[0,1],[0,0]]
 * Output: [[1,0],[2,1]]
 * Explanation: The image shows the assigned heights of each cell.
 * The blue cell is the water cell, and the green cells are the land cells.
 * Example 2:
 *
 *
 *
 * Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * Output: [[1,1,0],[0,1,1],[1,2,2]]
 * Explanation: A height of 2 is the maximum possible height of any assignment.
 * Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 *
 *
 * Constraints:
 *
 * m == isWater.length
 * n == isWater[i].length
 * 1 <= m, n <= 1000
 * isWater[i][j] is 0 or 1.
 * There is at least one water cell.
 */
public class MapOfHighestPeak {
    public int[][] highestPeak(int[][] isWater) {
        int[][] ret = new int[isWater.length][isWater[0].length];
        boolean[][] used = new boolean[isWater.length][isWater[0].length];
        LinkedList<int[]> q = new LinkedList<>();
        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[0].length; j++) {
                if (isWater[i][j] == 1) {
                    used[i][j] = true;
                    ret[i][j] = 0;
                    q.add(new int[]{i, j});
                }
            }
        }
        int[] directions = new int[]{0, -1, 0, 1, 0};
        int c = 1;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] p = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = p[0] + directions[j];
                    int y = p[1] + directions[j + 1];
                    if (x >= 0 && x < isWater.length && y >= 0 && y < isWater[0].length && !used[x][y]) {
                        ret[x][y] = c;
                        q.add(new int[]{x, y});
                        used[x][y] = true;
                    }
                }
            }
            c++;
        }
        return ret;
    }
}
