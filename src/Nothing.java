public class Nothing {
    public static void main(String[] args) {
        for (int i=0; i<=200; i++) {
            if (i % 7 == 0)
                System.out.print(i + " ");
            if (i == 100)
                System.out.println();
        }
        System.out.println("");
        System.out.println(92 % 7);
    }
}