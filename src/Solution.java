import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.List;

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
        BufferedWriter writer = getWriter();
        for (int i = 0; i < t; i++)
            solve(sc, writer);
        writer.flush();
    }

    private static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        List<Pair<Integer, Integer>> points = new ArrayList<>();
        for (int i = 0; i < n; i++)
            points.add(new Pair<>(sc.nextInt(), sc.nextInt()));
        // save points on each vertical
        Map<Integer, List<Pair<Integer, Integer>>> verticalSet = new HashMap<>();
        Map<Integer, List<Pair<Integer, Integer>>> horizontalSet = new HashMap<>();
        for (Pair<Integer, Integer> p : points) {
            verticalSet.put(p.first, new ArrayList<>());
            horizontalSet.put(p.second, new ArrayList<>());
        }
        for (Pair<Integer, Integer> p : points) {
            verticalSet.get(p.first).add(p);
            horizontalSet.get(p.second).add(p);
        }
        // sort points by height on each vertical
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : verticalSet.entrySet())
            entry.getValue().sort(Comparator.comparingInt(x -> x.second));
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : horizontalSet.entrySet())
            entry.getValue().sort(Comparator.comparingInt(x -> x.first));
        // for checking if point exist
        Map<Integer, Set<Integer>> existSet = new HashMap<>();
        for (Pair<Integer, Integer> p : points)
            existSet.put(p.first, new HashSet<>());
        for (Pair<Integer, Integer> p : points)
            existSet.get(p.first).add(p.second);
        // find rising cross and decreasing cross of each point
        Map<Integer, List<Pair<Integer, Integer>>> risingCrossSet = new HashMap<>();
        Map<Integer, List<Pair<Integer, Integer>>> decreasingCrossSet = new HashMap<>();
        for (Pair<Integer, Integer> p : points) {
            // rising
            int risingCross = findRisingCross(p);
            List<Pair<Integer, Integer>> risingNeighbors = risingCrossSet.getOrDefault(risingCross, new ArrayList<>());
            risingNeighbors.add(p);
            risingCrossSet.put(risingCross, risingNeighbors);
            // decreasing
            int decreasingCross = findDecreasingCross(p);
            List<Pair<Integer, Integer>> decreasingNeighbors = decreasingCrossSet.getOrDefault(decreasingCross, new ArrayList<>());
            decreasingNeighbors.add(p);
            decreasingCrossSet.put(decreasingCross, decreasingNeighbors);
        }
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : risingCrossSet.entrySet())
            entry.getValue().sort(Comparator.comparingInt(x -> x.first));
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : decreasingCrossSet.entrySet())
            entry.getValue().sort(Comparator.comparingInt(x -> x.second));
        // caculate for each pair on a vertical
        int ans = 0;
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : verticalSet.entrySet()) {
            for (int i = 0; i < entry.getValue().size() - 1; i++) {
                Pair<Integer, Integer> fir = entry.getValue().get(i);
                Pair<Integer, Integer> sec = entry.getValue().get(i + 1);
                List<Pair<Integer, Integer>> thirds = generateThirdPoints(fir, sec);
                for (Pair<Integer, Integer> thir : thirds) {
                    if (exist(existSet, thir)) {
                        // check if there are any other point on line fir -> thir and sec -> thir
                        if (validLine(horizontalSet, risingCrossSet, decreasingCrossSet, fir, thir)
                                && validLine(horizontalSet, risingCrossSet, decreasingCrossSet, sec, thir)) {
                            ans++;
                        }
                    }
                }
            }
        }
        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry : horizontalSet.entrySet()) {
            for (int i = 0; i < entry.getValue().size() - 1; i++) {
                Pair<Integer, Integer> fir = entry.getValue().get(i);
                Pair<Integer, Integer> sec = entry.getValue().get(i + 1);
                List<Pair<Integer, Integer>> thirds = new ArrayList<>();
                int diff = sec.first - fir.first;
                if (diff % 2 == 0) {
                    int half = diff / 2;
                    thirds.add(new Pair<>(fir.first + half, fir.second + half));
                    thirds.add(new Pair<>(fir.first + half, fir.second - half));
                }
                for (Pair<Integer, Integer> thir : thirds) {
                    if (exist(existSet, thir)) {
                        // check if there are any other point on line fir -> thir and sec -> thir
                        if (validLine(horizontalSet, risingCrossSet, decreasingCrossSet, fir, thir)
                                && validLine(horizontalSet, risingCrossSet, decreasingCrossSet, sec, thir)) {
                            ans++;
                        }
                    }
                }
            }
        }
        writer.write(ans + "");
    }

    static boolean validLine(Map<Integer, List<Pair<Integer, Integer>>> horizontalSet,
                             Map<Integer, List<Pair<Integer, Integer>>> risingCrossSet,
                             Map<Integer, List<Pair<Integer, Integer>>> decreasingCrossSet,
                             Pair<Integer, Integer> fir, Pair<Integer, Integer> sec) {
        if (fir.second == sec.second) {
            List<Pair<Integer, Integer>> set = horizontalSet.get(fir.second);
            int firstIndex = Collections.binarySearch(set, fir, (x, y) -> {
                if (x.first < y.first) return -1;
                if (x.first > y.first) return 1;
                return 0;
            });
            if (fir.first < sec.first)
                return eq(set.get(firstIndex + 1), sec);
            return eq(set.get(firstIndex - 1), sec);
        }
        if (fir.first < sec.first && fir.second < sec.second
                || fir.first > sec.first && fir.second > sec.second) {
            int rising = findRisingCross(fir);
            List<Pair<Integer, Integer>> set = risingCrossSet.get(rising);
            int firstIndex = Collections.binarySearch(set, fir, (x, y) -> {
                if (x.first < y.first) return -1;
                if (x.first > y.first) return 1;
                return 0;
            });
            if (fir.first < sec.first)
                return eq(set.get(firstIndex + 1), sec);
            return eq(set.get(firstIndex - 1), sec);
        }
        int decreasing = findDecreasingCross(fir);
        List<Pair<Integer, Integer>> set = decreasingCrossSet.get(decreasing);
        int firstIndex = Collections.binarySearch(set, fir, (x, y) -> {
            if (x.second < y.second) return -1;
            if (x.second > y.second) return 1;
            return 0;
        });
        if (fir.second < sec.second)
            return eq(set.get(firstIndex + 1), sec);
        return eq(set.get(firstIndex - 1), sec);
    }

    static boolean eq(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
        return first.first == second.first && first.second == second.second;
    }

    static int findRisingCross(Pair<Integer, Integer> point) {
        Pair<Integer, Integer> next = new Pair<>(point.first + 1, point.second + 1);
        int a = next.second - point.second;
        int b = point.first - next.first;
        int c = a * (point.first) + b * (point.second);
        return c / a;
    }

    static int findDecreasingCross(Pair<Integer, Integer> point) {
        Pair<Integer, Integer> next = new Pair<>(point.first - 1, point.second + 1);
        int a = next.second - point.second;
        int b = point.first - next.first;
        int c = a * (point.first) + b * (point.second);
        return c / a;
    }

    static boolean exist(Map<Integer, Set<Integer>> existSet, Pair<Integer, Integer> point) {
        if (!existSet.containsKey(point.first)) return false;
        return existSet.get(point.first).contains(point.second);
    }

    static List<Pair<Integer, Integer>> generateThirdPoints(Pair<Integer, Integer> fir, Pair<Integer, Integer> sec) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        int dist = sec.second - fir.second;
        result.add(new Pair<>(sec.first + dist, sec.second));
        result.add(new Pair<>(fir.first + dist, fir.second));
        result.add(new Pair<>(sec.first - dist, sec.second));
        result.add(new Pair<>(fir.first - dist, fir.second));
        if (dist % 2 != 0) return result;
        int half = dist / 2;
        result.add(new Pair<>(fir.first + half, fir.second + half));
        result.add(new Pair<>(fir.first - half, fir.second + half));
        return result;
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
