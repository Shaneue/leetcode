package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 * Example 1:
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 *
 * dijkstra
 * 用二维数组会造成MLE，边比较少，直接用点边表示图即可
 */
public class PathWithMaximumProbability {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, Map<Integer, Double>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new HashMap<>());
        for (int i = 0; i < edges.length; i++) {
            g.get(edges[i][0]).put(edges[i][1], succProb[i]);
            g.get(edges[i][1]).put(edges[i][0], succProb[i]);
        }
        boolean[] visited = new boolean[n];
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        pq.add(new double[]{start, 1.0});
        while (!pq.isEmpty()) {
            double[] now = pq.poll();
            visited[(int) now[0]] = true;
            if (Double.compare(now[0], end) == 0) return now[1];
            for (Map.Entry<Integer, Double> map : g.get((int) now[0]).entrySet()) {
                if (!visited[map.getKey()]) {
                    pq.add(new double[]{map.getKey(), map.getValue() * now[1]});
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        PathWithMaximumProbability p = new PathWithMaximumProbability();
        System.out.println(p.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2}, 0, 2));
        System.out.println(p.maxProbability(3, new int[][]{{0, 1}}, new double[]{0.5}, 0, 2));
    }
}
