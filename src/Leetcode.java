import java.util.Stack;

public class Leetcode {

    public static void main(String[] args) {
        System.out.println(
                largestRectangleArea(
                        new int[]{
                                2,1,5,6,2,3
                        }
                )
        );
    }

    static class Area {
        int index;
        int value;

        public Area(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // save right and left pivot
        int[] right = new int[n];
        int[] left = new int[n];
        // calculate left right
        Stack<Area> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty()
                    && st.peek().value >= heights[i])
                st.pop();
            if (!st.empty())
                right[i] = st.peek().index;
            else
                right[i] = n;
            st.push(new Area(i, heights[i]));
        }
        st.clear();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty()
                    && st.peek().value >= heights[i])
                st.pop();
            if (!st.empty())
                left[i] = st.peek().index;
            else
                left[i] = -1;
            st.push(new Area(i, heights[i]));
        }
        // calculate ans
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int l = left[i] + 1;
            int r = right[i] - 1;
            ans = Math.max(ans, (r - l + 1) * heights[i]);
        }
        return ans;
    }
}
