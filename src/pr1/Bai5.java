package pr1;

import java.util.Scanner;

public class Bai5 {
    public static void main(String[] args) {
        System.out.println("Enter a number:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(n + "th fibonacci number is " + fib(n));
    }

    static int fib(int n) {
        if (n == 0) return 0;
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++)
            f[i] = f[i - 1] + f[i - 2];
        return f[n];
    }
}
