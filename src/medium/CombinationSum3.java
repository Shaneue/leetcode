package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n > 45) return new ArrayList<>();
        List<List<Integer>> ret = new ArrayList<>();
        makeCombinations(ret, new ArrayList<>(), 0, 1, n, k);
        return ret;
    }

    void makeCombinations(List<List<Integer>> ret, List<Integer> curr, int total, int c, int n, int k) {
        if (total == n) {
            if (curr.size() == k)
                ret.add(new ArrayList<>(curr));
            return;
        }
        if (c > 9) return;
        int diff = n - total;
        if ((c + 9) * (9 - c + 1) / 2 < diff) return;
        if (k - curr.size() > 9 - c + 1) return;
        makeCombinations(ret, curr, total, c + 1, n, k);
        curr.add(c);
        makeCombinations(ret, curr, total + c, c + 1, n, k);
        curr.remove(curr.size() - 1);
    }

    public static void main(String[] args) {
        CombinationSum3 c = new CombinationSum3();
        System.out.println(c.combinationSum3(3, 9));
    }
}
