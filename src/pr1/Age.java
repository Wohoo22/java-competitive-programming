package pr1;

import java.time.ZonedDateTime;
import java.util.Scanner;

public class Age {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert the year in which you were born:");
        int bornYear = sc.nextInt();
        int currentYear = ZonedDateTime.now().getYear();
        System.out.println("I was born in " + bornYear + ". This year is " + currentYear + ".");
        System.out.println("Therefore, my age is:");
        System.out.println(currentYear - bornYear);
    }
}
