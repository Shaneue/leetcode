package medium;

import java.util.Arrays;

/**
 * You have an initial power P, an initial score of 0 points, and a bag of tokens.
 * <p>
 * Each token can be used at most once, has a value token[i], and has potentially two ways to use it.
 * <p>
 * If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
 * If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
 * Return the largest number of points we can have after playing any number of tokens.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: tokens = [100], P = 50
 * Output: 0
 * Example 2:
 * <p>
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * Example 3:
 * <p>
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 * <p>
 * <p>
 * Note:
 * <p>
 * tokens.length <= 1000
 * 0 <= tokens[i] < 10000
 * 0 <= P < 10000
 */
public class BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int l = 0, r = tokens.length - 1, ret = 0, currentPoints = 0;
        while (l <= r) {
            if (P >= tokens[l]) {
                currentPoints++;
                P -= tokens[l];
                l++;
                if (currentPoints > ret) {
                    ret = currentPoints;
                }
            } else if (currentPoints > 0) {
                currentPoints--;
                P += tokens[r];
                r--;
            } else {
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] tokens = new int[]{100, 200, 300, 400};
        int p = 200;
        System.out.println(new BagOfTokens().bagOfTokensScore(tokens, p));
    }
}
