package hard;

/**
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 *   1
 *  / \
 * v   v
 * 2-->3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5 <- 1 -> 2
 *      ^    |
 *      |    v
 *      4 <- 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 * 1、可能有一个节点有两个父，如果没有，就一定有环
 * 2、如果有，就记录下来，并删掉第二条边
 * 3、再找一下有没有环
 * 总之有两种情况要考虑：1、有环；2、有节点有两个父。
 * 两种情况可能同时存在，又有可能只存在其中一个
 */
public class RedundantConnection2 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[][] sameParent = new int[2][2];
        int[] parent = new int[edges.length + 1];
        for (int[] e : edges) {
            if (parent[e[1]] > 0) {
                sameParent[0][0] = parent[e[1]];
                sameParent[1][0] = e[0];
                sameParent[0][1] = sameParent[1][1] = e[1];
                e[0] = -1;
                break;
            }
            parent[e[1]] = e[0];
        }
        int[] f = new int[edges.length + 1];
        for (int i = 0; i < f.length; i++) f[i] = i;
        for (int[] e : edges) {
            if (e[0] == -1) continue;
            int x = root(f, e[0]);
            int y = root(f, e[1]);
            if (x == y) return sameParent[0][0] == 0 ? e : sameParent[0];
            f[x] = y;
        }
        return sameParent[1];
    }

    int root(int[] f, int x) {
        if (f[x] != x) f[x] = root(f, f[x]);
        return f[x];
    }
}
