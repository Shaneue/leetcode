package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, new ArrayList<>(), n, k, 1);
        return ret;
    }

    void dfs(List<List<Integer>> ret, List<Integer> currentList, int n, int k, int i) {
        if (currentList.size() == k) {
            ret.add(new ArrayList<>(currentList));
            return;
        }
        if (i > n) return;
        if (k - currentList.size() - 1 <= n - i) {
            currentList.add(i);
            dfs(ret, currentList, n, k, i + 1);
            currentList.remove(currentList.size() - 1);
        }
        if (k - currentList.size() <= n - i) {
            dfs(ret, currentList, n, k, i + 1);
        }
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        System.out.println(c.combine(10, 2).size());
    }
}
