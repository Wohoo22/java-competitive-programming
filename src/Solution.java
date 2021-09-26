import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Solution {

    private static final String checkerSolutionOutput = "D:\\Work\\work-space\\CP\\src\\_checker.solution.out";
    private static final String checkerInput = "D:\\Work\\work-space\\CP\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = "D:\\Work\\work-space\\CP\\src\\_checker.bruteforces.out";
    private static final String fileInput = "D:\\Work\\work-space\\CP\\src\\_in";
    private static final String fileOutput = "D:\\Work\\work-space\\CP\\src\\_out";


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

    static int n;
    static boolean initGraph[][];
    static boolean indexedGraph[][];

    private static List<Integer> bfsInitGraph() {
        boolean[] visted = new boolean[n + 1];
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty()) {
            int top = queue.poll();
            res.add(top);
            visted[top] = true;
            for (int v = 1; v <= n; v++)
                if (initGraph[top][v] && !visted[v])
                    queue.add(v);
        }
        return res;
    }

    private static void dfs(ArrayList<Integer>[] tree, int u, boolean[] visited) {
        visited[u] = true;
        tree[u] = new ArrayList<>();
        for (int v = 1; v <= n; v++) {
            if (!visited[v] && indexedGraph[u][v]) {
                tree[u].add(v);
                dfs(tree, v, visited);
            }
        }
    }

    private static ArrayList<Integer>[] buidDfsTree() {
        ArrayList<Integer>[] tree = new ArrayList[n + 1];
        dfs(tree, 1, new boolean[n + 1]);
        return tree;
    }

    private static void calcDistFromRoot(ArrayList<Integer>[] dfsTree, int u, int dist, int[] distFromRoot) {
        distFromRoot[u] = dist;
        for (int v : dfsTree[u])
            calcDistFromRoot(dfsTree, v, dist + 1, distFromRoot);
    }

    private static int calcChildCount(ArrayList<Integer>[] dfsTree, int u, int[] childCnt) {
        int child = 0;
        for (int v : dfsTree[u])
            child += calcChildCount(dfsTree, v, childCnt) + 1;
        childCnt[u] = child;
        return child;
    }

    private static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        n = sc.nextInt();
        initGraph = new boolean[n + 1][n + 1];
        for (int i = 1; i < n; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            initGraph[u][v] = initGraph[v][u] = true;
        }
        // build index
        List<Integer> nodes = bfsInitGraph();
        int[] mapInitGraphToIndexedGraph = new int[n + 1];
        int index = 1;
        for (int node : nodes) {
            mapInitGraphToIndexedGraph[node] = index;
            index++;
        }
        indexedGraph = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                if (initGraph[i][j])
                    indexedGraph[mapInitGraphToIndexedGraph[i]][mapInitGraphToIndexedGraph[j]] = true;
        // build dfs tree
        ArrayList<Integer>[] dfsTree = buidDfsTree();
        // calc dist from node 1
        int[] distFromRoot = new int[n + 1];
        calcDistFromRoot(dfsTree, 1, 0, distFromRoot);
        // prefix dist
        long[] postfixSumDistFromRoot = new long[n + 1];
        for (int i = n; i >= 1; i--) {
            if (i == n) postfixSumDistFromRoot[i] = distFromRoot[i];
            else postfixSumDistFromRoot[i] = distFromRoot[i] + postfixSumDistFromRoot[i + 1];
        }
        // calc child
        int[] childCnt = new int[n + 1];
        calcChildCount(dfsTree, 1, childCnt);
        // calc ans
        long ans = 0;
        for (int i = 1; i < n; i++) {
            ans += postfixSumDistFromRoot[i + 1] + (n - i) * distFromRoot[i] - childCnt[i] * 2 * distFromRoot[i];
        }
        writer.write(ans + "");
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
