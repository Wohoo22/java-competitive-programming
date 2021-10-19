package datastructure;

public class KMP {
    static int calculateLps(String s) {
        int n = s.length();
        int[] lps = new int[n];
        lps[0] = -1;
        for (int i = 1; i < n; i++) {
            if (i == n / 2) {
                lps[i] = -1;
            } else if (s.charAt(i) == s.charAt(lps[i - 1] + 1)) {
                lps[i] = lps[i - 1] + 1;
            } else {
                int j = -1;
                if (lps[i - 1] != j) j = lps[lps[i - 1]];
                lps[i] = -1;
                if (s.charAt(i) == s.charAt(j + 1)) lps[i] = j + 1;
            }
        }
        return lps[n - 1];
    }
}
