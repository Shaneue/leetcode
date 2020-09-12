package medium;

import common.TreeNode;

/**
 * Given a binary tree root, a ZigZag path for a binary tree is defined as follow:
 *
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right then move to the right child of the current node otherwise move to the left child.
 * Change the direction from right to left or right to left.
 * Repeat the second and third step until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 *
 * Return the longest ZigZag path contained in that tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 * Example 2:
 *
 *
 *
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 * Example 3:
 *
 * Input: root = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * Each tree has at most 50000 nodes..
 * Each node's value is between [1, 100].
 *
 * 注意左右，只要遍历一遍即可
 */
public class LongestZigZagPathInABinaryTree {
    public int longestZigZag(TreeNode root) {
        int[] ret = new int[1];
        dfs(root.left, ret, true, 1);
        dfs(root.right, ret, false, 1);
        return ret[0];
    }

    void dfs(TreeNode node, int[] ret, boolean direction, int count) {
        if (node == null) return;
        if (node.left != null) {
            dfs(node.left, ret, true, !direction ? count + 1 : 1);
        }
        if (node.right != null) {
            dfs(node.right, ret, false, direction ? count + 1 : 1);
        }
        ret[0] = Math.max(ret[0], count);
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(6);
        TreeNode n3 = new TreeNode(6, null, n4);
        TreeNode n2 = new TreeNode(6, n3, null);
        TreeNode n1 = new TreeNode(6, null, n2);
        TreeNode n5 = new TreeNode(6, null, n1);
        LongestZigZagPathInABinaryTree l = new LongestZigZagPathInABinaryTree();
        System.out.println(l.longestZigZag(n5));
    }
}
