package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * <p>
 * <p>
 * Note:
 * <p>
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 * <p>
 * 去重的时候用一个boolean数组即可，因为给定的数范围只有200。
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        search(ret, indices, nums, 0);
        return ret;
    }

    public void search(List<List<Integer>> ret, List<Integer> list, int[] nums, int index) {
        if (list.size() >= 2) ret.add(new ArrayList<>(list));
        boolean[] visited = new boolean[201];
        for (int i = index; i < nums.length; i++) {
            if ((list.size() == 0 || list.get(list.size() - 1) <= nums[i]) && !visited[nums[i] + 100]) {
                visited[nums[i] + 100] = true;
                list.add(nums[i]);
                search(ret, list, nums, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        System.out.println(new IncreasingSubsequences().findSubsequences(nums));
    }
}
