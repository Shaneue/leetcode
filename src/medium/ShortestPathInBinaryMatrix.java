package medium;

import java.util.LinkedList;
import java.util.List;

/**
 * n an N by N square grid, each cell is either empty (0) or blocked (1).
 * <p>
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 * <p>
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,1],[1,0]]
 * <p>
 * <p>
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 * <p>
 * <p>
 * Output: 4
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length == grid[0].length <= 100
 * grid[r][c] is 0 or 1
 */
public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int l = grid.length;
        if (grid[0][0] == 1) return -1;
        int[][] reMatrix = new int[l][l];
        reMatrix[0][0] = 1;
        List<int[]> list = new LinkedList<>();
        list.add(new int[]{0, 0});
        while (!list.isEmpty()) {
            int[] current = list.remove(0);
            for (int i = current[0] - 1; i <= current[0] + 1; i++) {
                for (int j = current[1] - 1; j <= current[1] + 1; j++) {
                    if (i >= 0 && i < l && j >= 0 && j < l && reMatrix[i][j] == 0 && grid[i][j] != 1) {
                        reMatrix[i][j] = reMatrix[current[0]][current[1]] + 1;
                        list.add(new int[]{i, j});
                    }
                }
            }
        }
        return reMatrix[l - 1][l - 1] < 1 ? -1 : reMatrix[l - 1][l - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        System.out.println(new ShortestPathInBinaryMatrix().shortestPathBinaryMatrix(matrix));
    }
}
