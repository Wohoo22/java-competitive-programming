package pr1;

import java.util.Scanner;

public class Bai3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so a:");
        float a = Float.parseFloat(sc.next());
        System.out.println("Nhap so b:");
        float b = Float.parseFloat(sc.next());
        System.out.println("Nhap so c:");
        float c = Float.parseFloat(sc.next());
        System.out.println(solve(a, b, c));
    }

    public static String solve(float a, float b, float c) {
        if (a == 0) {
            if (b == 0)
                return c == 0 ?
                        "The equation has infinite roots." :
                        "The equation has no roots.";
            return "The equation has one roots:\n x = " + (-c / b);
        }
        float delta = b * b - 4 * a * c;
        if (delta < 0) return "The equation has no roots.";
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
        return "The equation has two roots:\n"
                + "x1 = $1, x2 = $2"
                .replace("$1", x1 + "")
                .replace("$2", x2 + "");
    }
}
