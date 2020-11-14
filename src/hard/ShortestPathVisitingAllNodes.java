package hard;

import java.util.LinkedList;

/**
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 *
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * Example 2:
 *
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 *
 *
 * Note:
 *
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 *
 * bfs, 状态压缩
 */
public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        LinkedList<int[]> q = new LinkedList<>();
        int target = (1 << n) - 1;
        for (int i = 0; i < n; i++) q.add(new int[]{i, 1 << i});
        boolean[][] visited = new boolean[n][1 << n];
        int ret = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] node = q.poll();
                if (node[1] == target) return ret;
                if (visited[node[0]][node[1]]) continue;
                visited[node[0]][node[1]] = true;
                for (int j : graph[node[0]]) q.add(new int[]{j, node[1] | 1 << j});
            }
            ret++;
        }
        return -1;
    }
}
