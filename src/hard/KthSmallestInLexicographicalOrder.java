package hard;

/**
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
 *
 * Note: 1 ≤ k ≤ n ≤ 10^9.
 *
 * Example:
 *
 * Input:
 * n: 13   k: 2
 *
 * Output:
 * 10
 *
 * Explanation:
 * The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 */
public class KthSmallestInLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        int ret = 1;
        while (k - 1 > 0) {
            int c = count(ret, ret + 1, n);
            if (k > c) {
                k -= c;
                ret += 1;
            } else {
                ret *= 10;
                k -= 1;
            }
        }
        return ret;
    }

    int count(long from, long to, int n) {
        int ret = 0;
        while (from <= n) {
            long min = Math.min(to, n + 1);
            ret += min - from;
            from *= 10;
            to *= 10;
        }
        return ret;
    }
}
