import java.util.Stack;

public class Nothing {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(
                ")()())"
        ));
    }

    public static int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        int[] pref = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (!st.isEmpty()) {
                    int j = st.pop();
                    pref[i] = i - j + 1;
                    if (j > 0) pref[i] += pref[j - 1];
                }
            } else {
                st.push(i);
            }
            max = Math.max(max, pref[i]);
            ans[i] = max;
        }
        return ans[n - 1];
    }
}