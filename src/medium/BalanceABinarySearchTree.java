package medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 *
 * A binary search tree is balanced if and only if the depth of the two subtrees of every node never differ by more than 1.
 *
 * If there is more than one answer, return any of them.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,null,2,null,3,null,4,null,null]
 * Output: [2,1,3,null,null,null,4]
 * Explanation: This is not the only correct answer, [3,1,4,null,2,null,null] is also correct.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is between 1 and 10^4.
 * The tree nodes will have distinct values between 1 and 10^5.
 *
 * sublist有点低效，可以直接用下标
 */
public class BalanceABinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        searchTree(list, root);
        return constructTree(list);
    }

    void searchTree(List<TreeNode> list, TreeNode root) {
        if (root == null) return;
        searchTree(list, root.left);
        list.add(root);
        searchTree(list, root.right);
    }

    TreeNode constructTree(List<TreeNode> list) {
        if (list.size() == 0) return null;
        TreeNode ret = list.get(list.size() / 2);
        ret.left = constructTree(list.subList(0, list.size() / 2));
        ret.right = constructTree(list.subList(list.size() / 2 + 1, list.size()));
        return ret;
    }
}
