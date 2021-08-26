package gdsctest.NénChuỗi;

import java.io.*;
import java.util.Random;

public class TestGenerator {
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String base = System.getProperty("user.dir") + "\\src\\gdsctest";

    private static class Config {
        static boolean useOutputFile = true;
        static String outputFile = base + "\\NénChuỗi\\input\\input04.txt";
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        BufferedWriter writer;
        writer = getConsoleWriter();
        writer = getFileWriter();
        int t = rndInt(1, 1);
        writer.write(t + "\n");

        for (int k = 0; k < t; k++) {
            int n = rndInt(100000, 100000);
            writer.write(n + "\n");

            int r = rndInt(1, 10000);
            if (r % 2 == 0) {
                for (int i = 0; i < n; i++) {
                    writer.write('a' + rndInt(0, 25));
                }
                writer.write("\n");
                continue;
            }

            int i = 0;
            while (i < n) {
                int cnt = rndInt(1, n - i);
                char c = (char) ('a' + rndInt(0, 25));
                for (int j = 0; j < cnt; j++)
                    writer.write(c);
                i += cnt;
            }

            writer.write("\n");
        }
        writer.flush();
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
