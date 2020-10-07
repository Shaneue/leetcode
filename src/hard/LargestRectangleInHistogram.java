package hard;

import java.util.LinkedList;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * 末尾补一个dummy元素，单调队列
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int[] arr = new int[heights.length + 1];
        System.arraycopy(heights, 0, arr, 0, heights.length);
        arr[arr.length - 1] = -1;
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int ret = 0;
        for (int i = 0; i < arr.length; i++) {
            while (stack.peek() != -1 && arr[stack.peek()] > arr[i]) {
                int h = arr[stack.pop()];
                ret = Math.max(ret, h * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();
        System.out.println(l.largestRectangleArea(new int[]{2, 1, 1, 1, 1}));
    }
}
