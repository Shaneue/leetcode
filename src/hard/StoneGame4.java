package hard;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there are n stones in a pile.  On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.
 *
 * Also, if a player cannot make a move, he/she loses the game.
 *
 * Given a positive integer n. Return True if and only if Alice wins the game otherwise return False, assuming both players play optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * Example 3:
 *
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 * Example 4:
 *
 * Input: n = 7
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 * If Alice starts removing 4 stones, Bob will remove 1 stone then Alice should remove only 1 stone and finally Bob removes the last one (7 -> 3 -> 2 -> 1 -> 0).
 * If Alice starts removing 1 stone, Bob will remove 4 stones then Alice only can remove 1 stone and finally Bob removes the last one (7 -> 6 -> 2 -> 1 -> 0).
 * Example 5:
 *
 * Input: n = 17
 * Output: false
 * Explanation: Alice can't win the game if Bob plays optimally.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 *
 * 能找到一个false就是true
 * 直接迭代即可
 */
public class StoneGame4 {
    private static int[] dp = new int[100001];

    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean winnerSquareGameRecursively(int n) {
        for (int i = 1; i * i <= n; i++) dp[i * i] = 1;
        return search(n);
    }

    boolean search(int n) {
        if (dp[n] != 0) return dp[n] == 1;
        boolean ret = true;
        for (int i = 1; i * i < n; i++) {
            ret &= search(n - i * i);
        }
        dp[n] = ret ? 2 : 1;
        return !ret;
    }

    public static void main(String[] args) {
        StoneGame4 s = new StoneGame4();
        System.out.println(s.winnerSquareGame(8));
        System.out.println(s.winnerSquareGame(3));
        System.out.println(s.winnerSquareGame(2));
        System.out.println(s.winnerSquareGame(4));
        System.out.println(s.winnerSquareGame(7));
        System.out.println(s.winnerSquareGame(17));
    }
}
