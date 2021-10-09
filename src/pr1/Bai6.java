package pr1;

public class Bai6 {
    public static void main(String[] args) {
        System.out.println("Amstrong numbers from 100 to 999:");
        for (int i = 100; i <= 999; i++) {
            if (isAmstrong(i))
                System.out.print(i + " ");
        }
    }

    static boolean isAmstrong(int num) {
        String s = String.valueOf(num);
        int ams = 0;
        for (int i = 0; i < s.length(); i++) {
            int v = s.charAt(i) - '0';
            ams += v * v * v;
        }
        return ams == num;
    }
}
