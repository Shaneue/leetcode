package medium;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [
 *   ['1','1','1','1','0'],
 *   ['1','1','0','1','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','0','0','0']
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ['1','1','0','0','0'],
 *   ['1','1','0','0','0'],
 *   ['0','0','1','0','0'],
 *   ['0','0','0','1','1']
 * ]
 * Output: 3
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && connectedComponent(grid, i, j)) ret++;
            }
        }
        return ret;
    }

    boolean connectedComponent(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return true;
        if (grid[x][y] == '0' || grid[x][y] == '2') return true;
        boolean ret;
        grid[x][y] = '2';
        ret = connectedComponent(grid, x + 1, y);
        ret &= connectedComponent(grid, x - 1, y);
        ret &= connectedComponent(grid, x, y + 1);
        ret &= connectedComponent(grid, x, y - 1);
        return ret;
    }

    public static void main(String[] args) {
        NumberOfIslands n = new NumberOfIslands();
        System.out.println(n.numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));
    }
}
