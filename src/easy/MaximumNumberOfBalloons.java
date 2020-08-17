package easy;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * <p>
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 * <p>
 * Example 1:
 * <p>
 * Input: text = "nlaebolko"
 * Output: 1
 * Example 2:
 * <p>
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 * <p>
 * Input: text = "leetcode"
 * Output: 0
 * <p>
 * Constraints:
 * <p>
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 */
public class MaximumNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        int b = 0;
        int a = 0;
        int l = 0;
        int o = 0;
        int n = 0;
        for (char c : text.toCharArray()) {
            if (c == 'b') b++;
            if (c == 'a') a++;
            if (c == 'l') l++;
            if (c == 'o') o++;
            if (c == 'n') n++;
        }
        return Math.min(b, Math.min(a, Math.min(l / 2, Math.min(o / 2, n))));
    }

    public static void main(String[] args) {
        System.out.println(new MaximumNumberOfBalloons().maxNumberOfBalloons("loonbalxballpoon"));
    }
}
