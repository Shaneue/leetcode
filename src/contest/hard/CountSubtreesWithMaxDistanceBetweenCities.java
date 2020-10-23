package contest.hard;

import java.util.*;

/**
 * There are n cities numbered from 1 to n. You are given an array edges of size n-1, where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi. There exists a unique path between each pair of cities. In other words, the cities form a tree.
 *
 * A subtree is a subset of cities where every city is reachable from every other city in the subset, where the path between each pair passes through only the cities from the subset. Two subtrees are different if there is a city in one subtree that is not present in the other.
 *
 * For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the subtree is equal to d.
 *
 * Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum distance between any two cities is equal to d.
 *
 * Notice that the distance between the two cities is the number of edges in the path between them.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, edges = [[1,2],[2,3],[2,4]]
 * Output: [3,4,0]
 * Explanation:
 * The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
 * The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
 * No subtree has two nodes where the max distance between them is 3.
 * Example 2:
 *
 * Input: n = 2, edges = [[1,2]]
 * Output: [1]
 * Example 3:
 *
 * Input: n = 3, edges = [[1,2],[2,3]]
 * Output: [2,1]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 15
 * edges.length == n-1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * All pairs (ui, vi) are distinct.
 *
 * 两遍bfs/dfs求树的直径
 * 最优解使用dp
 */
public class CountSubtreesWithMaxDistanceBetweenCities {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[] ret = new int[n - 1];
        int size = 1 << n;
        for (int i = 1; i < size; i++) {
            int diameter = diameter(i, edges);
            if (diameter > 0) {
                ret[diameter - 1]++;
            }
        }
        return ret;
    }

    int diameter(int state, int[][] edges) {
        Set<Integer> cities = new HashSet<>();
        for (int i = 0; state > 0; i++) {
            if ((state & 1) == 1) cities.add(i + 1);
            state >>= 1;
        }
        if (cities.size() < 2) return 0;
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i : cities) g.put(i, new HashSet<>());
        int start = 0;
        int c = 0;
        for (int[] edge : edges) {
            if (cities.contains(edge[0]) && cities.contains(edge[1])) {
                c++;
                start = edge[0];
                g.get(edge[0]).add(edge[1]);
                g.get(edge[1]).add(edge[0]);
            }
        }
        if (c != cities.size() - 1) return 0;
        int[] max = new int[]{0};
        int[] t = new int[]{0};
        dfs(max, t, g, start, new boolean[20], 0);
        dfs(max, t, g, t[0], new boolean[20], 0);
        return max[0];
    }

    void dfs(int[] max, int[] t, Map<Integer, Set<Integer>> g, int start, boolean[] visited, int depth) {
        visited[start] = true;
        if (depth > max[0]) {
            max[0] = depth;
            t[0] = start;
        }
        for (int p : g.get(start)) {
            if (!visited[p]) {
                dfs(max, t, g, p, visited, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        CountSubtreesWithMaxDistanceBetweenCities c = new CountSubtreesWithMaxDistanceBetweenCities();
        System.out.println(Arrays.toString(c.countSubgraphsForEachDiameter(4, new int[][]{{1, 2}, {2, 3}, {2, 4}})));
        System.out.println(Arrays.toString(c.countSubgraphsForEachDiameter(2, new int[][]{{1, 2}})));
        System.out.println(Arrays.toString(c.countSubgraphsForEachDiameter(3, new int[][]{{1, 2}, {2, 3}})));
    }
}
