package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return 1 - n in lexicographical order.
 *
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 *
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 *
 * 有三种情况
 * 1、加0小等于n
 * 2、末尾不为9且加1小于等于n
 * 3、current末尾去9或者current已经比n大了也要取消末尾且加1
 *
 * 还是dfs最易理解
 */
public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 1; i < 10; i++) {
            dfs(i, n, ret);
        }
        return ret;
    }

    private void dfs(int current, int n, List<Integer> ret) {
        if (current > n) return;
        ret.add(current);
        for (int i = 0; i < 10; i++) {
            int t = current * 10 + i;
            dfs(t, n, ret);
        }
    }

    public List<Integer> lexicalOrderNoDfs(int n) {
        List<Integer> list = new ArrayList<>(n);
        int current = 1;
        for (int i = 1; i <= n; i++) {
            list.add(current);
            if (current * 10 <= n) {
                current *= 10;
            } else if (current % 10 != 9 && current + 1 <= n) {
                current++;
            } else {
                while ((current / 10) % 10 == 9) {
                    current /= 10;
                }
                current = current / 10 + 1;
            }
        }
        return list;
    }


    public static void main(String[] args) {
        LexicographicalNumbers l = new LexicographicalNumbers();
        System.out.println(l.lexicalOrder(25));
    }
}
