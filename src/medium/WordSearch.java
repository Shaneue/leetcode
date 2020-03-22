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
    public boolean exist(char[][] board, String word) {
        boolean[][] occupied = new boolean[board.length][board[0].length];
        return solve(board, occupied, word, 0, -1, -1);
    }

    public boolean solve(char[][] array, boolean[][] occupied, String target, int index, int x, int y) {
        if (index > target.length() - 1)
            return true;
        if (x == -1) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    if (array[i][j] == target.charAt(index)) {
                        occupied[i][j] = true;
                        if (solve(array, occupied, target, index + 1, i, j)) {
                            return true;
                        }
                        occupied[i][j] = false;
                    }
                }
            }
        } else {
            if (x - 1 > -1 && !occupied[x - 1][y] && array[x - 1][y] == target.charAt(index)) {
                occupied[x - 1][y] = true;
                if (solve(array, occupied, target, index + 1, x - 1, y)) return true;
                occupied[x - 1][y] = false;
            }
            if (x + 1 < array.length && !occupied[x + 1][y] && array[x + 1][y] == target.charAt(index)) {
                occupied[x + 1][y] = true;
                if (solve(array, occupied, target, index + 1, x + 1, y)) return true;
                occupied[x + 1][y] = false;
            }
            if (y - 1 > -1 && !occupied[x][y - 1] && array[x][y - 1] == target.charAt(index)) {
                occupied[x][y - 1] = true;
                if (solve(array, occupied, target, index + 1, x, y - 1)) return true;
                occupied[x][y - 1] = false;
            }
            if (y + 1 < array[0].length && !occupied[x][y + 1] && array[x][y + 1] == target.charAt(index)) {
                occupied[x][y + 1] = true;
                if (solve(array, occupied, target, index + 1, x, y + 1)) return true;
                occupied[x][y + 1] = false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}};
        System.out.println(new WordSearch().exist(board, "aaaaaaaaaaaaa"));
    }
}
