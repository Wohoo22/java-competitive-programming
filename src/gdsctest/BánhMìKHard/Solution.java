package gdsctest.BánhMìKHard;

import java.io.*;
import java.util.*;

public class Solution {

    private static final String checkerSolutionOutput = System.getProperty("user.dir") + "\\src\\_checker.solution.out";
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out";
    private static final String fileInput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String fileOutput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String base = System.getProperty("user.dir") + "\\src\\gdsctest";


    private static class Config {
        static final boolean useInputFile = false;
        static final boolean useOutputFile = false;
        static final String inputFile = fileInput;
        static final String outputFile = fileOutput;
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        FastScanner sc = new FastScanner();
        int t = sc.nextInt();
        BufferedWriter writer = getWriter();
        for (int i = 0; i < t; i++) {
            solve(sc, writer);
        }
        writer.flush();
    }

    static Map<Integer, Boolean> visited;
    static Set<Integer> ele;
    static int close;
    static int k;

    static boolean open(int e) {
        visited.put(e, true);
        boolean isOpen = true;
        if (ele.contains(e - k) && !visited.get(e - k)) {
            if (open(e - k))
                isOpen = false;
        }
        if (ele.contains(e + k) && !visited.get(e + k)) {
            if (open(e + k))
                isOpen = false;
        }
        if (!isOpen)
            close++;
        return isOpen;
    }

    public static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        k = sc.nextInt();
        int[] a = sc.readArray(n);
        visited = new HashMap<>();
        ele = new HashSet<>();
        for (int i : a) {
            visited.put(i, false);
            ele.add(i);
        }
        int ans = 0;
        for (int i : a) {
            if (!visited.get(i)) {
                close = 0;
                open(i);
                    ans += close;
            }
        }
        writer.write(ans + "\n");
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
