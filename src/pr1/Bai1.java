package pr1;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        System.out.println("Number of prime numbers " +
                "in range [1000, 2000] is " + countPrimeInRange(1000, 2000));
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = sc.nextInt();
        if (isPrime(n)) System.out.println(n + " is a prime number");
        else System.out.println(n + " is not a prime number");
    }

    private static boolean isPrime(int n) {
        return sieve(n)[n];
    }

    private static int countPrimeInRange(int l, int r) {
        boolean[] p = sieve(r);
        int ans = 0;
        for (int i = l; i <= r; i++)
            if (p[i]) ans++;
        return ans;
    }

    private static boolean[] sieve(int n) {
        boolean[] p = new boolean[n + 1];
        for (int i = 1; i <= n; i++) p[i] = true;
        for (int i = 2; i * i <= n; i++)
            if (p[i])
                for (int j = i * i; j <= n; j += i)
                    p[j] = false;
        return p;
    }
}
