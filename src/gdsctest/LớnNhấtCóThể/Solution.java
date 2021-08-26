package gdsctest.LớnNhấtCóThể;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static final String checkerSolutionOutput = System.getProperty("user.dir") + "\\src\\_checker.solution.out";
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out";
    private static final String inputFile = System.getProperty("user.dir") + "\\src\\_in";
    private static final String outputFile = System.getProperty("user.dir") + "\\src\\_in";
    private static final String base = System.getProperty("user.dir") + "\\src\\gdsctest";


    private static class Config {
        static final boolean useInputFile = true;
        static final boolean useOutputFile = true;
        static final String inputFile = checkerInput;
        static final String outputFile = checkerSolutionOutput;
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
        int[] a = sc.readArray(n);
        writer.write(largestNumber(a) + "\n");
    }

    public static boolean greater(int smallerInt, int greaterInt) {
        String smaller = String.valueOf(smallerInt);
        String greater = String.valueOf(greaterInt);
        return Long.parseLong(smaller + greater) < Long.parseLong(greater + smaller);
    }

    public static String largestNumber(int[] nums) {
        int n = nums.length;
        StringBuilder result = new StringBuilder();
        boolean[] chosen = new boolean[n];
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (chosen[j]) j++;
            for (int k = j + 1; k < n; k++) {
                if (!chosen[k] && greater(nums[j], nums[k])) {
                    j = k;
                }
            }
            result.append(nums[j]);
            chosen[j] = true;
        }

        boolean full0 = true;
        for (int i = 0; i < result.length(); i++)
            if (result.charAt(i) != '0')
                full0 = false;

        if (full0)
            return "0";

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
