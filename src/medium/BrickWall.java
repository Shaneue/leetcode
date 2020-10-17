package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
 * <p>
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
 * <p>
 * If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
 * <p>
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 * <p>
 * Example:
 * <p>
 * Input: [[1,2,2,1],
 * [3,1,2],
 * [1,3,2],
 * [2,4],
 * [3,1,2],
 * [1,3,1,1]]
 * <p>
 * Output: 2
 * <p>
 * Note:
 * <p>
 * The width sum of bricks in different rows are the same and won't exceed INT_MAX.
 * The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
 *
 * 等价于最多能穿过多少条边
 */
public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int ret = Integer.MIN_VALUE;
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                int c = sumMap.getOrDefault(sum, 0);
                c++;
                sumMap.put(sum, c);
                ret = Math.max(ret, c);
            }
        }
        return ret == Integer.MIN_VALUE ? wall.size() : wall.size() - ret;
    }

    public static void main(String[] args) {
        BrickWall b = new BrickWall();
        System.out.println(b.leastBricks(Arrays.asList(Arrays.asList(1, 2, 2, 1), Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 2), Arrays.asList(2, 4), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 1, 1))));
    }
}
