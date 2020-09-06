package medium;

import common.TreeNode;

/**
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the sums of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * Example 2:
 *
 *
 *
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 * Example 3:
 *
 * Input: root = [2,3,9,10,7,8,6,5,4,11,1]
 * Output: 1025
 * Example 4:
 *
 * Input: root = [1,1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * Each tree has at most 50000 nodes and at least 2 nodes.
 * Each node's value is between [1, 10000].
 *
 * 用map会降低效率
 */
public class MaximumProductOfSplittedBinaryTree {
    public int maxProduct(TreeNode root) {
        long sum = sum(root);
        long[] ret = new long[1];
        compute(sum, root, ret);
        return (int) (ret[0] % (1e9 + 7));
    }

    long sum(TreeNode node) {
        if (node == null) return 0;
        return sum(node.left) + sum(node.right) + node.val;
    }

    long compute(long sum, TreeNode root, long[] ret) {
        if (root == null) return 0;
        long currentCum = root.val;
        currentCum += compute(sum, root.left, ret);
        currentCum += compute(sum, root.right, ret);
        ret[0] = Math.max(ret[0], (sum - currentCum) * currentCum);
        return currentCum;
    }

    public static void main(String[] args) {
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n6, null);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);
        MaximumProductOfSplittedBinaryTree m = new MaximumProductOfSplittedBinaryTree();
        System.out.println(m.maxProduct(n1));
    }
}
