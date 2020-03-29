package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Input:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * Output: ["1->2->5", "1->3"]
 * <p>
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePath {
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
        List<String> results = new ArrayList<String>();
        recursiveSearch(root, path, results);
        return results;
    }

    public void recursiveSearch(TreeNode root, List<Integer> path, List<String> results) {
        if (root == null)
            return;
        path.add(root.val);
        if (root.left == null && root.right == null)
            results.add(getPath(path));
        else {
            if (root.left != null)
                recursiveSearch(root.left, path, results);
            if (root.right != null)
                recursiveSearch(root.right, path, results);
        }
        path.remove(path.size() - 1);
    }

    public String getPath(List<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i != path.size() - 1)
                sb.append(path.get(i)).append("->");
            else
                sb.append(path.get(i));
        }
        return sb.toString();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
