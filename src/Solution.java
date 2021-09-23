import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {

    private static final String checkerSolutionOutput = "D:\\Work\\work-space\\CP\\src\\_checker.solution.out";
    private static final String checkerInput = "D:\\Work\\work-space\\CP\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = "D:\\Work\\work-space\\CP\\src\\_checker.bruteforces.out";
    private static final String fileInput = "D:\\Work\\work-space\\CP\\src\\_in";
    private static final String fileOutput = "D:\\Work\\work-space\\CP\\src\\_out";


    private static class Config {
        private static final boolean useInputFile = true;
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

    private static int upper(int[][] peopleArray, int bound, int axis) {
        int bin = Arrays.binarySearch(peopleArray, bound, (value, key) -> {
            int val = (int[]) value[1];
        });
        return 0;
    }

    private static int lower(int[][] peopleArray, int bound, int axis) {
        return 0;

    }

    private static int calcCouples(int[][] peopleArray, int axis, int start, int end) {
        return 0;

    }

    private static int calcAxis(ArrayList<int[]> people, int axis, int[] lines) {
        int[][] peopleArray = new int[people.size()][2];
        for (int i = 0; i < peopleArray.length; i++)
            peopleArray[i] = people.get(i);
        int ans = 0;
        for (int i = 0; i < lines.length; i++) {
            int curVertical = lines[i];
            int lstVertical = lines[i - 1];
            int start = upper(peopleArray, lstVertical, 1);
            int end = lower(peopleArray, curVertical, 1);
            ans += calcCouples(peopleArray, 1, start, end);
        }
        return ans;
    }

    private static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[] verticals = new int[n];
        int[] horizontals = new int[m];
        int[][] people = new int[k][2];
        ArrayList<int[]> peopleVertical = new ArrayList<>();
        ArrayList<int[]> peopleHorizontal = new ArrayList<>();
        for (int[] pp : people) {
            peopleVertical.add(pp);
            peopleHorizontal.add(pp);
        }
        peopleVertical.sort(Comparator.comparingInt(a -> a[1]));
        peopleHorizontal.sort(Comparator.comparingInt(a -> a[0]));
        int ans = calcAxis(peopleVertical, 1, verticals) + calcAxis(peopleHorizontal, 0, horizontals);
        writer.write(ans + "\n");
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
