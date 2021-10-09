import java.util.*;

class Leetcode {
    public static void main(String[] args) {
        List<String> l = wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat"));
        for (String w : l)
            System.out.println(w);
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        for (String w : wordDict) dict.add(w);
        ArrayList<String>[] dp = new ArrayList[s.length()];
        for (int i = 0; i < s.length(); i++) dp[i] = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String w = s.substring(i, j + 1);
                if (dict.contains(w)) {
                    if (i > 0)
                        for (String p : dp[i - 1])
                            dp[j].add(p + " " + w);
                    else
                        dp[j].add(w);
                }
            }
        }
        return dp[s.length() - 1];
    }
}