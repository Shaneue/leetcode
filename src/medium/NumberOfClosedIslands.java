package medium;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1,1,1,1,1,0],
 *                [1,0,0,0,0,1,1,0],
 *                [1,0,1,0,1,1,1,0],
 *                [1,0,0,0,0,1,0,1],
 *                [1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 *
 * Input: grid = [[0,0,1,0,0],
 *                [0,1,0,1,0],
 *                [0,1,1,1,0]]
 * Output: 1
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 *
 * 对0进行dfs，当且仅当全部被1包围时，ret++
 * dfs求connected components
 */
public class NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && connectedComponent(grid, i, j)) ret++;
            }
        }
        return ret;
    }

    boolean connectedComponent(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) return false;
        if (grid[x][y] == 1) return true;
        grid[x][y]++;
        boolean ret = true;
        ret &= connectedComponent(grid, x + 1, y);
        ret &= connectedComponent(grid, x - 1, y);
        ret &= connectedComponent(grid, x, y + 1);
        ret &= connectedComponent(grid, x, y - 1);
        return ret;
    }

    public static void main(String[] args) {
        NumberOfClosedIslands n = new NumberOfClosedIslands();
        System.out.println(n.closedIsland(new int[][]{{1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}}));
    }
}
