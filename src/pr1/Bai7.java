package pr1;

import java.util.Scanner;

public class Bai7 {
    public static void main(String[] args) {
        System.out.println("Enter an integer:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 0) n *= -1;
        int ans = 1;
        for (int i = 10; true; i *= 10) {
            if (i > n) {
                System.out.println("The number of digits is: " + ans);
                return;
            }
            ans++;
        }
    }
}
