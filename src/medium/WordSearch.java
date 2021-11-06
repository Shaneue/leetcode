package medium;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * 时间最快，空间复杂度太高
 */
public class WordSearch {
    int[] direction = new int[]{1, 0, -1, 0, 1};

    public boolean search(char[][] matrix, String word) {
        boolean[][] used = new boolean[matrix.length][matrix[0].length];
        return solve(matrix, used, word, 0, -1, -1);
    }

    public boolean solve(char[][] matrix, boolean[][] used, String target, int idx, int x, int y) {
        if (idx > target.length() - 1)
            return true;
        if (x == -1) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == target.charAt(idx)) {
                        used[i][j] = true;
                        if (solve(matrix, used, target, idx + 1, i, j)) {
                            return true;
                        }
                        used[i][j] = false;
                    }
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int xt = x + direction[i];
                int yt = y + direction[i + 1];
                if (xt > -1 && xt < matrix.length && yt > -1 && yt < matrix[0].length && matrix[xt][yt] == target.charAt(idx)) {
                    used[xt][yt] = true;
                    if (solve(matrix, used, target, idx + 1, xt, yt)) return true;
                    used[xt][yt] = false;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'O', 'U'}, {'E', 'T', 'R', 'T'}, {'D', 'D', 'A', 'Y'}};
        System.out.println(new WordSearch().search(board, "DAY"));
        System.out.println(new WordSearch().search(board, "ABORTED"));
        System.out.println(new WordSearch().search(board, "TRY"));
    }
}
