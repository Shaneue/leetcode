package hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
 *
 * Example:
 *
 * Given the following 3x6 height map:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 *
 * Return 4.
 *
 *
 * The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
 *
 *
 *
 *
 *
 * After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 *
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 110
 * 0 <= heightMap[i][j] <= 20000
 */
public class TrappingRainWater2 {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int i = 0; i < n; i++) {
            pq.add(new int[]{0, i, heightMap[0][i]});
            visited[0][i] = true;
        }
        for (int i = 1; i < m; i++) {
            pq.add(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][n - 1] = true;
        }
        for (int i = n - 2; i >= 0; i--) {
            pq.add(new int[]{m - 1, i, heightMap[m - 1][i]});
            visited[m - 1][i] = true;
        }
        for (int i = m - 2; i >= 1; i--) {
            pq.add(new int[]{i, 0, heightMap[i][0]});
            visited[i][0] = true;
        }
        int ret = 0;
        int min = pq.peek()[2];
        int[] xx = {0, 0, 1, -1};
        int[] yy = {1, -1, 0, 0};
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            if (p[2] > min) min = p[2];
            for (int i = 0; i < 4; i++) {
                int x = p[0] + xx[i];
                int y = p[1] + yy[i];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    visited[x][y] = true;
                    if (heightMap[x][y] < min) ret += min - heightMap[x][y];
                    pq.add(new int[]{x, y, heightMap[x][y]});
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TrappingRainWater2 t = new TrappingRainWater2();
        System.out.println(t.trapRainWater(new int[][]{
                {5, 8, 7, 7},
                {5, 2, 1, 5},
                {7, 1, 7, 1},
                {8, 9, 6, 9},
                {9, 8, 9, 9}}));
        System.out.println(t.trapRainWater(new int[][]{{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}}));
    }
}
