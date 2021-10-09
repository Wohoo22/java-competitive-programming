package pr1;

import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter number:");
            String number = sc.next();
            int l = 0, r = number.length() - 1;
            while (l < r) {
                if (number.charAt(l) != number.charAt(r)) {
                    System.out.println(number + " is not a palindrome.");
                    return;
                }
                l++;
                r--;
            }
            System.out.println(number + " is a palindrome.");
        }
    }
}
