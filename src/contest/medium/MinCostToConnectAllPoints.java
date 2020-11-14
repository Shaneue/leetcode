package contest.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * Example 2:
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 * Example 3:
 *
 * Input: points = [[0,0],[1,1],[1,0],[-1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: points = [[-1000000,-1000000],[1000000,1000000]]
 * Output: 4000000
 * Example 5:
 *
 * Input: points = [[0,0]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * All pairs (xi, yi) are distinct.
 *
 * 最小生成树用堆优化
 * prime+pq
 * kruskal+uf
 */
public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int ret = 0;
        int t = 0;
        for (int i = 1; i < n; i++) {
            for (int k = 1; k < n; k++) {
                if (!visited[k]) {
                    pq.add(new int[]{Math.abs(points[k][0] - points[t][0]) + Math.abs(points[k][1] - points[t][1]), k});
                }
            }
            while (visited[pq.peek()[1]]) pq.poll();
            int[] p = pq.poll();
            visited[p[1]] = true;
            t = p[1];
            ret += p[0];
        }
        return ret;
    }

    public static void main(String[] args) {
        MinCostToConnectAllPoints m = new MinCostToConnectAllPoints();
        System.out.println(m.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }
}
