package contest;

/**
 * You are given two strings a and b of the same length. Choose an index and split both strings at the same index, splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings: bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
 *
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty. For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 *
 * Return true if it is possible to form a palindrome string, otherwise return false.
 *
 * Notice that x + y denotes the concatenation of strings x and y.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "x", b = "y"
 * Output: true
 * Explaination: If either a or b are palindromes the answer is true since you can split in the following way:
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * Then, aprefix + bsuffix = "" + "y" = "y", which is a palindrome.
 * Example 2:
 *
 * Input: a = "abdef", b = "fecab"
 * Output: false
 * Example 3:
 *
 * Input: a = "ulacfd", b = "jizalu"
 * Output: true
 * Explaination: Split them at index 3:
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 10^5
 * a.length == b.length
 * a and b consist of lowercase English letters
 */
public class SplitTwoStringsToMakePalindrome {
    public boolean checkPalindromeFormation(String a, String b) {
        if (isPalindrome(a) || isPalindrome(b)) return true;
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();
        int l = 0, r = a.length() - 1;
        while (l < r && aChars[l] == bChars[r]) {
            l++;
            r--;
        }
        if (isPalindrome(b.substring(l, r + 1)) || isPalindrome(a.substring(l, r + 1))) return true;
        l = 0;
        r = a.length() - 1;
        while (l < r && bChars[l] == aChars[r]) {
            l++;
            r--;
        }
        return isPalindrome(b.substring(l, r + 1)) || isPalindrome(a.substring(l, r + 1));
    }

    boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SplitTwoStringsToMakePalindrome s = new SplitTwoStringsToMakePalindrome();
        System.out.println(s.checkPalindromeFormation("aejbaalflrmkswrydwdkdwdyrwskmrlfqizjezd",
                "uvebspqckawkhbrtlqwblfwzfptanhiglaabjea"));
        System.out.println(s.checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp"));
        System.out.println(s.checkPalindromeFormation("x", "y"));
        System.out.println(s.checkPalindromeFormation("abdef", "fecab"));
        System.out.println(s.checkPalindromeFormation("ulacfd", "jizalu"));
    }
}
