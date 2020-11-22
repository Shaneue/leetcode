package contest.hard;

/**
 * You are given four integers, m, n, introvertsCount, and extrovertsCount. You have an m x n grid, and there are two types of people: introverts and extroverts. There are introvertsCount introverts and extrovertsCount extroverts.
 *
 * You should decide how many people you want to live in the grid and assign each of them one grid cell. Note that you do not have to have all the people living in the grid.
 *
 * The happiness of each person is calculated as follows:
 *
 * Introverts start with 120 happiness and lose 30 happiness for each neighbor (introvert or extrovert).
 * Extroverts start with 40 happiness and gain 20 happiness for each neighbor (introvert or extrovert).
 * Neighbors live in the directly adjacent cells north, east, south, and west of a person's cell.
 *
 * The grid happiness is the sum of each person's happiness. Return the maximum possible grid happiness.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
 * Output: 240
 * Explanation: Assume the grid is 1-indexed with coordinates (row, column).
 * We can put the introvert in cell (1,1) and put the extroverts in cells (1,3) and (2,3).
 * - Introvert at (1,1) happiness: 120 (starting happiness) - (0 * 30) (0 neighbors) = 120
 * - Extrovert at (1,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
 * - Extrovert at (2,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
 * The grid happiness is 120 + 60 + 60 = 240.
 * The above figure shows the grid in this example with each person's happiness. The introvert stays in the light green cell while the extroverts live on the light purple cells.
 * Example 2:
 *
 * Input: m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
 * Output: 260
 * Explanation: Place the two introverts in (1,1) and (3,1) and the extrovert at (2,1).
 * - Introvert at (1,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
 * - Extrovert at (2,1) happiness: 40 (starting happiness) + (2 * 20) (2 neighbors) = 80
 * - Introvert at (3,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
 * The grid happiness is 90 + 80 + 90 = 260.
 * Example 3:
 *
 * Input: m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
 * Output: 240
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 5
 * 0 <= introvertsCount, extrovertsCount <= min(m * n, 6)
 *
 * 可以通过前面n个元素的状态压缩来dp
 */
public class MaximizeGridHappiness {
    int m;
    int n;
    int[][][][][][] dp;

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int[][] grid = new int[m][n];
        this.m = m;
        this.n = n;
        dp = new int[m][n][introvertsCount + 1][extrovertsCount + 1][64][64];
        dfs(grid, 0, 0, introvertsCount, extrovertsCount, 0, 0);
        return dp[0][0][introvertsCount][extrovertsCount][0][0];
    }

    int dfs(int[][] grid, int x, int y, int in, int ex, int state1, int state2) {
        if (in == 0 && ex == 0) return 0;
        if (x >= m || y >= n) return 0;
        if (dp[x][y][in][ex][state1][state2] > 0) return dp[x][y][in][ex][state1][state2];
        int nextX = x + 1;
        int nextY = y;
        if (nextX >= m) {
            nextX = 0;
            nextY++;
        }
        int up = x == 0 ? 0 : grid[x - 1][y];
        int left = y == 0 ? 0 : grid[x][y - 1];
        int nextState1 = (state1 << 1) & 63, nextState2 = (state2 << 1) & 63;
        int max = dfs(grid, nextX, nextY, in, ex, nextState1, nextState2);
        if (in > 0) {
            grid[x][y] = 1;
            int diff = 120;
            if (up == 1) diff -= 60;
            else if (up == 2) diff -= 10;
            if (left == 1) diff -= 60;
            else if (left == 2) diff -= 10;
            max = Math.max(max, diff + dfs(grid, nextX, nextY, in - 1, ex, nextState1 + 1, nextState2));
        }
        if (ex > 0) {
            grid[x][y] = 2;
            int diff = 40;
            if (up == 1) diff -= 10;
            else if (up == 2) diff += 40;
            if (left == 1) diff -= 10;
            else if (left == 2) diff += 40;
            max = Math.max(max, diff + dfs(grid, nextX, nextY, in, ex - 1, nextState1, nextState2 + 1));
        }
        grid[x][y] = 0;
        dp[x][y][in][ex][state1][state2] = max;
        return max;
    }

    public static void main(String[] args) {
        MaximizeGridHappiness m = new MaximizeGridHappiness();
        System.out.println(m.getMaxGridHappiness(5, 5, 6, 6));
        System.out.println(m.getMaxGridHappiness(5, 5, 3, 6));
        m = new MaximizeGridHappiness();
        System.out.println(m.getMaxGridHappiness(3, 1, 2, 1));
        m = new MaximizeGridHappiness();
        System.out.println(m.getMaxGridHappiness(2, 2, 4, 0));
    }
}
