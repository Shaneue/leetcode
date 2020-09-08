package medium;

import java.util.Arrays;

/**
 * Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
 *
 * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
 * Example 2:
 *
 * Input: ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 * Example 3:
 *
 * Input: ["a==b","b==c","a==c"]
 * Output: true
 * Example 4:
 *
 * Input: ["a==b","b!=c","c==a"]
 * Output: false
 * Example 5:
 *
 * Input: ["c==c","b==d","x!=z"]
 * Output: true
 *
 *
 * Note:
 *
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] and equations[i][3] are lowercase letters
 * equations[i][1] is either '=' or '!'
 * equations[i][2] is '='
 */
public class SatisfiabilityOfEqualityEquations {
    int[] f = new int[26];

    public boolean equationsPossible(String[] equations) {
        Arrays.sort(equations, (a, b) -> Character.compare(b.charAt(1), a.charAt(1)));
        for (int i = 0; i < 26; i++) {
            f[i] = i;
        }
        for (String e : equations) {
            if (e.charAt(1) == '=') {
                union(f[e.charAt(0) - 'a'], f[e.charAt(3) - 'a']);
            } else {
                int rootA = root(e.charAt(0) - 'a');
                int rootB = root(e.charAt(3) - 'a');
                if (rootA == rootB) return false;
            }
        }
        return true;
    }

    void union(int x, int y) {
        int rootX = root(x);
        int rootY = root(y);
        if (rootX != rootY) {
            f[rootX] = f[rootY];
        }
    }

    int root(int x) {
        if (x != f[x]) {
            f[x] = root(f[x]);
        }
        return f[x];
    }

    public static void main(String[] args) {
        SatisfiabilityOfEqualityEquations s = new SatisfiabilityOfEqualityEquations();
        System.out.println(s.equationsPossible(new String[]{"a==b", "b!=c", "c==a"}));
    }
}
