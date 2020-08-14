package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 * <p>
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 */
public class JumpGame3 {
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[arr.length];
        q.add(start);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (arr[i] == 0) return true;
            visited[i] = true;
            if (i - arr[i] >= 0 && !visited[i - arr[i]]) q.add(i - arr[i]);
            if (i + arr[i] < arr.length && !visited[i + arr[i]]) q.add(i + arr[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame3().canReach(new int[]{3, 0, 2, 1, 2}, 2));
        System.out.println(new JumpGame3().canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));
    }
}
