package medium;

import java.util.LinkedList;

/**
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 *
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * If no land or water exists in the grid, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation:
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 * Example 2:
 *
 *
 *
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation:
 * The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 *
 * Note:
 *
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 *
 * 不适合dfs
 */
public class AsFarFromLandAsPossible {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int ret = -1;
        LinkedList<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) q.add(new int[]{i, j});
            }
        }
        int[][] direction = new int[][]{{0, 0, 1, -1}, {1, -1, 0, 0}};
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] p = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = p[0] + direction[0][j], y = p[1] + direction[1][j];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        q.add(new int[]{x, y});
                    }
                }
            }
            ret++;
        }
        return ret <= 0 ? -1 : ret;
    }


    public static void main(String[] args) {
        AsFarFromLandAsPossible a = new AsFarFromLandAsPossible();
        System.out.println(a.maxDistance(new int[][]{
                {0, 0}, {0, 1}}));
        System.out.println(a.maxDistance(new int[][]{
                {0, 0}, {0, 0}}));
        System.out.println(a.maxDistance(new int[][]{
                {0}}));
        System.out.println(a.maxDistance(new int[][]{
                {1}}));
        System.out.println(a.maxDistance(new int[][]{
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 0}}));
    }
}
