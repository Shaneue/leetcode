package medium;


import java.util.ArrayList;
import java.util.List;

/**
 * You are given a map of a server center, represented as a m * n integer grid grid, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.
 * <p>
 * Return the number of servers that communicate with any other server.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,0],[0,1]]
 * Output: 0
 * Explanation: No servers can communicate with others.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,0],[1,1]]
 * Output: 3
 * Explanation: All three servers can communicate with at least one other server.
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
 * Output: 4
 * Explanation: The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can't communicate with any other server.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 250
 * 1 <= n <= 250
 * grid[i][j] == 0 or 1
 */
public class CountServers {
    public int countServers(int[][] grid) {
        int[] row = new int[grid.length];
        int[] column = new int[grid[0].length];
        List<int[]> computers = new ArrayList<>();
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    column[j]++;
                    computers.add(new int[]{i, j});
                }
            }
        }
        for (int[] computer : computers) {
            if (row[computer[0]] > 1 || column[computer[1]] > 1) {
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 1}, {1, 0, 0}, {0, 0, 0}};
        System.out.println(new CountServers().countServers(matrix));
    }
}
