package datastructure;

import java.util.HashMap;
import java.util.Map;

public class AVLTree {
    /* <<<<< INTUITION >>>>>

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

    private InternalTree tree;

    public AVLTree() {
        tree = new InternalTree();
    }

    // > search

    public boolean contains(int key) {
        return getKeyOccurence(key) > 0;
    }

    public Integer findMinimumKeyGreaterThan(int key) {
        return 0;
    }

    public Integer findMaximumKeyLessThan(int key) {
        tree.findMaximumKeyLessThanResult = null;
        tree.findMaximumKeyLessThan(tree.root, key);
        return tree.findMaximumKeyLessThanResult;
    }

    // < search

    // > insertion and deletion

    public void insert(int key) {
        int lastOcc = tree.keyOccurenceMap.getOrDefault(key, 0);
        tree.keyOccurenceMap.put(key, lastOcc + 1);
        tree.root = tree.insert(tree.root, key);
        tree.totalKeyOccurence++;
    }

    public void remove(int key) {

    }

    // < insertion and deletion

    // > count-keys

    public int countKeysLessThan(int key) {
        return tree.countKeysLessThan(key);
    }

    public int countKeysGreaterThan(int key) {
        int total = getTotalNumberOfKeys();
        int ans = total - countKeysLessThan(key);
        if (contains(key)) ans--;
        return ans;
    }

    // < count-keys

    // > count-occurence-of-keys

    public int countOccurenceOfKeysLessThan(int key) {
        return tree.countOccurenceOfKeysLessThan(key);
    }

    public int countOccurenceOfKeysGreaterThan(int key) {
        int lt = countOccurenceOfKeysLessThan(key);
        int eq = getKeyOccurence(key);
        return getTotalKeyOccurence() - lt - eq;
    }

    public int getKeyOccurence(int key) {
        return tree.keyOccurenceMap.getOrDefault(key, 0);
    }

    // > count-occurence-of-keys

    // > get-total

    public int getTotalKeyOccurence() {
        return tree.totalKeyOccurence;
    }

    public int getTotalNumberOfKeys() {
        if (tree.root == null) return 0;
        return tree.root.childCount + 1;
    }

    // < get-total

    // > pre-order

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

    // < pre-order

    // > print-nodes

    public void printNodes() {
        printNodesUtil(tree.root);
    }

    private void printNodesUtil(Node node) {
        if (node != null) {
            System.out.println("<<<< NODE >>>>");
            System.out.println("KEY: " + node.key + " ");
            System.out.println("_childcount: " + node.childCount);
            if (node.left != null) System.out.println("_left: " + node.left.key);
            if (node.right != null) System.out.println("_right: " + node.right.key);
            System.out.println("");
            printNodesUtil(node.left);
            printNodesUtil(node.right);
        }
    }

    // > print-nodes

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

    private static class InternalTree {
        int totalKeyOccurence = 0;
        private Node root;
        private Map<Integer, Integer> keyOccurenceMap = new HashMap<>();

        // > cacculate-child-occurence

        private int countChildOccurence(Node n) {
            if (n == null) return 0;
            int result = 0;
            if (n.left != null) result += n.left.childOccurence + keyOccurenceMap.get(n.left.key);
            if (n.right != null) result += n.right.childOccurence + keyOccurenceMap.get(n.right.key);
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

        // < caculate-child-count

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

        // > insert and remove

        private void updateInformation(Node n) {
            if (n == null) return;
            n.height = caculateHeight(n);
            n.childCount = countChild(n);
            n.childOccurence = countChildOccurence(n);
        }

        private Node makeBalance(Node n, int key) {
            if (n == null) return n;
            int balance = getBalance(n);
            if (balance > 1) {
                // Left left case
                if (key < n.left.key) return rightRotate(n);
                // Left right case
                if (key > n.left.key) {
                    n.left = leftRotate(n.left);
                    return rightRotate(n);
                }
            }
            if (balance < -1) {
                // Right right case
                if (key > n.right.key) return leftRotate(n);
                //  Right left case
                if (key < n.right.key) {
                    n.right = rightRotate(n.right);
                    return leftRotate(n);
                }
            }
            return n;
        }

        private Node insert(Node n, int key) {
            if (n == null) return new Node(key);
            if (key < n.key) n.left = insert(n.left, key);
            if (key > n.key) n.right = insert(n.right, key);
            if (key == n.key) return n;
            updateInformation(n);
            return makeBalance(n, key);
        }

        private Node minKeyNode(Node n) {
            if (n.left == null) return n;
            return minKeyNode(n.left);
        }

        private Node remove(Node n, int key) {
            if (n == null) return n;
            if (key < n.key) n.left = remove(n.left, key);
            if (key > n.key) n.right = remove(n.right, key);
            if (key == n.key) {
                if (n.left == null || n.right == null) {
                    /*
                    Case n.left or n.right is null:
                    => Replace n with the not null one
                     */
                    n = n.left == null ? n.right : n.left;
                } else {
                    /*
                    Case n.left and n.right is not null:
                    => Find min node x in the n.right
                    => Replace n.key with x.key
                    => Remove x in the n.right
                     */
                    Node tmp = minKeyNode(n.right);
                    n.key = tmp.key;
                    n.right = remove(n.right, tmp.key);
                }
            }
            updateInformation(n);
            return makeBalance(n, key);
        }

        // < insert and remove

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
                countOccurenceOfKeysLessThanResult += keyOccurenceMap.get(n.key);
                if (n.left != null)
                    countOccurenceOfKeysLessThanResult += n.left.childOccurence + keyOccurenceMap.get(n.left.key);
                countOccurenceOfKeysLessThanUtil(n.right, key);
            } else if (key < n.key) {
                countOccurenceOfKeysLessThanUtil(n.left, key);
            } else {
                if (n.left != null)
                    countOccurenceOfKeysLessThanResult += n.left.childOccurence + keyOccurenceMap.get(n.left.key);
                return;
            }
        }

        // < count-occurence-of-keys-less-than

        // > find-maximum-key-less-than

        Integer findMaximumKeyLessThanResult;

        private void findMaximumKeyLessThan(Node n, int key) {
            if (n == null) return;
            if (key > n.key) {
                findMaximumKeyLessThanResult = n.key;
                findMaximumKeyLessThan(n.right, key);
            } else if (key < n.key) {
                findMaximumKeyLessThan(n.left, key);
            } else {
                findMaximumKeyLessThan(n.left, key);
            }
        }

        // < find-maximum-key-less-than
    }
}
