import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

public class Bruteforce {

    private static final String checkerSolutionOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.solution.out";
    private static final String checkerInput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.in";
    private static final String checkerBruteforcesOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_checker.bruteforces.out";
    private static final String fileInput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_in";
    private static final String fileOutput = "/home/quanvda/Main/Projects/MyProject/java-competitive-programming/src/_out";

    private static class Config {
        private static final boolean useInputFile = true;
        private static final boolean useOutputFile = true;
        private static final String inputFile = checkerInput;
        private static final String outputFile = checkerBruteforcesOutput;
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

    private static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            points.add(new int[]{sc.nextInt(), sc.nextInt()});
        }
        Map<Integer, List<int[]>> xSet = new HashMap<>();
        Map<Integer, List<int[]>> ySet = new HashMap<>();
        for (int[] p : points) {
            xSet.put(p[0], new ArrayList<>());
            ySet.put(p[1], new ArrayList<>());
        }
        for (int[] p : points) {
            xSet.get(p[0]).add(p);
            ySet.get(p[1]).add(p);
        }
        List<List<int[]>> existSet = new ArrayList<>();
        int ans = 0;
        Object[][] map = new Object[][]{
                new Object[]{
                        0, 0, "C"
                },
                new Object[]{
                        0, 1, "B"
                },
                new Object[]{
                        0, 2, "A"
                },
                new Object[]{
                        1, 1, "D"
                },
                new Object[]{
                        1, 0, "E"
                },
                new Object[]{
                        2, 2, "G"
                },
                new Object[]{
                        2, 0, "F"
                },
        };
        for (int[] fir : points) {
            for (int[] sec : points) {
                for (int[] thir : points) {
                    if (!fir.equals(sec) && !fir.equals(thir) && !sec.equals(thir)) {
                        if (!exist(existSet, fir, sec, thir)
                                && validTri(fir, sec, thir)
                                && !hasPointBtw(fir, thir, points)
                                && !hasPointBtw(sec, thir, points)
                                && !hasPointBtw(fir, sec, points)
                                && ss(fir, sec, thir)) {
                            ans++;
                            existSet.add(Arrays.asList(fir, sec, thir));
                            System.out.println("First " + fir[0] + "," + fir[1]
                                    + " Second " + sec[0] + "," + sec[1] + " Third " + thir[0] + "," + thir[1]);
                        }
                    }
                }
            }
        }
        writer.write(ans + "");
    }

    static boolean ss(int[] fir, int[] sec, int[] thir) {
        return parallel(fir, sec) || parallel(fir, thir) || parallel(sec, thir);
    }

    static boolean parallel(int[]... a) {
        boolean x = true, y = true;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i][0] != a[i + 1][0])
                x = false;
            if (a[i][1] != a[i + 1][1])
                y = false;
        }
        if (x)
            return true;
        else if (y)
            return true;
        else
            return false;
    }

    static String str(int[] p, Object[][] map) {
        for (Object[] o : map) {
            if ((int) o[0] == p[0] && (int) o[1] == p[1])
                return o[2] + " ";
        }
        return "";
    }

    static boolean hasPointBtw(int[] x, int[] y, List<int[]> points) {
        int x1 = Math.min(x[0], y[0]), x2 = Math.max(x[0], y[0]);
        int y1 = Math.min(x[1], y[1]), y2 = Math.max(x[1], y[1]);
        for (int[] z : points) {
            if (!eq(x, z) && !eq(y, z) && collinear(x[0], x[1], y[0], y[1], z[0], z[1])
                    && x1 <= z[0] && z[0] <= x2
                    && y1 <= z[1] && z[1] <= y2
            )
                return true;
        }
        return false;
    }

    static boolean collinear(int x1, int y1, int x2,
                             int y2, int x3, int y3) {
        int a = x1 * (y2 - y3) +
                x2 * (y3 - y1) +
                x3 * (y1 - y2);
        return a == 0;
    }


    static boolean eq(int[] a, int[] b) {
        return a[0] == b[0] && a[1] == b[1];
    }

    static boolean validTri(int[]... points) {
        int x1 = points[0][0], y1 = points[0][1];
        int x2 = points[1][0], y2 = points[1][1];
        int x3 = points[2][0], y3 = points[2][1];
        return vc(x1, y1, x2, y2, x3, y3) || vc(x1, y1, x3, y3, x2, y2)
                || vc(x2, y2, x1, y1, x3, y3) || vc(x3, y3, x1, y1, x2, y2)
                || vc(x2, y2, x3, y3, x1, y1) || vc(x3, y3, x2, y2, x1, y1);
    }

    static boolean vc(int x1, int y1, int x2,
                      int y2, int x3, int y3) {

        double a = distance(x1, y1, x2, y2);
        double b = distance(x1, y1, x3, y3);
        double c = distance(x2, y2, x3, y3);
        return a == b
                && distancePow(x2, y2, x3, y3) == distancePow(x1, y1, x2, y2) + distancePow(x1, y1, x3, y3);
    }

    static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    static double distancePow(int x1, int y1, int x2, int y2) {
        return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
    }

    static boolean exist(List<List<int[]>> existSet, int[]... points) {
        for (List<int[]> prev : existSet)
            if (contains(prev, points[0]) && contains(prev, points[1]) && contains(prev, points[2]))
                return true;
        return false;
    }

    static boolean contains(List<int[]> list, int[] p) {
        for (int[] a : list)
            if (a[0] == p[0] && a[1] == p[1])
                return true;
        return false;
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
