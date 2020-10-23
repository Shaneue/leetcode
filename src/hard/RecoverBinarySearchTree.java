package hard;

import common.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 *
 * 注意一下second的判定
 */
public class RecoverBinarySearchTree {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode last = null;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

    void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        if (second != null && second.val > node.val) second = node;
        if (first == null && last != null && last.val > node.val) {
            first = last;
            second = node;
        }
        last = node;
        inOrder(node.right);
    }

    public static void main(String[] args) {
        TreeNode n3 = new TreeNode(1);
        TreeNode n2 = new TreeNode(4, new TreeNode(2), null);
        TreeNode n1 = new TreeNode(3);
        n1.left = n3;
        n1.right = n2;
        RecoverBinarySearchTree r = new RecoverBinarySearchTree();
        r.recoverTree(n1);
        System.out.println();
    }
}
