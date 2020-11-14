package contest.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 *
 * dijkstra
 */
public class PathWithMinimumEffort {

    int[][] dirs = new int[][]{{0, 0, -1, 1}, {-1, 1, 0, 0}};

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[m][n];
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            if (p[0] == m - 1 && p[1] == n - 1) return p[2];
            visited[p[0]][p[1]] = true;
            for (int i = 0; i < 4; i++) {
                int x = p[0] + dirs[0][i];
                int y = p[1] + dirs[1][i];
                if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                    pq.add(new int[]{x, y, Math.max(p[2], Math.abs(heights[p[0]][p[1]] - heights[x][y]))});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        PathWithMinimumEffort p = new PathWithMinimumEffort();
        System.out.println(p.minimumEffortPath(new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}}));
    }
}
