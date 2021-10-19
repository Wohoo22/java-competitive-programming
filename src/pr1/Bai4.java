package pr1;

import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number:");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) System.out.print("*");
            System.out.print(" ");
            for (int j = 0; j < n; j++)
                if (j == 0 || j == n - 1 || i == 0 || i == n - 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            System.out.println("");
        }
    }
}
