package easy;

import common.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    TreeNode construct(int[] nums, int i, int len) {
        if (len <= 0) return null;
        int root = i + len / 2;
        TreeNode node = new TreeNode(nums[root]);
        node.left = construct(nums, i, root - i);
        node.right = construct(nums, root + 1, len - root + i - 1);
        return node;
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBinarySearchTree c = new ConvertSortedArrayToBinarySearchTree();
        TreeNode node = c.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        System.out.println();
    }
}
