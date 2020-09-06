package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Alice and Bob have an undirected graph of n nodes and 3 types of edges:
 *
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can by traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.
 *
 * Return the maximum number of edges you can remove, or return -1 if it's impossible for the graph to be fully traversed by Alice and Bob.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * Output: 2
 * Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
 * Example 2:
 *
 *
 *
 * Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * Output: 0
 * Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
 * Example 3:
 *
 *
 *
 * Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * Output: -1
 * Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 *
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
 * edges[i].length == 3
 * 1 <= edges[i][0] <= 3
 * 1 <= edges[i][1] < edges[i][2] <= n
 * All tuples (typei, ui, vi) are distinct.
 *
 * 需要两个union-find set
 */
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] fa = new int[n + 1];
        int[] fb = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fa[i] = i;
            fb[i] = i;
        }
        Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0]));
        int ret = 0;
        int n1 = n, n2 = n;

        for (int[] edge : edges) {
            if (edge[0] == 3) {
                int rootA = root(edge[1], fa);
                int rootB = root(edge[2], fa);
                if (rootA != rootB) {
                    union(edge[1], edge[2], fa);
                    union(edge[1], edge[2], fb);
                    n1--;
                    n2--;
                } else ret++;
            } else if (edge[0] == 1) {
                int rootA = root(edge[1], fa);
                int rootB = root(edge[2], fa);
                if (rootA != rootB) {
                    union(edge[1], edge[2], fa);
                    n1--;
                } else ret++;
            } else if (edge[0] == 2) {
                int rootA = root(edge[1], fb);
                int rootB = root(edge[2], fb);
                if (rootA != rootB) {
                    union(edge[1], edge[2], fb);
                    n2--;
                } else ret++;
            }
        }
        if (n1 != 1 || n2 != 1) return -1;
        return ret;
    }

    void union(int i, int j, int[] f) {
        int rootI = root(i, f);
        int rootJ = root(j, f);
        if (rootI != rootJ) {
            f[rootI] = f[rootJ];
        }
    }

    int root(int i, int[] f) {
        if (i != f[i]) {
            f[i] = root(f[i], f);
        }
        return f[i];
    }

    public static void main(String[] args) {
        RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable r = new RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable();
        System.out.println(r.maxNumEdgesToRemove(4, new int[][]{{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}}));
    }
}
