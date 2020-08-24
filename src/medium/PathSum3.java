package medium;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value.
 *
 * Find the number of paths that sum to a given value.
 *
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 *
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 *
 * 换个方式计算，长路径和减去短路径和。
 */
public class PathSum3 {
    Map<Integer, Integer> pathSums = new HashMap<>();

    public int pathSum(TreeNode root, int sum) {
        pathSums.put(0, 1);
        return dfs(root, 0, sum);
    }

    int dfs(TreeNode node, int currentSum, int target) {
        if (node == null) return 0;
        currentSum += node.val;
        int ret = pathSums.getOrDefault(currentSum - target, 0);
        pathSums.put(currentSum, pathSums.getOrDefault(currentSum, 0) + 1);
        ret += dfs(node.left, currentSum, target);
        ret += dfs(node.right, currentSum, target);
        pathSums.put(currentSum, pathSums.get(currentSum) - 1);
        return ret;
    }

    public static void main(String[] args) {
        TreeNode n9 = new TreeNode(1);
        TreeNode n8 = new TreeNode(-2);
        TreeNode n7 = new TreeNode(3);
        TreeNode n6 = new TreeNode(3, n7, n8);
        TreeNode n5 = new TreeNode(2, null, n9);
        TreeNode n4 = new TreeNode(11);
        TreeNode n3 = new TreeNode(3, n6, n5);
        TreeNode n2 = new TreeNode(2, null, n3);
        TreeNode n1 = new TreeNode(1, null, n2);
        PathSum3 p = new PathSum3();
        System.out.println(p.pathSum(n1, 8));
    }
}
