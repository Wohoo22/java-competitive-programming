package pr1;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = sc.nextInt();
        System.out.println("Binary form: " + toBinary(n));
    }

    static String toBinary(int n) {
        int cnt = 0;
        int tmp = n;
        while (tmp != 0) {
            cnt++;
            tmp = tmp >> 1;
        }
        StringBuilder res = new StringBuilder();
        for (int k = cnt; k >= 1; k--)
            if ((n & (1 << (k - 1))) > 0) res.append(1);
            else res.append(0);
        return res.toString();
    }
}
