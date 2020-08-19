package medium;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.
 * <p>
 * The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * <p>
 * 1
 * /   \
 * 3     2
 * / \     \
 * 5   3     9
 * <p>
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 * Example 2:
 * <p>
 * Input:
 * <p>
 * 1
 * /
 * 3
 * / \
 * 5   3
 * <p>
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 * Example 3:
 * <p>
 * Input:
 * <p>
 * 1
 * / \
 * 3   2
 * /
 * 5
 * <p>
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 * Example 4:
 * <p>
 * Input:
 * <p>
 * 1
 * / \
 * 3   2
 * /     \
 * 5       9
 * /         \
 * 6           7
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 * <p>
 * <p>
 * Note: Answer will in the range of 32-bit signed integer.
 *
 * 空间复杂度可以优化
 */


public class WidthOfBinaryTree {
    int width = Integer.MIN_VALUE;


    public int widthOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        List<NodePosition> currentLevel = new ArrayList<>();
        List<NodePosition> nextLevel;
        currentLevel.add(new NodePosition(root, 1));
        while (!currentLevel.isEmpty()) {
            nextLevel = new ArrayList<>();
            int left = Integer.MAX_VALUE;
            int right = Integer.MIN_VALUE;
            for (NodePosition node : currentLevel) {
                if (node.position < left) {
                    left = node.position;
                }
                if (node.position > right) {
                    right = node.position;
                }
                if (node.node.left != null) {
                    nextLevel.add(new NodePosition(node.node.left, node.position * 2 - 1));
                }
                if (node.node.right != null) {
                    nextLevel.add(new NodePosition(node.node.right, node.position * 2));
                }
            }
            if (width < right - left + 1) {
                width = right - left + 1;
            }
            currentLevel.clear();
            currentLevel = nextLevel;
        }
        return width;
    }

    public class NodePosition {
        TreeNode node;
        int position;

        NodePosition(TreeNode node, int position) {
            this.node = node;
            this.position = position;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = null;
        System.out.println(new WidthOfBinaryTree().widthOfBinaryTree(null));
    }

}
