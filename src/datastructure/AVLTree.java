package datastructure;

public class AVLTree {
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static class Tree {
        Node root;

        int heightUtil(Node n) {
            return n == null ? 0 : n.height;
        }

        int height(Node n) {
            return n == null ? 0 : Math.max(heightUtil(n.left), heightUtil(n.right)) + 1;
        }

        /*
                     z                                       y
                    / \                                    /   \
                   y   m       Right Rotate (z)          x      z
                  / \          - - - - - - - - ->       / \    / \
                 x   n                                 k  p   n   m
               / \
              k   p
         */

        Node rightRotate(Node z) {
            Node y = z.left, n = y.right;
            y.right = z;
            z.left = n;
            y.height = height(y);
            z.height = height(z);
            return y;
        }

        /*
                 z                                 z
                / \                              /  \
               y   m     Left Rotate (y)        x    m
              / \        - - - - - - - - ->    / \
            n    x                            y   k
                / \                          / \
              p    k                        n   p
         */

        Node leftRotate(Node y) {
            Node x = y.right;
            Node p = x.left;
            x.left = y;
            y.right = p;
            y.height = height(y);
            x.height = height(x);
            return x;
        }

        int getBalance(Node n) {
            return n == null ? 0 : height(n.left) - height(n.right);
        }

        Node insert(Node n, int key) {
            if (n == null) return new Node(key);
            if (key < n.key) n.left = insert(n.left, key);
            if (key > n.key) n.right = insert(n.right, key);
            if (key == n.key) return n;
            n.height = height(n);
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
    }

    static void preOrder(Node node) {
        if (node != null) {
            System.out.print("Node: " + node.key + " ");
            if (node.left != null)
                System.out.println("Left: " + node.left.key);
            if (node.right != null)
                System.out.println("Right: " + node.right.key);
            System.out.println("________________________");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 60);
        tree.root = tree.insert(tree.root, 70);
        tree.root = tree.insert(tree.root, 80);
        tree.root = tree.insert(tree.root, 90);
        tree.root = tree.insert(tree.root, 100);

        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        preOrder(tree.root);
    }
}
