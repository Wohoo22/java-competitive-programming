package pr1;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter year:");
        int year = sc.nextInt();
        String leap = isLeap(year) ? "" : " not";
        System.out.println("The year " + year + " is" + leap + " a leap year.");
    }

    static boolean isLeap(int year) {
        if (year % 4 != 0) return false;
        if (year % 100 != 0) return true;
        return year % 400 == 0;
    }
}
