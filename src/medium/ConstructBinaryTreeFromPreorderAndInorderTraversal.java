package medium;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *要从父节点开始找
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(map, preorder, 0, 0, preorder.length);
    }

    TreeNode construct(Map<Integer, Integer> map, int[] pre, int i, int j, int len) {
        if (len <= 0) return null;
        TreeNode root = new TreeNode(pre[i]);
        if (len == 1) return root;
        int k = map.get(pre[i]);
        int l = k - j;
        root.left = construct(map, pre, i + 1, j, l);
        root.right = construct(map, pre, i + 1 + l, k + 1, len - l - 1);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal c = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        TreeNode node = c.buildTree(new int[]{1, 2}, new int[]{1, 2});
        System.out.println();
    }
}
