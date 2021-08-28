import java.io.*;
import java.util.StringTokenizer;

public class Nothing {


    public static void main(String[] args) {
        run();
    }

    public static void run() {
        FastScanner sc = new FastScanner();
        int t = 1;
        BufferedWriter writer = getWriter();
        for (int i = 0; i < t; i++)
            solve(sc, writer);
        try {
            writer.flush();
        } catch (Exception ignored) {

        }
    }

    public static void solve(FastScanner sc, BufferedWriter writer) {
        int n = sc.nextInt();
        long k = sc.nextLong();
        try {
            sc.close();
        } catch (Exception ignored) {

        }
        long[] dp = new long[n + 1];
        long sum = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int x = 3; x <= n; x++) {
            sum += dp[x - 1];
            sum %= k;
            dp[x] += sum;
            for (int z = 2; z <= (int) (Math.ceil(Math.sqrt(x))); z++) {
                dp[x] += dp[Math.floorDiv(x, z)];
                dp[x] %= k;
            }
            for (int z = (int) (Math.ceil(Math.sqrt(x)) + 1); z <= x; z++) {
                dp[x] += dp[Math.floorDiv(x, z)];
                dp[x] %= k;
            }
        }
        try {
            writer.write(dp[n] + "\n");
        } catch (Exception ignored) {

        }
    }

    private static class FastScanner {

        BufferedReader br;
        StringTokenizer st;

        private FastScanner() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
            this.st = new StringTokenizer("");
        }

        void close() throws IOException {
            br.close();
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

    private static BufferedWriter getWriter() {
        return getConsoleWriter();
    }

    private static BufferedWriter getConsoleWriter() {
        return new BufferedWriter(new OutputStreamWriter(System.out));
    }
}
