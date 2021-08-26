import java.io.*;
import java.util.Random;

public class TestGenerator {
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String base = System.getProperty("user.dir") + "\\src\\gdsctest";

    private static class Config {
        static boolean useOutputFile = true;
        static String outputFile = base + "\\LeoThang\\input\\input02.txt";
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        BufferedWriter writer;
        writer = getConsoleWriter();
        writer = getFileWriter();

        int t = rndInt(46, 46);
        writer.write(t + "\n");

        for (int k = 0; k <= 45; k++) {
            writer.write(k + "\n");
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
