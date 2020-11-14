package hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between nodes fromi and toi. A minimum spanning tree (MST) is a subset of the edges of the graph that connects all vertices without cycles and with the minimum possible total edge weight.
 *
 * Find all the critical and pseudo-critical edges in the minimum spanning tree (MST) of the given graph. An MST edge whose deletion from the graph would cause the MST weight to increase is called a critical edge. A pseudo-critical edge, on the other hand, is that which can appear in some MSTs but not all.
 *
 * Note that you can return the indices of the edges in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * Output: [[0,1],[2,3,4,5]]
 * Explanation: The figure above describes the graph.
 * The following figure shows all the possible MSTs:
 *
 * Notice that the two edges 0 and 1 appear in all MSTs, therefore they are critical edges, so we return them in the first list of the output.
 * The edges 2, 3, 4, and 5 are only part of some MSTs, therefore they are considered pseudo-critical edges. We add them to the second list of the output.
 * Example 2:
 *
 *
 *
 * Input: n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * Output: [[],[0,1,2,3]]
 * Explanation: We can observe that since all 4 edges have equal weight, choosing any 3 edges from the given 4 will yield an MST. Therefore all 4 edges are pseudo-critical.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti <= 1000
 * All pairs (fromi, toi) are distinct.
 *
 * mst: kruskal+uf
 */
public class FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[][] edgesIndex = new int[edges.length][4];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, edgesIndex[i], 0, 3);
            edgesIndex[i][3] = i;
        }
        Arrays.sort(edgesIndex, Comparator.comparingInt(a -> a[2]));
        int target = mst(edgesIndex, null, -1, n);
        List<Integer> critical = new ArrayList<>();
        for (int[] edge : edgesIndex) if (target != mst(edgesIndex, null, edge[3], n)) critical.add(edge[3]);
        List<Integer> pseudo = new ArrayList<>();
        for (int[] edge : edgesIndex) {
            if (!critical.contains(edge[3]) && target == mst(edgesIndex, edge, -1, n)) {
                pseudo.add(edge[3]);
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(critical);
        ret.add(pseudo);
        return ret;
    }

    int mst(int[][] edges, int[] in, int out, int n) {
        int[] f = new int[n];
        for (int i = 0; i < n; i++) f[i] = i;
        int sum = 0;
        if (in != null) {
            f[in[0]] = in[1];
            sum += in[2];
        }
        for (int[] edge : edges) {
            if (edge[3] == out) continue;
            int x = root(f, edge[0]);
            int y = root(f, edge[1]);
            if (x != y) {
                f[x] = y;
                sum += edge[2];
            }
        }
        return sum;
    }

    int root(int[] f, int x) {
        if (f[x] != x) f[x] = root(f, f[x]);
        return f[x];
    }
}
