import java.io.File;
import java.util.Scanner;

public class Checker {
    public static void main(String[] args) throws Exception {
        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            runTest();
            System.out.println("Test " + (i + 1) + " passed.");
        }
    }

    private static void runTest() throws Exception {
        TestGenerator.run();
        Solution.run();
        Bruteforces.run();
        File bruteforcesOutputFile = new File(System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out");
        File solutionOutputFile = new File(System.getProperty("user.dir") + "\\src\\_checker.solution.out");
        Scanner bruteforcesScanner = new Scanner(bruteforcesOutputFile);
        Scanner solutionScanner = new Scanner(solutionOutputFile);
        check(bruteforcesScanner, solutionScanner);
    }

    private static void check(Scanner bruteforcesScanner, Scanner solutionScanner) throws Bug {
        int line = 1;
        while (bruteforcesScanner.hasNext()) {
            String bruteforcesResult = bruteforcesScanner.nextLine();
            if (!solutionScanner.hasNext())
                throw new Bug("Solution has less line than bruteforces.");
            String solutionResult = solutionScanner.nextLine();
            if (!bruteforcesResult.equals(solutionResult))
                throw new Bug(makeMessage(line, bruteforcesResult, solutionResult));
            line++;
        }
        if (solutionScanner.hasNext()) {
            throw new Bug("Solution has more line than bruteforces.");
        }
    }

    private static String makeMessage(int line, String correct, String wrong) {
        return "Bug found at line " + line + ", expect \"" + correct + "\" but found \"" + wrong + "\".";
    }

    private static class Bug extends Exception {
        public Bug(String message) {
            super(message);
        }
    }
}
