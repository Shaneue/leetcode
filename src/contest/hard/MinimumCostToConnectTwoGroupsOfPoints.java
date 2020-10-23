package contest.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given two groups of points where the first group has size1 points, the second group has size2 points, and size1 >= size2.
 *
 * The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j] is the cost of connecting point i of the first group and point j of the second group. The groups are connected if each point in both groups is connected to one or more points in the opposite group. In other words, each point in the first group must be connected to at least one point in the second group, and each point in the second group must be connected to at least one point in the first group.
 *
 * Return the minimum cost it takes to connect the two groups.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: cost = [[15, 96], [36, 2]]
 * Output: 17
 * Explanation: The optimal way of connecting the groups is:
 * 1--A
 * 2--B
 * This results in a total cost of 17.
 * Example 2:
 *
 *
 * Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
 * Output: 4
 * Explanation: The optimal way of connecting the groups is:
 * 1--A
 * 2--B
 * 2--C
 * 3--A
 * This results in a total cost of 4.
 * Note that there are multiple points connected to point 2 in the first group and point A in the second group. This does not matter as there is no limit to the number of points that can be connected. We only care about the minimum total cost.
 * Example 3:
 *
 * Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
 * Output: 10
 *
 *
 * Constraints:
 *
 * size1 == cost.length
 * size2 == cost[i].length
 * 1 <= size1, size2 <= 12
 * size1 >= size2
 * 0 <= cost[i][j] <= 100
 *
 * 左边每个点先选一条，如果右边没有匹配的话，再加上右边没有匹配的点的最小值的边
 * 在dfs过程中会有很多重复计算，通过dp[13][4096]来剪枝
 * 这是一道NP-hard的题目
 */
public class MinimumCostToConnectTwoGroupsOfPoints {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int[] size2Min = new int[cost.get(0).size()];
        Arrays.fill(size2Min, Integer.MAX_VALUE);
        for (List<Integer> integers : cost) {
            for (int j = 0; j < cost.get(0).size(); j++) {
                size2Min[j] = Math.min(integers.get(j), size2Min[j]);
            }
        }
        return dfs(new int[13][4096], cost, cost.get(0).size(), size2Min, 0, 0);
    }

    int dfs(int[][] dp, List<List<Integer>> cost, int size2, int[] size2Min, int i, int mask) {
        if (dp[i][mask] != 0) return dp[i][mask] - 1;
        int ret = i >= cost.size() ? 0 : Integer.MAX_VALUE;
        if (i >= cost.size()) {
            for (int j = 0; j < size2; j++) {
                if ((mask & (1 << j)) == 0) {
                    ret += size2Min[j];
                }
            }
        } else {
            for (int j = 0; j < size2; j++)
                ret = Math.min(ret, cost.get(i).get(j) + dfs(dp, cost, size2, size2Min, i + 1, mask | 1 << j));
        }
        dp[i][mask] = ret + 1;
        return ret;
    }

    public static void main(String[] args) {
        MinimumCostToConnectTwoGroupsOfPoints m = new MinimumCostToConnectTwoGroupsOfPoints();
        List<List<Integer>> cost = new ArrayList<>();
        List<Integer> l1 = Arrays.asList(2, 5, 1);
        List<Integer> l2 = Arrays.asList(3, 4, 7);
        List<Integer> l3 = Arrays.asList(8, 1, 2);
        List<Integer> l4 = Arrays.asList(6, 2, 4);
        List<Integer> l5 = Arrays.asList(3, 8, 8);
        cost.add(l1);
        cost.add(l2);
        cost.add(l3);
        cost.add(l4);
        cost.add(l5);
        System.out.println(m.connectTwoGroups(cost));
    }
}
