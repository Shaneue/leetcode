package hard;

import java.util.*;

/**
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * <p>
 * In one step you can jump from index i to index:
 * <p>
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * Example 2:
 * <p>
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * Example 3:
 * <p>
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 * Example 4:
 * <p>
 * Input: arr = [6,1,9]
 * Output: 2
 * Example 5:
 * <p>
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 * <p>
 * 同值集合遍历一次即可
 */
public class JumpGame4 {
    public int minJumps(int[] arr) {
        if (arr.length == 1) return 0;
        Map<Integer, List<Integer>> count = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = count.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            count.put(arr[i], list);
        }
        int d = arr.length - 1, ret = 0;
        boolean[] visited = new boolean[arr.length];
        Set<Integer> set = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        while (true) {
            int size = q.size();
            ret++;
            while (size-- != 0) {
                int index = q.poll();
                visited[index] = true;
                int current = index - 1;
                if (current >= 0 && !visited[current]) {
                    if (current == d) return ret;
                    q.add(current);
                }
                current = index + 1;
                if (current < arr.length && !visited[current]) {
                    if (current == d) return ret;
                    q.add(current);
                }
                if (!set.contains(arr[index])) {
                    set.add(arr[index]);
                    for (int i : count.get(arr[index])) {
                        if (visited[i]) continue;
                        if (i == d) return ret;
                        q.add(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame4().minJumps(new int[]{11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13}));
        System.out.println(new JumpGame4().minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
        System.out.println(new JumpGame4().minJumps(new int[]{7, 6, 9, 6, 9, 6, 9, 7}));
        System.out.println(new JumpGame4().minJumps(new int[]{7}));
        System.out.println(new JumpGame4().minJumps(new int[]{6, 1, 9}));
    }
}
