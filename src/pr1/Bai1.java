package pr1;

public class Bai1 {
    public static void main(String[] args) {
        double weightInKg = 50.0;
        int heightInCentimeter = 159;
        System.out.println("My height " + heightInCentimeter);
        System.out.println("Mey weight " + weightInKg);
        System.out.println("My BMI " + (
                weightInKg / (heightInCentimeter * heightInCentimeter)
                ));
    }
}
