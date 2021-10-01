package pr1;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        System.out.println("Give me your age right now ?");
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        if (age < 13) System.out.println("Not for kids");
        else if (age > 19) System.out.println("You are too old");
        else System.out.println("Welcome, teenager");
    }
}
