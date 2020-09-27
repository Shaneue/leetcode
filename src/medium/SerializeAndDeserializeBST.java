package medium;

import common.TreeNode;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 * deserialize的使用用另外的递归方法效率快
 */
public class SerializeAndDeserializeBST {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        String left = root.left == null ? "" : " " + serialize(root.left);
        String right = root.right == null ? "" : " " + serialize(root.right);
        return root.val + left + right;
    }

    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] ints = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(ints[0]));
        int r = 1;
        for (; r < ints.length; r++) {
            if (Integer.parseInt(ints[r]) > root.val) break;
        }
        StringBuilder left = new StringBuilder();
        for (int i = 1; i < r; i++) left.append(ints[i]).append(" ");
        StringBuilder right = new StringBuilder();
        for (int i = r; i < ints.length; i++) right.append(ints[i]).append(" ");
        root.left = deserialize(left.toString().trim());
        root.right = deserialize(right.toString().trim());
        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBST s = new SerializeAndDeserializeBST();
        TreeNode n4 = new TreeNode(2);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3, null, n4);
        TreeNode n1 = new TreeNode(4, n3, n2);
        String str = s.serialize(n1);
        System.out.println(str);

        n1 = s.deserialize(str);
        System.out.println();
    }
}
