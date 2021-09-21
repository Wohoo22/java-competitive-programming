package pr1;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class Money {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the money in VND:");
        int vnd = sc.nextInt();
        System.out.println("The amount of JPY for " + vnd + " VND is:");
        System.out.println(vnd / (double)207);
    }
}
