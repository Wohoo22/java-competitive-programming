package pr1;

import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert base in cm:");
        double base = Double.parseDouble(sc.nextLine());
        System.out.println("Insert height in cm:");
        double height =  Double.parseDouble(sc.nextLine());
        System.out.println("The triangle's base is " + base + " (cm) and " + height + " is 1.5 (cm).");
        System.out.println("Its area (cm2) is:");
        System.out.println((base * height) / 2);
    }
}
