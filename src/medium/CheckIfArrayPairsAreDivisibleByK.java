package medium;

/**
 * Given an array of integers arr of even length n and an integer k.
 *
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 *
 * Return True If you can find a way to do that or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * Example 4:
 *
 * Input: arr = [-10,10], k = 2
 * Output: true
 * Example 5:
 *
 * Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * arr.length == n
 * 1 <= n <= 10^5
 * n is even.
 * -10^9 <= arr[i] <= 10^9
 * 1 <= k <= 10^5
 */
public class CheckIfArrayPairsAreDivisibleByK {
    public boolean canArrange(int[] arr, int k) {
        int[] r = new int[k];
        for (int i : arr) r[(i % k + k) % k]++;
        for (int i = 1; i <= k / 2; i++) if (r[i] != r[k - i]) return false;
        return k % 2 != 0 || r[k / 2] % 2 != 1;
    }

    public static void main(String[] args) {
        CheckIfArrayPairsAreDivisibleByK c = new CheckIfArrayPairsAreDivisibleByK();
        System.out.println(c.canArrange(new int[]{-1, -1, -1, -1, 2, 2, -2, -2}, 3));
    }
}
