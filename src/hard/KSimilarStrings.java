package hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: 1
 * Example 2:
 *
 * Input: A = "abc", B = "bca"
 * Output: 2
 * Example 3:
 *
 * Input: A = "abac", B = "baca"
 * Output: 2
 * Example 4:
 *
 * Input: A = "aabc", B = "abca"
 * Output: 2
 * Note:
 *
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 *
 * 似乎没有好的dp方法，直接一步一步枚举即可
 * 每一步只需要找到第一个不相等的字符
 */
public class KSimilarStrings {
    public int kSimilarity(String A, String B) {
        Queue<String> q = new LinkedList<>();
        Set<String> filter = new HashSet<>();
        filter.add(A);
        q.add(A);
        int l = A.length();
        int ret = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                String s = q.remove();
                if (s.equals(B)) return ret;
                int j = 0;
                while (j < l && s.charAt(j) == B.charAt(j)) j++;
                for (int k = j + 1; k < l; k++) {
                    if (s.charAt(k) == B.charAt(j) && s.charAt(k) != B.charAt(k)) {
                        char[] chars = s.toCharArray();
                        char t = chars[k];
                        chars[k] = chars[j];
                        chars[j] = t;
                        String st = new String(chars);
                        if (!filter.contains(st)) {
                            q.add(st);
                            filter.add(st);
                        }
                    }
                }
            }
            ret++;
        }
        return 0;
    }

    public static void main(String[] args) {
        KSimilarStrings k = new KSimilarStrings();
        System.out.println(k.kSimilarity("abcdeabcdeabcdeabcde", "aaaabbbbccccddddeeee"));
    }
}
