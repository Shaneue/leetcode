package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 注意一下去重的方式
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, candidates, new ArrayList<>(), 0, target);
        return ret;
    }

    void dfs(List<List<Integer>> ret, int[] candidates, List<Integer> curr, int i, int target) {
        if (target < 0) return;
        if (target == 0) {
            ret.add(new ArrayList<>(curr));
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (j > i && candidates[j - 1] == candidates[j]) continue;
            curr.add(candidates[j]);
            dfs(ret, candidates, curr, j + 1, target - candidates[j]);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum2 c = new CombinationSum2();
        System.out.println(c.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }
}
