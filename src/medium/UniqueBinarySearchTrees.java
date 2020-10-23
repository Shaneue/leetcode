package medium;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 19
 */
public class UniqueBinarySearchTrees {
    static int[] cache = new int[20];

    public int numTrees(int n) {
        if (cache[n] > 0) return cache[n];
        if (n == 1 || n == 0) return 1;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            ret += numTrees(i) * numTrees(n - i - 1);
        }
        cache[n] = ret;
        return ret;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees u = new UniqueBinarySearchTrees();
        System.out.println(u.numTrees(19));
    }
}
