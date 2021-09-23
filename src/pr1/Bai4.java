package pr1;

public class Bai4 {
    public static void main(String[] args) {
        float num = -1000000.1F;
        if (num == 0) {
            System.out.println("The number is zero");
            return;
        }
        String result = "A ";

        float abs = Math.abs(num);
        if (abs < 1) result += "small ";
        if (abs > 1000000) result += "large ";

        if (num < 0) result += "negative ";
        else result += "positive ";

        result += "number";
        System.out.println(result);
    }
}
