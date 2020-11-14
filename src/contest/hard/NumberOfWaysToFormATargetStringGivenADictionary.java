package contest.hard;

/**
 * You are given a list of strings of the same length words and a string target.
 *
 * Your task is to form target using the given words under the following rules:
 *
 * target should be formed from left to right.
 * To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
 * Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
 * Repeat the process until you form the string target.
 * Notice that you can use multiple characters from the same string in words provided the conditions above are met.
 *
 * Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["acca","bbbb","caca"], target = "aba"
 * Output: 6
 * Explanation: There are 6 ways to form target.
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
 * Example 2:
 *
 * Input: words = ["abba","baab"], target = "bab"
 * Output: 4
 * Explanation: There are 4 ways to form target.
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
 * "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
 * "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 * Example 3:
 *
 * Input: words = ["abcd"], target = "abcd"
 * Output: 1
 * Example 4:
 *
 * Input: words = ["abab","baba","abba","baab"], target = "abba"
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * All strings in words have the same length.
 * 1 <= target.length <= 1000
 * words[i] and target contain only lowercase English letters.
 */
public class NumberOfWaysToFormATargetStringGivenADictionary {
    public int numWays(String[] words, String target) {
        int mod = (int) (1e9 + 7);
        long[][] dp = new long[words[0].length()][target.length()];
        int[][] count = new int[words[0].length()][128];
        for (String word : words) {
            for (int j = 0; j < words[0].length(); j++) {
                count[j][word.charAt(j)]++;
            }
        }
        for (int j = 0; j < target.length(); j++) {
            for (int i = j; i < words[0].length(); i++) {
                dp[i][j] = ((i == 0 ? 0 : dp[i - 1][j]) + (j == 0 ? 1 : dp[i - 1][j - 1]) * count[i][target.charAt(j)]) % mod;
            }
        }
        return (int) (dp[words[0].length() - 1][target.length() - 1]);
    }

    public static void main(String[] args) {
        NumberOfWaysToFormATargetStringGivenADictionary n = new NumberOfWaysToFormATargetStringGivenADictionary();
        System.out.println(n.numWays(new String[]{
                        "abba",
                        "baab"
                },
                "bab"));
        System.out.println(n.numWays(new String[]{
                        "acca",
                        "bbbb",
                        "caca"},
                "aba"));
    }
}
