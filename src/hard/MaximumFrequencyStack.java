package hard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 *
 * FreqStack has two functions:
 *
 * push(int x), which pushes an integer x onto the stack.
 * pop(), which removes and returns the most frequent element in the stack.
 * If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 *
 *
 * Example 1:
 *
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 *
 *
 * Note:
 *
 * Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 * It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 * The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 * The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 */
public class MaximumFrequencyStack {
    int max = 0;
    Map<Integer, Integer> frequency = new HashMap<>();
    Map<Integer, LinkedList<Integer>> fSet = new HashMap<>();

    public void push(int x) {
        int f = frequency.getOrDefault(x, 0);
        f++;
        max = Math.max(max, f);
        frequency.put(x, f);
        LinkedList<Integer> q = fSet.getOrDefault(f, new LinkedList<>());
        q.push(x);
        fSet.put(f, q);
    }

    public int pop() {
        int x = fSet.get(max).pop();
        frequency.put(x, frequency.get(x) - 1);
        if (fSet.get(max).isEmpty()) max--;
        return x;
    }

    public static void main(String[] args) {
        MaximumFrequencyStack m = new MaximumFrequencyStack();
        m.push(5);
        m.push(7);
        m.push(5);
        m.push(7);
        m.push(4);
        m.push(5);
        System.out.println(m.pop());
        System.out.println(m.pop());
        System.out.println(m.pop());
        System.out.println(m.pop());
    }
}
