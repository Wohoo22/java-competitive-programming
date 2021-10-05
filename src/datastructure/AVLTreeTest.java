package datastructure;

import java.util.Random;

public class AVLTreeTest {
    /*
        WRONG ANSWER at index 4
        + Expect: 3
        + Real: 2
        Input:
        { 18, 34, 52, 65, 72, 73, 0, }
        m = 65
    */
    public static void main(String[] args) {
//        simpleTest();
//        printTree(new int[]{18, 34, 52, 65, 72});
//        countNodeLessThanTest(new int[]{8, 34, 52, 65, 72}, 65);
        countNodeLessThanTest(null, null);
    }

    private static void printTree(int[] insertion) {
        AVLTree tree = new AVLTree();
        for (int k : insertion) tree.insert(k);
        tree.printNodes();
        tree.countNodesLessThan(482);
    }


    private static void countNodeLessThanTest(int[] inputArray, Integer inputm) {
        int tests = rndInt(100, 100);
        for (int test = 0; test < tests; test++) {
            int n = inputArray == null ? rndInt(1, 1000) : inputArray.length;
            int a[] = inputArray == null ? new int[n + 1] : inputArray;
            if (inputArray == null)
                for (int i = 0; i < n; i++) {
                    if (i > 0) a[i] = a[i - 1] + rndInt(1, 20);
                    else a[i] = rndInt(1, 20);
                }
            int m = inputm == null ? rndInt(1, 200) : inputm;
            AVLTree tree = new AVLTree();
            for (int i = 0; i < n; i++) {
                tree.insert(a[i]);
                int expect = 0;
                for (int j = 0; j <= i; j++)
                    if (a[j] < m) expect++;
                int real = tree.countNodesLessThan(m);
                if (expect != real) {
                    System.out.println("WRONG ANSWER at index " + i);
                    System.out.println("+ Expect: " + expect);
                    System.out.println("+ Real: " + real);
                    System.out.println("Input:");
                    System.out.print("{ ");
                    for (int v : a) System.out.print(v + ", ");
                    System.out.print("}\n");
                    System.out.println("m = " + m);
                    System.out.println("");
                    return;
                }
            }
        }
        System.out.print("OK " + tests + " tests.");
    }

    private static void simpleTest() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder();
        tree.printNodes();
        /*
        Preorder traversal of the constructed AVL tree is
        30 20 10 25 40 50
         */
    }

    private static int rndInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
