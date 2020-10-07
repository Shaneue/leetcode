package hard;

/**
 * Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?
 *
 * Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.
 *
 * Example 1:
 * Input: m = 3, n = 3, k = 5
 * Output:
 * Explanation:
 * The Multiplication Table:
 * 1	2	3
 * 2	4	6
 * 3	6	9
 *
 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 * Example 2:
 * Input: m = 2, n = 3, k = 6
 * Output:
 * Explanation:
 * The Multiplication Table:
 * 1	2	3
 * 2	4	6
 *
 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 * Note:
 * The m and n will be in the range [1, 30000].
 * The k will be in the range [1, m * n]
 */
public class KthSmallestNumberInMultiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int l = 1;
        int r = m * n;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int c = 0;
            for (int i = 1; i <= n; i++) {
                c += Math.min(mid / i, m);
            }
            if (c >= k) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        KthSmallestNumberInMultiplicationTable k = new KthSmallestNumberInMultiplicationTable();
        System.out.println(k.findKthNumber(45, 12, 471));
        System.out.println(k.findKthNumber(1, 3, 3));
        System.out.println(k.findKthNumber(1, 1, 1));
        System.out.println(k.findKthNumber(3, 3, 5));
        System.out.println(k.findKthNumber(2, 3, 6));
    }
}
