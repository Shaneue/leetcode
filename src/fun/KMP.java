package fun;

public class KMP {
    int[] next(char[] p) {
        int[] next = new int[p.length];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < p.length - 1) {
            if (j == -1 || p[i] == p[j]) {
                i++;
                j++;
                if (p[i] != p[j]) next[i] = j;
                else next[i] = next[j];
            } else j = next[j];
        }
        return next;
    }

    int indexOf(String source, String pattern) {
        char[] s = source.toCharArray();
        char[] p = pattern.toCharArray();
        if (s.length < p.length) return -1;
        int i = 0, j = 0;
        int[] next = next(p);
        while (i < s.length) {
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else j = next[j];
            if (j == p.length) break;
        }
        return j == p.length ? i - j : -1;
    }

    public static void main(String[] args) {
        KMP k = new KMP();
        System.out.println(k.indexOf("abcaba", "aba"));
        System.out.println(k.indexOf("aba", "aba"));
        System.out.println(k.indexOf("abc", "aba"));
        System.out.println(k.indexOf("bb", "aa"));
        System.out.println(k.indexOf("aa", "aa"));
    }
}
