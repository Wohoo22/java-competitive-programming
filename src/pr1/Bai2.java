package pr1;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;
        System.out.println("Years:");
        n = sc.nextInt();
        System.out.println("Money:");
        m = sc.nextInt();
        System.out.println("Rate:");
        double k = Double.parseDouble(sc.next());
        System.out.print("After " + n + " years, u will receive ");
        System.out.printf("%.2f", calc(n, m, k));
    }

    static Double calc(int years, int money, double rate) {
        double currentMoney = money;
        for (int i = 0; i < years; i++) {
            currentMoney += (currentMoney / 100) * rate;
        }
        return currentMoney;
    }
}
