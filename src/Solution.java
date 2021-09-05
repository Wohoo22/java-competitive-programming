import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution {

    private static final String checkerSolutionOutput = System.getProperty("user.dir") + "\\src\\_checker.solution.out";
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out";
    private static final String fileInput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String fileOutput = System.getProperty("user.dir") + "\\src\\_in";


    private static class Config {
        private static final boolean useInputFile = true;
        private static final boolean useOutputFile = true;
        private static final String inputFile = checkerInput;
        private static final String outputFile = checkerSolutionOutput;
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

    private static CustomBigInteger sumRange(int l, int r, CustomBigInteger[] postfixSum, int[] d) {
        if (l > r)
            return new CustomBigInteger(0);
        if (l == r)
            return new CustomBigInteger(d[l]);
        if (r == postfixSum.length - 1)
            return postfixSum[l];
        return postfixSum[l].subtract(postfixSum[r + 1]);
    }

    private static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] d = new int[2 * n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i + n] = sc.nextInt();
        }
        CustomBigInteger[] postfixSum = new CustomBigInteger[2 * n + 1];
        for (int i = 2 * n; i >= 1; i--) {
            if (i == 2 * n) {
                postfixSum[i] = new CustomBigInteger(d[i]);
            } else {
                postfixSum[i] = new CustomBigInteger(d[i]).add(d[i + 1]);
            }
        }
        CustomBigInteger xBig = new CustomBigInteger(x);
        int maxMonth = 1;
        int maxValue = 0;
        for (int i = n + 1; i <= 2 * n; i++) {
            int l = 1, r = i;
            while (l < r) {
                int m = Math.floorDiv(r - l + 1, 2);
                CustomBigInteger sum = sumRange(m, i, postfixSum, d);
                if (sum.greaterThan(xBig) || sum.equal(xBig)) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            CustomBigInteger all = sumRange(l, i, postfixSum, d);
            CustomBigInteger remain;
            if (l < i) {
                remain = all.subtract(
                        sumRange(l - 1, i, postfixSum, d)
                );
            } else {
                remain = all;
            }

        }
    }

    private static class CustomBigInteger {
        private final BigInteger value;

        public CustomBigInteger(int value) {
            this.value = new BigInteger(String.valueOf(value));
        }


        public CustomBigInteger(String value) {
            this.value = new BigInteger(value);
        }

        public CustomBigInteger(long value) {
            this.value = new BigInteger(String.valueOf(value));
        }

        public CustomBigInteger(CustomBigInteger value) {
            this.value = new BigInteger(value.toString());
        }

        public CustomBigInteger(BigInteger value) {
            this.value = new BigInteger(value.toString());
        }

        @Override
        public String toString() {
            return this.value.toString();
        }

        public boolean lessThan(CustomBigInteger value) {
            return this.value.compareTo(value.value) < 0;
        }

        public boolean equal(CustomBigInteger value) {
            return this.value.compareTo(value.value) == 0;
        }

        public boolean greaterThan(CustomBigInteger value) {
            return this.value.compareTo(value.value) > 0;
        }

        public static CustomBigInteger max(CustomBigInteger a, CustomBigInteger b) {
            if (a.greaterThan(b))
                return a;
            return b;
        }

        public static CustomBigInteger min(CustomBigInteger a, CustomBigInteger b) {
            if (a.lessThan(b))
                return a;
            return b;
        }

        public CustomBigInteger add(String value) {
            return new CustomBigInteger(
                    this.value.add(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger add(int value) {
            return new CustomBigInteger(
                    this.value.add(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger add(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.add(
                            value.value
                    )
            );
        }

        public CustomBigInteger add(long value) {
            return new CustomBigInteger(
                    this.value.add(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger subtract(String value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger subtract(int value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger subtract(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            value.value
                    )
            );
        }

        public CustomBigInteger subtract(long value) {
            return new CustomBigInteger(
                    this.value.subtract(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mod(String value) {
            return new CustomBigInteger(
                    this.value.mod(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger mod(int value) {
            return new CustomBigInteger(
                    this.value.mod(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mod(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.mod(
                            value.value
                    )
            );
        }

        public CustomBigInteger mod(long value) {
            return new CustomBigInteger(
                    this.value.mod(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }
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
