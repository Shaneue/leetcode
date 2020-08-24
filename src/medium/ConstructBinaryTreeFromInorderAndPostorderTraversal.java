package medium;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return construct(map, postorder, postorder.length - 1, postorder.length - 1, postorder.length);
    }

    TreeNode construct(Map<Integer, Integer> map, int[] post, int i, int j, int len) {
        if (len <= 0) return null;
        TreeNode root = new TreeNode(post[i]);
        if (len == 1) return root;
        int k = map.get(post[i]);
        int l = j - k;
        root.right = construct(map, post, i - 1, j, l);
        root.left = construct(map, post, i - 1 - l, k - 1, len - l - 1);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal c = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
        TreeNode node = c.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        System.out.println();
    }
}
