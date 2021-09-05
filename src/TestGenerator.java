import java.io.*;
import java.util.Random;

public class TestGenerator {
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";

    private static class Config {
        static boolean useOutputFile = true;
        static String outputFile = checkerInput;
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        BufferedWriter writer;
        writer = getWriter();

        int t = rndInt(1, 100);
        writer.write(t + "\n");
        for (int v = 0; v < t; v++) {
            int n = rndInt(1, 20);
            int k = rndInt(1, 20);
            writer.write(n + " " + k + "\n");
            for (int i = 0; i < n; i++) {
                writer.write(rndInt(-1000, 1000) + " ");
            }
            writer.write("\n");
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
