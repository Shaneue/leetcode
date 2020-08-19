package medium;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, determine if it is a complete binary tree.
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.
 * Example 1:
 * Input: [1,2,3,4,5,6]
 * Output: true
 * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 * Input: [1,2,3,4,5,null,7]
 * Output: false
 * Explanation: The node with value 7 isn't as far left as possible.
 * <p>
 * Note:
 * <p>
 * The tree will have between 1 and 100 nodes.
 */
public class CheckCompletenessOfaBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean notNull = true;
        while (!q.isEmpty()) {
            Queue<TreeNode> t = new LinkedList<>();
            for (TreeNode node : q) {
                if (node.left != null) {
                    if (!notNull) return false;
                    t.add(node.left);
                } else {
                    notNull = false;
                }
                if (node.right != null) {
                    if (!notNull) return false;
                    t.add(node.right);
                } else {
                    notNull = false;
                }
            }
            q = t;
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n6, null);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);
        System.out.println(new CheckCompletenessOfaBinaryTree().isCompleteTree(n1));
    }
}
