package pr1;

import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args) {
        System.out.println("Enter 9 digits:");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.length() != 9) {
            System.out.println("Invalid input.");
            return;
        }
        int lst = 0;
        for (int i = 0; i < 9; i++) lst += (s.charAt(i) - '0') * (i + 1);
        lst %= 11;
        String lstStr = lst == 10 ? "X" : lst + "";
        System.out.println(s + lstStr);
    }
}
