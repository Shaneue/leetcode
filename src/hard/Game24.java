package hard;

/**
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 *
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 */
public class Game24 {
    public boolean judgePoint24(int[] nums) {
        return equals24(new double[]{nums[0], nums[1], nums[2], nums[3]});
    }

    boolean equals24(double[] n) {
        if (n.length == 1) return Math.abs(n[0] - 24) < 1e-5;
        for (int i = 0; i < n.length; i++) {
            for (int j = i + 1; j < n.length; j++) {
                double[] list = new double[]{n[i] - n[j], n[j] - n[i], n[i] + n[j], n[i] * n[j], n[i] / n[j], n[j] / n[i]};
                for (double k : list) {
                    double[] b = new double[n.length - 1];
                    int idx = 0;
                    for (int m = 0; m < n.length; m++) {
                        if (m != i && m != j) b[idx++] = n[m];
                    }
                    b[b.length - 1] = k;
                    if (equals24(b)) return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Game24 g = new Game24();
        System.out.println(g.judgePoint24(new int[]{3, 3, 8, 8}));
        System.out.println(g.judgePoint24(new int[]{1, 3, 4, 6}));
    }
}
