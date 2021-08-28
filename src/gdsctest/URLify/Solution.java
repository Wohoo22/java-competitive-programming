package gdsctest.URLify;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static final String checkerSolutionOutput = System.getProperty("user.dir") + "\\src\\_checker.solution.out";
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out";
    private static final String fileInput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String fileOutput = System.getProperty("user.dir") + "\\src\\_in";


    private static class Config {
        static final boolean useInputFile = true;
        static final boolean useOutputFile = true;
        static final String inputFile = System.getProperty("user.dir") + "\\src\\gdsctest\\URLify\\input\\input05.txt";
        static final String outputFile = System.getProperty("user.dir") + "\\src\\gdsctest\\URLify\\output\\output05.txt";
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        FastScanner sc = new FastScanner();
        int t = sc.nextInt();
        BufferedWriter writer = getWriter();
        for (int i = 0; i < t; i++)
            solve(sc, writer);
        writer.flush();
    }

    public static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        String s = sc.nextLine();
        writer.write(calc(s) + "\n");
    }

    public static String calc(String s) {
        int n = s.length();
        StringBuilder result = new StringBuilder();
        int l = 0;
        int r = s.length() - 1;
        while (l < n && s.charAt(l) == ' ')
            l++;
        while (r >= 0 && s.charAt(r) == ' ')
            r--;
        while (l <= r) {
            if (s.charAt(l) != ' ') {
                result.append(s.charAt(l));
                l++;
            } else {
                while (l <= r && s.charAt(l) == ' ')
                    l++;
                result.append('%');
            }
        }
        return result.toString();
    }

    private static class FastScanner {

        BufferedReader br;
        StringTokenizer st;

        private FastScanner() throws FileNotFoundException {
            if (Config.useInputFile)
                this.br = new BufferedReader(new InputStreamReader(new FileInputStream(Config.inputFile)));
            else
                this.br = new BufferedReader(new InputStreamReader(System.in));
            this.st = new StringTokenizer("");
        }

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Error";
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    private static BufferedWriter getWriter() throws Exception {
        if (Config.useOutputFile)
            return getFileWriter();
        return getConsoleWriter();
    }

    private static BufferedWriter getFileWriter() throws Exception {
        PrintWriter writer = new PrintWriter(Config.outputFile);
        writer.print("");
        writer.close();
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.outputFile)));
    }

    private static BufferedWriter getConsoleWriter() {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }
}
