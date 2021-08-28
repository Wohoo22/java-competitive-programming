import java.io.*;
import java.util.StringTokenizer;

public class Bruteforces {

    private static final String checkerSolutionOutput = System.getProperty("user.dir") + "\\src\\_checker.solution.out";
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out";
    private static final String fileInput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String fileOutput = System.getProperty("user.dir") + "\\src\\_in";
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
        int t = 1;
        BufferedWriter writer = getWriter();
        for (int i = 0; i < t; i++)
            solve(sc, writer);
        writer.flush();
    }

    public static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        long k = sc.nextLong();
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int x = 3; x <= n; x++) {
            for (int y = 1; y < x; y++) {
                dp[x] += dp[x - y];
                dp[x] %= k;
            }
            for (int z = 2; z <= x; z++) {
                dp[x] += dp[Math.floorDiv(x, z)];
                dp[x] %= k;
            }
        }
        writer.write(dp[n] + "\n");
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
