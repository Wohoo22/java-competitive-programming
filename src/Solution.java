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
        static final boolean useOutputFile = false;
        static final String inputFile = fileInput;
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

    public static int mod(char c, int zeros, int N) {
        String num = c + "0".repeat(Math.max(0, zeros));
        int res = 0;
        for (int i = 0; i < num.length(); i++)
            res = (res * 10 + (int) num.charAt(i) - '0') % N;
        return res;
    }

    public static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int M = sc.nextInt();
        int N = sc.nextInt();
        String S = sc.next();
        long[][][] dp = new long[M][N][M + 1];
        dp[M - 1][(S.charAt(M - 1) - '0') % N][1] = 1;
        for (int i = M - 2; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                for (int k = 1; k <= M - i; k++) {
                    if (k == 1 && (S.charAt(i) - '0') % N == j) {
                        dp[i][j][k] = 1;
                        continue;
                    }
                    int c = N + j - mod(S.charAt(i), k - 1, N);
                    c %= N;
                    for (int p = i + 1; p < M; p++)
                        dp[i][j][k] += dp[p][c][k - 1];
//                    System.out.println("i,j,k " + i + "," + j + "," + k);
//                    System.out.println("dp is " + dp[i][j][k]);
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < M; i++) {
            for (int k = 1; k <= M - i; k++) {
                ans += dp[i][0][k];
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
