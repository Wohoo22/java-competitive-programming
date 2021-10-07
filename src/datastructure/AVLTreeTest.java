package datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AVLTreeTest {
    public static void main(String[] args) {
        removeTest();
    }

    private static void printTree(int[] insertion) {
        AVLTree tree = new AVLTree();
        for (int k : insertion) tree.insert(k);
        tree.printNodes();
    }

    private static void runAllTest() {
        insertTest();
        countKeysLessThanTest(null, null);
        countOccurenceOfKeysLessThanTest(null, null);
        findMaximumKeyLessThanTest(null);
    }

    private static void removeTest() {
        AVLTree tree = new AVLTree();
        tree.insert(9);
        tree.insert(5);
        tree.insert(10);
        tree.insert(0);
        tree.insert(6);
        tree.insert(11);
        tree.insert(-1);
        tree.insert(1);
        tree.insert(2);
        tree.printNodes();
        System.out.println("[REMOVE_TEST] Preorder traversal of constructed tree is : ");
        tree.preOrder();
        tree.remove(10);
        System.out.println("[REMOVE_TEST] Preorder traversal after deletion of 10 :");
        tree.preOrder();
        tree.printNodes();
    }

    private static void insertTest() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
        System.out.println("Insert test: ");
        tree.preOrder();
        /*
        Preorder traversal of the constructed AVL tree is
        30 20 10 25 40 50
         */
    }

    private static void findMaximumKeyLessThanTest(int[] array) {
        int tests = array == null ? rndInt(500, 500) : 1;
        for (int test = 0; test < tests; test++) {
            int n = array == null ? rndInt(1, 1000) : array.length;
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                if (array != null) a[i] = array[i];
                else {
                    a[i] = rndInt(1, 100);
                }
            }
            AVLTree tree = new AVLTree();
            List<Integer> prefix = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                prefix.add(a[i]);
                prefix.sort(Integer::compareTo);
                Integer expected = null;
                for (int j = 0; j <= i; j++) {
                    if (prefix.get(j) == a[i]) {
                        if (j > 0) expected = prefix.get(j - 1);
                        break;
                    }
                }
                tree.insert(a[i]);
                Integer real = tree.findMaximumKeyLessThan(a[i]);
                if (expected == null && real == null)
                    continue;
                if (!expected.equals(real)) {
                    System.out.println("WRONG ANSWER at index " + i);
                    System.out.println("+ Expect: " + expected);
                    System.out.println("+ Real: " + real);
                    System.out.println("Input:");
                    System.out.print("{ ");
                    for (int v : a) System.out.print(v + ", ");
                    System.out.print("}\n");
                    return;
                }
            }
        }
        System.out.println("Find Maximum Key Less Than Test");
        System.out.println("OK " + tests + " tests.");
    }


    private static void countOccurenceOfKeysLessThanTest(int[] inputArray, Integer inputm) {
        int tests = rndInt(1000, 1000);
        for (int test = 0; test < tests; test++) {
            int n = inputArray == null ? rndInt(1, 1000) : inputArray.length;
            int a[] = inputArray == null ? new int[n + 1] : inputArray;
            if (inputArray == null)
                for (int i = 0; i < n; i++) {
                    int r = rndInt(1, 2);
                    if (r % 2 == 0) a[i] = a[rndInt(0, i)];
                    else a[i] = rndInt(1, 20);
                }
            int m = inputm == null ? rndInt(1, 200) : inputm;
            AVLTree tree = new AVLTree();
            for (int i = 0; i < n; i++) {
                try {
                    tree.insert(a[i]);
                    int expect = 0;
                    for (int j = 0; j <= i; j++)
                        if (a[j] < m) expect++;
                    int real = tree.countOccurenceOfKeysLessThan(m);
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
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERROR at index " + i);
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
        System.out.println("Count Occurence Of Keys Less Than Test");
        System.out.println("OK " + tests + " tests.");
    }

    private static void countKeysLessThanTest(int[] inputArray, Integer inputm) {
        int tests = rndInt(1000, 1000);
        for (int test = 0; test < tests; test++) {
            int n = inputArray == null ? rndInt(1, 1000) : inputArray.length;
            int a[] = inputArray == null ? new int[n + 1] : inputArray;
            if (inputArray == null)
                for (int i = 0; i < n; i++) {
                    if (i > 0) a[i] = a[i - 1] + rndInt(1, 20);
                    else a[i] = rndInt(1, 20);
                }
            int m = inputm == null ? rndInt(1, a[n - 1] + 10) : inputm;
            AVLTree tree = new AVLTree();
            for (int i = 0; i < n; i++) {
                tree.insert(a[i]);
                int expect = 0;
                for (int j = 0; j <= i; j++)
                    if (a[j] < m) expect++;
                int real = tree.countKeysLessThan(m);
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
        System.out.println("Count Keys Less Than Test");
        System.out.println("OK " + tests + " tests.");
    }

    private static int rndInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
