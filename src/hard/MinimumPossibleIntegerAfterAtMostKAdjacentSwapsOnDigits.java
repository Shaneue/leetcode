package hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a string num representing the digits of a very large integer and an integer k.
 *
 * You are allowed to swap any two adjacent digits of the integer at most k times.
 *
 * Return the minimum integer you can obtain also as a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: num = "4321", k = 4
 * Output: "1342"
 * Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
 * Example 2:
 *
 * Input: num = "100", k = 1
 * Output: "010"
 * Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
 * Example 3:
 *
 * Input: num = "36789", k = 1000
 * Output: "36789"
 * Explanation: We can keep the number without any swaps.
 * Example 4:
 *
 * Input: num = "22", k = 22
 * Output: "22"
 * Example 5:
 *
 * Input: num = "9438957234785635408", k = 23
 * Output: "0345989723478563548"
 *
 * 用贪心尽可能移动小的数字到最前，时间复杂度n^2，会造成TLE
 * Fenwick tree，aka BIT，求前缀和
 *
 * lowbit: x&-x
 */
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {
    public String minInteger(String num, int k) {
        Queue<Integer>[] queues = new Queue[10];
        for (int i = 0; i < 10; i++) {
            queues[i] = new LinkedList<>();
        }
        for (int i = 0; i < num.length(); i++) {
            queues[num.charAt(i) - '0'].add(i);
        }
        Bit bit = new Bit(num.length() + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            for (int j = 0; j < 10; j++) {
                if (queues[j].isEmpty()) continue;
                int index = queues[j].peek();
                int preSum = bit.query(index + 1);
                if (k - index + preSum >= 0) {
                    k -= index - preSum;
                    sb.append(num.charAt(index));
                    bit.update(index + 1, 1);
                    queues[j].remove();
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static class Bit {
        int[] array;

        public Bit(int length) {
            array = new int[length + 1];
        }

        public void update(int x, int v) {
            for (; x < array.length; x += x & -x) {
                array[x] += v;
            }
        }

        public int query(int x) {
            int sum = 0;
            for (; x > 0; x -= x & -x) {
                sum += array[x];
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits m = new MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits();
        System.out.println(m.minInteger("9438957234785635408", 23));
    }
}
