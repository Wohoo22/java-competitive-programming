package pr1;

import java.util.Random;

public class Bai5 {
    public static void main(String[] args) {
        String[] s = new String[]{
                "",
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };
        System.out.println(
                s[new Random().nextInt(11) + 1]
        );
    }
}
