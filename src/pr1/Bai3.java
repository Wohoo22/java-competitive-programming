package pr1;

import java.util.Arrays;
import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        a(s);
        b(s);
        c(s);
        d(s);
        e(s);
    }

    static void a(String s) {
        System.out.print("(a): ");
        for (int i = 0; i < s.length(); i++)
            if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z')
                System.out.print(s.charAt(i) + ' ');
        System.out.println("");
    }

    static void b(String s) {
        System.out.print("(b): ");
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cnt % 2 == 0) System.out.print(c + " ");
            if (Character.isLetterOrDigit(c)) cnt++;
        }
        System.out.println("");
    }


    static void c(String s) {
        System.out.print("(c): ");
        for (int i = 0; i < s.length(); i++)
           if ("ueoai".contains(s.charAt(i) + "")) System.out.print("_");
           else System.out.print(s.charAt(i));
        System.out.println("");
    }

    static void d(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++)
            if ("ueoai".contains(s.charAt(i) + "")) cnt++;
        System.out.println("(d): " + cnt);
    }

    static void e(String s) {
        System.out.print("(e): ");
        for (int i = 0; i < s.length(); i++)
            if ("ueoai".contains(s.charAt(i) + "")) System.out.print(i + " ");
        System.out.println("");
    }
}
