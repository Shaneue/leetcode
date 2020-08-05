package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                int k = j + 1, l = nums.length - 1;
                int remain = target - nums[i] - nums[j];
                while (k < l) {
                    if (remain == nums[k] + nums[l]) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        ret.add(list);
                        while (k < l && nums[k + 1] == nums[k]) k++;
                        while (k < l && nums[l - 1] == nums[l]) l--;
                        k++;
                        l--;
                    } else if (remain > nums[k] + nums[l]) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(new int[]{1, -2, -5, -4, -3, 3, 3, 5}, -11));
    }
}
