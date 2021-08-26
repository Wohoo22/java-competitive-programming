package gdsctest.LớnNhấtCóThể;

import java.io.*;
import java.util.StringTokenizer;

public class Bruteforces {

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
        static final String outputFile = checkerBruteforcesOutput;
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

    public static void calc(int n, int[] elements) {
        if (n == 1) {
            printArray(elements);
        } else {
            for (int i = 0; i < n - 1; i++) {
                calc(n - 1, elements);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            calc(n - 1, elements);
        }
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static void printArray(int[] input) {
        StringBuilder ans = new StringBuilder();
        for (int n : input)
            ans.append(n);
        String s = ans.toString();
        boolean better = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > max.charAt(i)) {
                better = true;
                break;
            } else if (s.charAt(i) < max.charAt(i)) {
                break;
            }
        }
        if (better) {
            max = s;
        }
    }

    static String max;

    public static String largestNumber(int[] nums) {
        int length = 0;
        for (int n : nums) {
            length += String.valueOf(n).length();
        }
        max = "0".repeat(Math.max(0, length));
        calc(nums.length, nums);
        return max;
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
