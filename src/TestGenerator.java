import java.io.*;
import java.util.Random;

public class TestGenerator {
    private static final String checkerSolutionOutput = "D:\\Work\\work-space\\CP\\src\\_checker.solution.out";
    private static final String checkerInput = "D:\\Work\\work-space\\CP\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = "D:\\Work\\work-space\\CP\\src\\_checker.bruteforces.out";
    private static final String fileInput = "D:\\Work\\work-space\\CP\\src\\_in";
    private static final String fileOutput = "D:\\Work\\work-space\\CP\\src\\_in";

    private static class Config {
        static boolean useOutputFile = true;
        static String outputFile = fileInput;
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        BufferedWriter writer;
        writer = getWriter();

        int t = rndInt(20000, 20000);
        writer.write(t + "\n");
        for (int v = 0; v < t; v++) {
            // n
            int n = rndInt(3, 7);
            writer.write(n + "\n");
            // for n
            for (int i = 0; i < n; i++) {
                // k
                int k = rndInt(0, n - 1);
                writer.write(k + " ");
                // for k
                for (int j = 0; j < k; j++) {
                    // f
                    int f = rndInt(1, n);
                    while (f == i + 1)
                        f = rndInt(1, n);
                    writer.write(f + " ");
                }
                writer.write("\n");
            }
        }

        writer.flush();
    }

    private static char rndChar() {
        return (char) ('a' + rndInt(0, 25));
    }

    private static int rndInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    private static BufferedWriter getWriter() throws Exception {
        if (Config.useOutputFile)
            return getFileWriter();
        return getConsoleWriter();
    }

    private static BufferedWriter getFileWriter() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(Config.outputFile);
        writer.print("");
        writer.close();
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.outputFile)));
    }

    private static BufferedWriter getConsoleWriter() {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }


}
