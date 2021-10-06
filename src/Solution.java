import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
        int a[] = sc.readArray(n);
        AVLTree tree = new AVLTree();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int lt = tree.countOccurenceOfKeysLessThan(a[i]);
            int gt = i - lt - tree.getKeyOccurence(a[i]);
//            System.out.println("a[i]: " + a[i]);
//            System.out.println("lt: " + lt + ", gt: " + gt);
            if (lt < gt) ans += lt;
            else ans += gt;
            tree.insert(a[i]);
        }
        writer.write(ans + "\n");
    }


    public static class AVLTree {
    /* <<<<< INSERTION >>>>>

    ### LEFT LEFT CASE

        T1, T2, T3 and T4 are subtrees.
             z                                      y
            / \                                   /   \
           y   T4      Right Rotate (z)          x      z
          / \          - - - - - - - - ->      /  \    /  \
         x   T3                               T1  T2  T3  T4
        / \
      T1   T2


   ### LEFT RIGHT CASE

             z                               z                           x
            / \                            /   \                        /  \
           y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
          / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
        T1   x                          y    T3                    T1  T2 T3  T4
            / \                        / \
          T2   T3                    T1   T2

   ### RIGHT RIGHT CASE

           z                                y
         /  \                            /   \
        T1   y     Left Rotate(z)       z      x
            /  \   - - - - - - - ->    / \    / \
           T2   x                     T1  T2 T3  T4
               / \
             T3  T4

   ### RIGHT LEFT CASE

        z                            z                            x
      / \                          / \                          /  \
    T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
        / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
       x   T4                      T2   y                  T1  T2  T3  T4
      / \                              /  \
    T2   T3                           T3   T4

     */

        private Tree tree;

        public AVLTree() {
            tree = new Tree();
        }

        public void insert(int key) {
            int lastOcc = tree.keyOccurence.getOrDefault(key, 0);
            tree.keyOccurence.put(key, lastOcc + 1);
            tree.root = tree.insert(tree.root, key);
        }

        public int countKeysLessThan(int key) {
            return tree.countKeysLessThan(key);
        }

        public int countOccurenceOfKeysLessThan(int key) {
            return tree.countOccurenceOfKeysLessThan(key);
        }

        public int getKeyOccurence(int key) {
            return tree.keyOccurence.getOrDefault(key, 0);
        }

        public void preOrder() {
            preOrderUtil(tree.root);
            System.out.println("");
        }

        private void preOrderUtil(Node node) {
            if (node != null) {
                System.out.print(node.key + " ");
                preOrderUtil(node.left);
                preOrderUtil(node.right);
            }
        }

        public void printNodes() {
            printNodesUtil(tree.root);
        }

        private void printNodesUtil(Node node) {
            if (node != null) {
                System.out.println("<<<<NODE>>>>");
                System.out.println("KEY: " + node.key + " ");
                System.out.println("_childcount: " + node.childCount);
                if (node.left != null) System.out.println("_left: " + node.left.key);
                if (node.right != null) System.out.println("_right: " + node.right.key);
                System.out.println("");
                printNodesUtil(node.left);
                printNodesUtil(node.right);
            }
        }

        private static class Node {
            int key, height, childCount, childOccurence;
            Node left, right;

            Node(int key) {
                this.key = key;
                this.height = 1;
                this.childCount = 0;
                this.childOccurence = 0;
            }
        }

        private static class Tree {
            private Node root;
            private Map<Integer, Integer> keyOccurence = new HashMap<>();

            // > cacculate-child-occurence

            private int countChildOccurence(Node n) {
                if (n == null) return 0;
                int result = 0;
                if (n.left != null) result += n.left.childOccurence + keyOccurence.get(n.left.key);
                if (n.right != null) result += n.right.childOccurence + keyOccurence.get(n.right.key);
                return result;
            }

            // < caculate-child-occcurence

            // > caculate-child-count

            private int countChild(Node n) {
                if (n == null) return 0;
                int result = 0;
                if (n.left != null) result += n.left.childCount + 1;
                if (n.right != null) result += n.right.childCount + 1;
                return result;
            }

            // < cacuate-child-count

            // > caculate-height

            private int caculateHeight(Node n) {
                if (n == null) return 0;
                int result = 0;
                if (n.left != null) result = Math.max(result, n.left.height);
                if (n.right != null) result = Math.max(result, n.right.height);
                return result + 1;
            }

            // < caculate-height

            // > rotate

            private Node rightRotate(Node z) {
            /*
                         z                                       y
                        / \                                    /   \
                       y   m       Right Rotate (z)          x      z
                      / \          - - - - - - - - ->       / \    / \
                     x   n                                 k  p   n   m
                   / \
                  k   p
            */
                Node y = z.left;
                Node n = y.right;
                y.right = z;
                z.left = n;
                // height
                z.height = caculateHeight(z);
                y.height = caculateHeight(y);
                // child count
                z.childCount = countChild(z);
                y.childCount = countChild(y);
                // child occurence
                z.childOccurence = countChildOccurence(z);
                y.childOccurence = countChildOccurence(y);

                return y;
            }

            private Node leftRotate(Node y) {
            /*
                     z                                 z
                    / \                              /  \
                   y   m     Left Rotate (y)        x    m
                  / \        - - - - - - - - ->    / \
                n    x                            y   k
                    / \                          / \
                  p    k                        n   p
            */
                Node x = y.right;
                Node p = x.left;
                x.left = y;
                y.right = p;
                // height
                y.height = caculateHeight(y);
                x.height = caculateHeight(x);
                // child count
                y.childCount = countChild(y);
                x.childCount = countChild(x);
                // child occurence
                y.childOccurence = countChildOccurence(y);
                x.childOccurence = countChildOccurence(x);
                return x;
            }

            // < rotate

            private int getBalance(Node n) {
                return n == null ? 0 : caculateHeight(n.left) - caculateHeight(n.right);
            }

            private Node insert(Node n, int key) {
                if (n == null) return new Node(key);
                if (key < n.key) n.left = insert(n.left, key);
                if (key > n.key) n.right = insert(n.right, key);
                if (key == n.key) return n;
                n.height = caculateHeight(n);
                n.childCount = countChild(n);
                n.childOccurence = countChildOccurence(n);
                int balance = getBalance(n);
                if (balance > 1) {
                    if (key < n.left.key) return rightRotate(n);
                    if (key > n.left.key) {
                        n.left = leftRotate(n.left);
                        return rightRotate(n);
                    }
                }
                if (balance < -1) {
                    if (key > n.right.key) return leftRotate(n);
                    if (key < n.right.key) {
                        n.right = rightRotate(n.right);
                        return leftRotate(n);
                    }
                }
                return n;
            }

            // > count-node-less-than

            private int countKeysLessThanAnswer;

            private int countKeysLessThan(int key) {
                countKeysLessThanAnswer = 0;
                countKeysLessThanUtil(root, key);
                return countKeysLessThanAnswer;
            }

            private void countKeysLessThanUtil(Node n, int key) {
                if (n == null) return;
                if (key > n.key) {
                    countKeysLessThanAnswer += 1;
                    if (n.left != null) countKeysLessThanAnswer += n.left.childCount + 1;
                    countKeysLessThanUtil(n.right, key);
                } else if (key < n.key) {
                    countKeysLessThanUtil(n.left, key);
                } else {
                    if (n.left != null) countKeysLessThanAnswer += n.left.childCount + 1;
                    return;
                }
            }

            // < count-node-less-than

            // > count-occurence-of-keys-less-than

            private int countOccurenceOfKeysLessThanResult;

            private int countOccurenceOfKeysLessThan(int key) {
                countOccurenceOfKeysLessThanResult = 0;
                countOccurenceOfKeysLessThanUtil(root, key);
                return countOccurenceOfKeysLessThanResult;
            }

            private void countOccurenceOfKeysLessThanUtil(Node n, int key) {
                if (n == null) return;
                if (key > n.key) {
                    countOccurenceOfKeysLessThanResult += keyOccurence.get(n.key);
                    if (n.left != null)
                        countOccurenceOfKeysLessThanResult += n.left.childOccurence + keyOccurence.get(n.left.key);
                    countOccurenceOfKeysLessThanUtil(n.right, key);
                } else if (key < n.key) {
                    countOccurenceOfKeysLessThanUtil(n.left, key);
                } else {
                    if (n.left != null)
                        countOccurenceOfKeysLessThanResult += n.left.childOccurence + keyOccurence.get(n.left.key);
                    return;
                }
            }

            // < count-occurence-of-keys-less-than

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
