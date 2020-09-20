package contest;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a string text of words that are placed among some number of spaces. Each word consists of one or more lowercase English letters and are separated by at least one space. It's guaranteed that text contains at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end, meaning the returned string should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "  this   is  a sentence "
 * Output: "this   is   a   sentence"
 * Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between the words: 9 / (4-1) = 3 spaces.
 * Example 2:
 *
 * Input: text = " practice   makes   perfect"
 * Output: "practice   makes   perfect "
 * Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space. We place this extra space at the end of the string.
 * Example 3:
 *
 * Input: text = "hello   world"
 * Output: "hello   world"
 * Example 4:
 *
 * Input: text = "  walks  udp package   into  bar a"
 * Output:       "walks  udp  package  into  bar  a "
 * Example 5:
 *
 * Input: text = "a"
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= text.length <= 100
 * text consists of lowercase English letters and ' '.
 * text contains at least one word.
 */
public class RearrangeSpacesBetweenWords {
    public String reorderSpaces(String text) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                count++;
                if (sb.length() != 0) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        if (sb.length() != 0) list.add(sb.toString());
        int remain, num;
        if (list.size() != 1) {
            remain = count % (list.size() - 1);
            num = count / (list.size() - 1);
        } else {
            remain = count;
            num = 0;
        }
        sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i <= list.size() - 1; i++) {
            for (int j = 0; j < num; j++) {
                sb.append(' ');
            }
            sb.append(list.get(i));
        }
        for (int i = 0; i < remain; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RearrangeSpacesBetweenWords r = new RearrangeSpacesBetweenWords();
        System.out.println(r.reorderSpaces("a"));
        System.out.println(r.reorderSpaces("  walks  udp package   into  bar a"));
        System.out.println(r.reorderSpaces("hello   world"));
        System.out.println(r.reorderSpaces(" practice   makes   perfect"));
    }
}
