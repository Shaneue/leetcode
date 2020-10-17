package medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is at most 1000.
 * Each node has a distinct value between 1 and 1000.
 * to_delete.length <= 1000
 * to_delete contains distinct values between 1 and 1000.
 *
 * 注意需要一个标记参数，标记自己的父是否是null
 */
public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int i : to_delete) deleteSet.add(i);
        List<TreeNode> ret = new ArrayList<>();
        dfs(ret, deleteSet, root, true);
        return ret;
    }

    void dfs(List<TreeNode> ret, Set<Integer> set, TreeNode node, boolean parentNull) {
        boolean isNull = set.contains(node.val);
        if (node.left != null) {
            boolean f = set.contains(node.left.val);
            dfs(ret, set, node.left, isNull);
            if (!isNull && f) node.left = null;
        }
        if (node.right != null) {
            boolean f = set.contains(node.right.val);
            dfs(ret, set, node.right, isNull);
            if (!isNull && f) node.right = null;
        }
        if (!isNull && parentNull) ret.add(node);
    }

    public static void main(String[] args) {
        DeleteNodesAndReturnForest d = new DeleteNodesAndReturnForest();
        TreeNode n7 = new TreeNode(7);
        TreeNode n6 = new TreeNode(6);
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n6, n7);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n1 = new TreeNode(1, n2, n3);
        System.out.println(d.delNodes(n1, new int[]{3, 5}));
    }
}
