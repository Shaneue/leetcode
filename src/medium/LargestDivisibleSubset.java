package medium;

import java.util.*;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 *
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 *
 * 排序，dp
 * dp[i]=max(dp[j]+i), if i<j and j%i=0
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        List<Integer> current;
        List<Integer> ret = new ArrayList<>();
        Map<Integer, List<Integer>> cache = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            current = new ArrayList<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0 && cache.get(j).size() > current.size()) {
                    current.clear();
                    current.addAll(cache.get(j));
                }
            }
            current.add(nums[i]);
            cache.put(i, current);
            if (ret.size() < current.size()) ret = current;
        }
        return ret;
    }


    public static void main(String[] args) {
        LargestDivisibleSubset l = new LargestDivisibleSubset();
        System.out.println(l.largestDivisibleSubset(new int[]{1, 2, 3}));
        System.out.println(l.largestDivisibleSubset(new int[]{1, 2, 4, 8}));
        System.out.println(l.largestDivisibleSubset(new int[]{5, 9, 18, 54, 108, 540, 90, 180, 360, 720}));
    }
}
