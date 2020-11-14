package contest.hard;

/**
 * Bob is standing at cell (0, 0), and he wants to reach destination: (row, column). He can only travel right and down. You are going to help Bob by providing instructions for him to reach destination.
 *
 * The instructions are represented as a string, where each character is either:
 *
 * 'H', meaning move horizontally (go right), or
 * 'V', meaning move vertically (go down).
 * Multiple instructions will lead Bob to destination. For example, if destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.
 *
 * However, Bob is very picky. Bob has a lucky number k, and he wants the kth lexicographically smallest instructions that will lead him to destination. k is 1-indexed.
 *
 * Given an integer array destination and an integer k, return the kth lexicographically smallest instructions that will take Bob to destination.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: destination = [2,3], k = 1
 * Output: "HHHVV"
 * Explanation: All the instructions that reach (2, 3) in lexicographic order are as follows:
 * ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
 * Example 2:
 *
 *
 *
 * Input: destination = [2,3], k = 2
 * Output: "HHVHV"
 * Example 3:
 *
 *
 *
 * Input: destination = [2,3], k = 3
 * Output: "HHVVH"
 *
 *
 * Constraints:
 *
 * destination.length == 2
 * 1 <= row, column <= 15
 * 1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b​​​​​.
 */
public class KthSmallestInstructions {

    public String kthSmallestPath(int[] destination, int k) {
        int h = destination[1];
        int v = destination[0];
        int[][] combine = getComb(h + v);
        StringBuilder sb = new StringBuilder();
        while (k > 0 && sb.length() < destination[0] + destination[1]) {
            if (v == 0 || k <= combine[h + v - 1][v]) {
                sb.append('H');
                h--;
            } else {
                k -= combine[h + v - 1][v];
                sb.append('V');
                v--;
            }
        }
        return sb.toString();
    }

    private static int[][] getComb(int n) {
        int[][] c = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) c[i][0] = c[i][i] = 1;
        for (int i = 2; i <= n; i++)
            for (int j = 1; j < i; j++)
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]);
        return c;
    }

    public static void main(String[] args) {
        KthSmallestInstructions k = new KthSmallestInstructions();
        System.out.println(k.kthSmallestPath(new int[]{2, 3}, 2));
        System.out.println(k.kthSmallestPath(new int[]{2, 3}, 1));
        System.out.println(k.kthSmallestPath(new int[]{15, 2}, 136));
        System.out.println(k.kthSmallestPath(new int[]{2, 3}, 3));
    }
}
