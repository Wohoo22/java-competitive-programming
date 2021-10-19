import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestGenerator {
    private static final String checkerSolutionOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.solution.out";
    private static final String checkerInput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.in";
    private static final String checkerBruteforcesOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.bruteforces.out";
    private static final String fileInput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_in";
    private static final String fileOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_out";

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

        int n = rndInt(20, 100);
        writer.write(n + "\n");
        List<int[]> exist = new ArrayList<>();
        int l = -10000, r = 10000;
        for (int i = 0; i < n; i++) {
            int[] p = new int[]{rndInt(l, r), rndInt(l, r)};
            while (isExist(exist, p))
                p = new int[]{rndInt(l, r), rndInt(l, r)};
            writer.write(p[0] + " " + p[1] + "\n");
            exist.add(p);
        }

        writer.flush();
    }

    static boolean isExist(List<int[]> exist, int[] p) {
        for (int[] b : exist)
            if (b[0] == p[0] && b[1] == p[1])
                return true;
        return false;
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
