package medium;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Return any binary tree that matches the given preorder and postorder traversals.
 *
 * Values in the traversals pre and post are distinct positive integers.
 *
 *
 *
 * Example 1:
 *
 * Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 *
 *
 * Note:
 *
 * 1 <= pre.length == post.length <= 30
 * pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 * It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 *
 * preorder与postorder不能确定唯一二叉树
 * j时post的起点位置，i是pre的起点位置，通过pre与post求对应左右子树的范围，且它们都是连续的区间。
 */
public class ConstructBinaryTreefromPreorderAndPostorderTraversal {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return construct(map, pre, 0, 0, pre.length);
    }

    TreeNode construct(Map<Integer, Integer> map, int[] pre, int i, int j, int len) {
        if (len <= 0) return null;
        TreeNode node = new TreeNode(pre[i]);
        if (len == 1) return node;
        int k = map.get(pre[i + 1]);
        int l = k - j + 1;
        node.left = construct(map, pre, i + 1, j, l);
        node.right = construct(map, pre, i + 1 + l, k + 1, len - l - 1);
        return node;
    }
}
