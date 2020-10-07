package medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSum2 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, new ArrayList<>(), root, 0, sum);
        return ret;
    }

    void dfs(List<List<Integer>> ret, List<Integer> current, TreeNode node, int sum, int target) {
        if (node == null) return;
        current.add(node.val);
        sum += node.val;
        if (sum == target && node.left == null & node.right == null) ret.add(new ArrayList<>(current));
        dfs(ret, current, node.left, sum, target);
        dfs(ret, current, node.right, sum, target);
        current.remove(current.size() - 1);
    }
}
