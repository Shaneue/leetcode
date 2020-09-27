package hard;

/**
 * Given a m * n matrix seats that represent seats distributions in a classroom. If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.' character.
 *
 * Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sitting directly in front or behind him. Return the maximum number of students that can take the exam together without any cheating being possible..
 *
 * Students must be placed in seats in good condition.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: seats = [['#','.','#','#','.','#'],
 *                 ['.','#','#','#','#','.'],
 *                 ['#','.','#','#','.','#']]
 * Output: 4
 * Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
 * Example 2:
 *
 * Input: seats = [['.','#'],
 *                 ['#','#'],
 *                 ['#','.'],
 *                 ['#','#'],
 *                 ['.','#']]
 * Output: 3
 * Explanation: Place all students in available seats.
 *
 * Example 3:
 *
 * Input: seats = [['#','.','.','.','#'],
 *                 ['.','#','.','#','.'],
 *                 ['.','.','#','.','.'],
 *                 ['.','#','.','#','.'],
 *                 ['#','.','.','.','#']]
 * Output: 10
 * Explanation: Place students in available seats in column 1, 3 and 5.
 *
 *
 * Constraints:
 *
 * seats contains only characters '.' and'#'.
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 *
 * dp，状态压缩
 * dp[i][j] = max{dp[i-1][k]+bitCount[j]}
 *
 * dp并不是最优解，用状态压缩，dfs效率最快
 */
public class MaximumStudentsTakingExam {
    public int maxStudents(char[][] seats) {
        int l = 1 << seats[0].length;
        int[][] dp = new int[seats.length][l];
        int[] nums = new int[seats.length];
        for (int i = 0; i < seats.length; i++) {
            nums[i] = Integer.parseInt(new String(seats[i]).replace('#', '1').replace('.', '0'), 2);
        }
        for (int i = 0; i < l; i++)
            if ((nums[0] & i) == 0 && !Integer.toString(i, 2).contains("11")) dp[0][i] = Integer.bitCount(i);
        for (int i = 1; i < seats.length; i++) {
            for (int j = 0; j < l; j++) {
                if ((j & nums[i]) == 0 && !Integer.toString(j, 2).contains("11")) {
                    for (int k = 0; k < l; k++)
                        if (((k >> 1) & j) == 0 && ((k << 1) & j) == 0)
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Integer.bitCount(j));
                }
            }
        }
        int ret = 0;
        for (int i : dp[seats.length - 1]) ret = Math.max(ret, i);
        return ret;
    }

    public static void main(String[] args) {
        MaximumStudentsTakingExam m = new MaximumStudentsTakingExam();
        System.out.println(m.maxStudents(new char[][]{
                {'.', '.', '#', '#'},
                {'.', '#', '.', '.'},
                {'#', '.', '.', '#'},
                {'#', '#', '#', '.'}}));
    }

}
