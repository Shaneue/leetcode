package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
 *
 * You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.
 *
 * Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 *
 * In case there is no path, return [0, 0].
 *
 *
 *
 * Example 1:
 *
 * Input: board = ["E23",
 *                 "2X2",
 *                 "12S"]
 * Output: [7,1]
 * Example 2:
 *
 * Input: board = ["E12",
 *                 "1X1",
 *                 "21S"]
 * Output: [4,2]
 * Example 3:
 *
 * Input: board = ["E11",
 *                 "XXX",
 *                 "11S"]
 * Output: [0,0]
 *
 *
 * Constraints:
 *
 * 2 <= board.length == board[i].length <= 100
 *
 * dp[x][y]=dp[x+1][y]+dp[x][y+1]+dp[x+1][y+1]
 */
public class NumberOfPathsWithMaxScore {
    public int[] pathsWithMaxScore(List<String> board) {
        int lenX = board.size();
        int lenY = board.get(0).length();
        int[][][] dp = new int[lenX][lenY][2];
        dp[lenX - 1][lenY - 1][1] = 1;
        for (int x = lenX - 1; x >= 0; x--) {
            for (int y = lenY - 1; y >= 0; y--) {
                char c = board.get(x).charAt(y);
                if (c == 'X' || x == lenX - 1 && y == lenY - 1) continue;
                int score = 0;
                long count = 0;
                if (x + 1 < lenX && dp[x + 1][y][1] != 0) {
                    int t = dp[x + 1][y][0] + (c == 'E' ? '0' : c) - '0';
                    if (score == t) count = count + dp[x + 1][y][1];
                    if (score < t) {
                        score = t;
                        count = dp[x + 1][y][1];
                    }
                }
                if (y + 1 < lenX && dp[x][y + 1][1] != 0) {
                    int t = dp[x][y + 1][0] + (c == 'E' ? '0' : c) - '0';
                    if (score == t) count = count + dp[x][y + 1][1];
                    if (score < t) {
                        score = t;
                        count = dp[x][y + 1][1];
                    }
                }
                if (y + 1 < lenX && x + 1 < lenY && dp[x + 1][y + 1][1] != 0) {
                    int t = dp[x + 1][y + 1][0] + (c == 'E' ? '0' : c) - '0';
                    if (score == t) count = count + dp[x + 1][y + 1][1];
                    if (score < t) {
                        score = t;
                        count = dp[x + 1][y + 1][1];
                    }
                }
                dp[x][y][0] = score;
                dp[x][y][1] = (int) (count % (1e9 + 7));
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        NumberOfPathsWithMaxScore n = new NumberOfPathsWithMaxScore();
        List<String> list = new ArrayList<>();
        list.add("E11");
        list.add("XXX");
        list.add("11S");
        int[] r = n.pathsWithMaxScore(list);
        list.clear();
        list.add("E12");
        list.add("1X1");
        list.add("21S");
        r = n.pathsWithMaxScore(list);
        System.out.println();

    }
}
