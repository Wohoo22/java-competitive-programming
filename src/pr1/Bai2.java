package pr1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter values: ");
        Scanner line = new Scanner(sc.nextLine());
        sc.close();
        List<Float> nums = new ArrayList<>();
        while (line.hasNextFloat())
            nums.add(line.nextFloat());
        float sum = 0;
        float min = Float.MAX_VALUE, max = Float.MIN_VALUE;
        for (float f : nums) {
            min = Math.min(f, min);
            max = Math.max(f, max);
            sum += f;
        }
        System.out.println("Average: " + sum / nums.size());
        System.out.println("Smallest: " + min);
        System.out.println("Largest: " + max);
        System.out.println("Range: " + (max - min));

    }
}
