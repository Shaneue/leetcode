package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a MapSum class with insert, and sum methods.
 *
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 *
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 *
 * 前缀树
 * 用map存也可以，代码会比较简单
 */
public class MapSumPairs {
    static class TrieNode {
        int val;
        TrieNode[] children;

        public TrieNode(int val) {
            this.val = val;
        }
    }

    Map<String, Integer> map = new HashMap<>();
    TrieNode trie = new TrieNode(0);

    public MapSumPairs() {

    }

    public void insert(String key, int val) {
        int diff = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode t = trie;
        for (char c : key.toCharArray()) {
            if (t.children == null) t.children = new TrieNode[128];
            if (t.children[c] == null) t.children[c] = new TrieNode(0);
            t.children[c].val += diff;
            t = t.children[c];
        }
    }

    public int sum(String prefix) {
        TrieNode t = trie;
        for (char c : prefix.toCharArray()) {
            if (t.children == null || t.children[c] == null) return 0;
            t = t.children[c];
        }
        return t.val;
    }

    public static void main(String[] args) {
        MapSumPairs m = new MapSumPairs();
        m.insert("aaa", 1);
        m.insert("aaa", 2);
        m.insert("aaab", 2);
        m.insert("aab", 4);
        System.out.println(m.sum("aa"));
    }
}
