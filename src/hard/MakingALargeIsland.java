package hard;

import java.util.HashSet;
import java.util.Set;

/**
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 *
 * Notes:
 *
 * 1 <= grid.length = grid[0].length <= 50.
 * 0 <= grid[i][j] <= 1.
 *
 * 注意求连通分量面积的方法
 */
public class MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int[] areas = new int[2555];
        int c = 1;
        int ret = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 1) {
                    areas[++c] = area(grid, x, y, c);
                }
            }
        }
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] == 0) {
                    Set<Integer> filter = new HashSet<>();
                    filter.add(getMark(grid, x - 1, y));
                    filter.add(getMark(grid, x + 1, y));
                    filter.add(getMark(grid, x, y - 1));
                    filter.add(getMark(grid, x, y + 1));
                    int total = 0;
                    for (int mark : filter) {
                        total += areas[mark];
                    }
                    ret = Math.max(ret, total + 1);
                }
            }
        }
        return ret == 0 ? grid.length * grid[0].length : ret;
    }

    int getMark(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return 0;
        else return grid[x][y];
    }

    int area(int[][] grid, int x, int y, int mark) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) return 0;
        grid[x][y] = mark;
        return 1 + area(grid, x - 1, y, mark) + area(grid, x + 1, y, mark) + area(grid, x, y + 1, mark) + area(grid, x, y - 1, mark);
    }

    public static void main(String[] args) {
        MakingALargeIsland m = new MakingALargeIsland();
        System.out.println(m.largestIsland(new int[][]{{0, 1}, {1, 1}}));
        System.out.println(m.largestIsland(new int[][]{{1}}));
        System.out.println(m.largestIsland(new int[][]{{0}}));
        System.out.println(m.largestIsland(new int[][]{{1, 1}, {1, 1}}));
    }
}
