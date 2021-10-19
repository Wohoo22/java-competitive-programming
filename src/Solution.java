import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution {

    private static final String checkerSolutionOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.solution.out";
    private static final String checkerInput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.in";
    private static final String checkerBruteforcesOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.bruteforces.out";
    private static final String fileInput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_in";
    private static final String fileOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_out";

    private static class Config {
        private static final boolean useInputFile = false;
        private static final boolean useOutputFile = false;
        private static final String inputFile = fileInput;
        private static final String outputFile = checkerSolutionOutput;
    }

    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception {
        FastScanner sc = new FastScanner();
        int t = 1;
        t = sc.nextInt();
        BufferedWriter writer = getWriter();
        for (int i = 0; i < t; i++)
            solve(sc, writer);
        writer.flush();
    }


    private static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        int top[] = new int[n + 1];
        int dif[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            top[i] = sc.nextInt();
            dif[i] = sc.nextInt();
        }
        int topCnt[] = new int[n + 1];
        int difCnt[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            topCnt[top[i]]++;
            difCnt[dif[i]]++;
        }
        long tot = n * 1L * (n - 1) * 1L * (n - 2) * 1L / 6;
        long bad = 0;
        for (int i = 1; i <= n; i++)
            bad += (topCnt[top[i]] - 1) * 1L * (difCnt[dif[i]] - 1) * 1L;
        writer.write((tot - bad) + "\n");
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

        public int toInt() {
            return Integer.parseInt(this.toString());
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

        public boolean greaterThanOrEqual(CustomBigInteger value) {
            return this.greaterThan(value) || this.equal(value);
        }

        public boolean lessThanOrEqual(CustomBigInteger value) {
            return this.lessThan(value) || this.equal(value);
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

        public CustomBigInteger mul(String value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger mul(int value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger mul(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            value.value
                    )
            );
        }

        public CustomBigInteger mul(long value) {
            return new CustomBigInteger(
                    this.value.multiply(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }


        public CustomBigInteger div(String value) {
            return new CustomBigInteger(
                    this.value.divide(
                            new BigInteger(value)
                    )
            );
        }

        public CustomBigInteger div(int value) {
            return new CustomBigInteger(
                    this.value.divide(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }

        public CustomBigInteger div(CustomBigInteger value) {
            return new CustomBigInteger(
                    this.value.divide(
                            value.value
                    )
            );
        }

        public CustomBigInteger div(long value) {
            return new CustomBigInteger(
                    this.value.divide(
                            new BigInteger(
                                    String.valueOf(value)
                            )
                    )
            );
        }
    }


    private static class Pair<A, B> {
        A first;
        B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public Pair() {
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
