package easy;

import common.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 * 左右子树深度之和
 * 这里不需要cache，只会计算一次
 */
public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] ret = new int[1];
        compute(ret, root);
        return ret[0];
    }

    int compute(int[] ret, TreeNode node) {
        if (node == null) return 0;
        int left = compute(ret, node.left);
        int right = compute(ret, node.right);
        ret[0] = Math.max(ret[0], left + right);
        return Math.max(right, left) + 1;
    }
}
