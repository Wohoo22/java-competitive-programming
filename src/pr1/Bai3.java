package pr1;

public class Bai3 {
    public static void main(String[] args) {
        int xu = 10000;
        int hao = xu / 10;
        int dong = hao / 10;
        int quan = dong / 100;
        System.out.printf(
                "%d quan, %d dong, %d hao, %d xu",
                quan, dong, hao, xu
        );
    }
}
