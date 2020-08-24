package medium;

import common.TreeNode;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 *
 * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
 *
 * It's guaranteed that for the given test cases there is always possible to find a binary search tree with the given requirements.
 *
 * Example 1:
 *
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        return construct(preorder, 0, preorder.length);
    }

    TreeNode construct(int[] pre, int i, int len) {
        if (len <= 0) return null;
        TreeNode root = new TreeNode(pre[i]);
        if (len == 1) return root;
        int k = i;
        while (k < i + len && pre[k] <= pre[i]) {
            k++;
        }
        if (k >= i + len || pre[k] > pre[i]) k--;
        int l = k - i;
        root.left = construct(pre, i + 1, l);
        root.right = construct(pre, i + l + 1, len - l - 1);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinarySearchTreeFromPreorderTraversal c = new ConstructBinarySearchTreeFromPreorderTraversal();
        TreeNode node = c.bstFromPreorder(new int[]{4, 2});
        System.out.println();
    }
}
