package easy;

import common.TreeNode;

/**
 *You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 *
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 * Example 1:
 * Input: Binary tree: [1,2,3,4]
 *        1
 *      /   \
 *     2     3
 *    /
 *   4
 *
 * Output: "1(2(4))(3)"
 *
 * Explanation: Originallay it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs.
 * And it will be "1(2(4))(3)".
 * Example 2:
 * Input: Binary tree: [1,2,3,null,4]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *
 * Output: "1(2()(4))(3)"
 *
 * Explanation: Almost the same as the first example,
 * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 */
public class ConstructStringFromBinaryTree {
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        dfs(sb, t);
        return sb.toString();
    }

    void dfs(StringBuilder sb, TreeNode node) {
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            return;
        }
        if (node.left != null) {
            sb.append('(');
            dfs(sb, node.left);
            sb.append(')');
        } else {
            sb.append("()");
        }
        if (node.right != null) {
            sb.append('(');
            dfs(sb, node.right);
            sb.append(')');
        }
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n4, null);
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1, n2, n3);
        ConstructStringFromBinaryTree c = new ConstructStringFromBinaryTree();
        System.out.println(c.tree2str(n1));
    }
}
