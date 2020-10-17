package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Example 2:
 *
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 *
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 *
 * Note:
 *
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class Matrix01 {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) {
                    visited[i][j] = true;
                    q.add(new int[]{i, j});
                }
        int[][] direction = {{0, 0, 1, -1}, {1, -1, 0, 0}};
        int[][] ret = new int[m][n];
        int c = 1;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] p = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = p[0] + direction[0][j];
                    int y = p[1] + direction[1][j];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        ret[x][y] = c;
                        visited[x][y] = true;
                        q.add(new int[]{x, y});
                    }
                }
            }
            c++;
        }
        return ret;
    }
}
