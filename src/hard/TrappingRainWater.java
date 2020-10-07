package hard;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * 注意要定义maxL, maxR
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int l = 0, r = height.length - 1;
        int ret = 0;
        int maxL = 0, maxR = 0;
        while (l < r) {
            if (height[l] > height[r]) {
                maxR = Math.max(maxR, height[r]);
                ret += maxR - height[r--];
            } else {
                maxL = Math.max(maxL, height[l]);
                ret += maxL - height[l++];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        System.out.println(t.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
