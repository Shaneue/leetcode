package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        solve(ret, nums, indices, 0);
        return ret;
    }

    void solve(List<List<Integer>> ret, int[] nums, List<Integer> indices, int i) {
        if (i == nums.length)
            ret.add(makeList(nums, indices));
        if (i < nums.length) {
            indices.add(i);
            solve(ret, nums, indices, i + 1);
            indices.remove(indices.size() - 1);
            solve(ret, nums, indices, i + 1);
        }
    }

    List<Integer> makeList(int[] nums, List<Integer> indices) {
        List<Integer> list = new ArrayList<>();
        for (int i : indices) {
            list.add(nums[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[]{1, 2}));
    }
}
