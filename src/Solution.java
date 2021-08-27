import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static final String checkerSolutionOutput = System.getProperty("user.dir") + "\\src\\_checker.solution.out";
    private static final String checkerInput = System.getProperty("user.dir") + "\\src\\_checker.in";
    private static final String checkerBruteforcesOutput = System.getProperty("user.dir") + "\\src\\_checker.bruteforces.out";
    private static final String fileInput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String fileOutput = System.getProperty("user.dir") + "\\src\\_in";
    private static final String base = System.getProperty("user.dir") + "\\src\\gdsctest";


    private static class Config {
        static final boolean useInputFile = true;
        static final boolean useOutputFile = true;
        static final String inputFile = base + "\\ChuỗiDàiNhất\\input\\input04.txt";
        static final String outputFile = base + "\\ChuỗiDàiNhất\\output\\output04.txt";
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

    public static void solve(FastScanner sc, BufferedWriter writer) throws Exception {
        int n = sc.nextInt();
        String s = sc.next();
        writer.write(lengthOfLongestSubstring(s, n) + "\n");
    }

    public static int lengthOfLongestSubstring(String s, int n) {
        boolean[] exist = new boolean[26];
        int ans = 0;
        int i = 0;
        int j = -1;
        while (i < n) {
            int c = s.charAt(i) - 'a';
            exist[c] = true;
            if (j <= i)
                j = i + 1;
            while (j < n && !exist[s.charAt(j) - 'a']) {
                exist[s.charAt(j) - 'a'] = true;
                j++;
            }
            ans = Math.max(ans, j - i);
            exist[c] = false;
            i++;
        }
        return ans;
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
