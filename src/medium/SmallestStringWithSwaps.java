package medium;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 *
 * 并查集
 */
public class SmallestStringWithSwaps {
    int[] f;

    void init(int n) {
        f = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
        }
    }

    private int root(int i) {
        if (i != f[i]) {
            f[i] = root(f[i]);
        }
        return f[i];
    }

    void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP != rootQ) {
            f[rootP] = f[rootQ];
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        init(s.length());
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }
        char[] chars = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            int root = root(i);
            PriorityQueue<Character> p = map.getOrDefault(root, new PriorityQueue<>());
            p.add(chars[i]);
            map.put(root, p);
        }

        for (int i = 0; i < chars.length; i++) {
            chars[i] = map.get(root(i)).poll();
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        SmallestStringWithSwaps s = new SmallestStringWithSwaps();
        List l1 = Arrays.asList(0, 3);
        List l2 = Arrays.asList(1, 2);
        List l3 = Arrays.asList(l1, l2);
        System.out.println(s.smallestStringWithSwaps("dcab", l3));
    }
}
