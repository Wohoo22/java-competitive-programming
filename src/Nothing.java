import java.math.BigInteger;

public class Nothing {
    public static void main(String[] args) {
        BigInteger first = new BigInteger("1000");
        System.out.println("First old " + first);
        BigInteger second = first.add(new BigInteger("50"));
        System.out.println("Second old " + second);
        System.out.println("First new " + first);
    }
}