package gdsctest.KiểmTraPalindrome;

import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int r = 0; r < t; r++) {
            int n = sc.nextInt();
            sc.nextLine();
            String s = sc.nextLine();
            int[] cnt = new int[26];
            for (int i = 0; i < n; i++) {
                cnt[s.charAt(i) - 'a']++;
            }
            int odd = 0;
            for (int c : cnt)
                if (c % 2 != 0)
                    odd++;
            if (odd <= 1)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}