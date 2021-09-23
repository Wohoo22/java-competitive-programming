package pr1;

import java.util.Scanner;

public class Bai6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter year:"); int year = sc.nextInt();
        System.out.println("Enter month:"); int month = sc.nextInt();
        System.out.println("Enter the day of the month:"); int dayOfTheMonth = sc.nextInt();

        int q, m, j, k;
        q = dayOfTheMonth;
        m = month < 3 ? 12 + month : month;
        if (month < 3) year--;
        j = year / 100;
        k = year % 100;

        int result = q + 26 * (m + 1) / 10 + k + k / 4 + j / 4 + 5 * j;
        result %= 7;

        String[] f = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        System.out.println(f[result]);
    }
}
