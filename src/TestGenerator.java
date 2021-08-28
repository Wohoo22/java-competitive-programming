import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGenerator {
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";

    private static class Config {
        static boolean useOutputFile = true;
        static String outputFile = System.getProperty("user.dir") + "\\src\\gdsctest\\URLify\\input\\input05.txt";
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        BufferedWriter writer;
        writer = getWriter();

        int t = rndInt(1, 1);
        writer.write(t + "\n");
        for (int k = 0; k < t; k++) {
            int n = rndInt(1000000, 1000000);
            writer.write(n + "\n");
            int blank = rndInt(1, n - 1);
            List<Character> chars = new ArrayList<>();
            for (int i = 0; i < blank; i++)
                chars.add(' ');
            for (int i = 0; i < n - blank; i++) {
                chars.add((char) ('a' + rndInt(0, 25)));
            }
            for (int i = 0; i < chars.size(); i++) {
                int j = rndInt(0, chars.size() - 1);
                char tmp = chars.get(j);
                chars.set(j, chars.get(i));
                chars.set(i, tmp);
            }
            for (char c : chars)
                writer.write(c);
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
