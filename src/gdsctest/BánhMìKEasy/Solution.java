package gdsctest.BánhMìKEasy;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] a = new int[n];
            for (int j = 0; j < n; j++)
                a[j] = sc.nextInt();
            Set<Integer> set = new HashSet<>();
            for (int e : a)
                set.add(e);
            int ans = 0;
            for (int e : a)
                if (set.contains(e - k))
                    ans++;
            System.out.println(ans);
        }
    }
}