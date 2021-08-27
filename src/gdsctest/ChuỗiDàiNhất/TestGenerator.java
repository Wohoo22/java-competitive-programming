package gdsctest.ChuỗiDàiNhất;

import java.io.*;
import java.util.Random;

public class TestGenerator {
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String base = System.getProperty("user.dir") + "\\src\\gdsctest";

    private static class Config {
        static boolean useOutputFile = true;
        static String outputFile = base + "\\ChuỗiDàiNhất\\input\\input04.txt";
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        BufferedWriter writer;
        writer = getWriter();

        int t = rndInt(10000, 10000);
        writer.write(t + "\n");

        for (int k = 0; k < t; k++) {

            int n = rndInt(1, 20);
            writer.write(n + "\n");
            for (int i = 0; i < n; i++) {
                writer.write(rndChar());
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
