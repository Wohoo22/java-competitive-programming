import java.util.HashMap;
import java.util.Map;

class Leetcode {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(
                        2,
                        new TreeNode(
                                3,
                                new TreeNode(
                                        4,
                                        new TreeNode(5),
                                        null
                                ),
                                null
                        ),
                        null
                ),
                null
        );
        System.out.println(maxPathSum(root));
    }

    static Map<TreeNode, Integer> maxPathOfNode = new HashMap<>();

    static void findMaxPathOfNode(TreeNode curNode) {
        if (curNode == null) return;
        findMaxPathOfNode(curNode.left);
        findMaxPathOfNode(curNode.right);
        int left = curNode.left == null ? 0 : maxPathOfNode.get(curNode.left);
        int right = curNode.right == null ? 0 : maxPathOfNode.get(curNode.right);
        if (left < 0) left = 0;
        if (right < 0) right = 0;
        maxPathOfNode.put(curNode, Math.max(
                curNode.val + left,
                curNode.val + right
        ));
    }

    static int maxPath;

    static void findMaxPath(TreeNode curNode) {
        if (curNode == null) return;
        findMaxPath(curNode.left);
        findMaxPath(curNode.right);
        int left = curNode.left == null ? Integer.MIN_VALUE : maxPathOfNode.get(curNode.left);
        int right = curNode.right == null ? Integer.MIN_VALUE : maxPathOfNode.get(curNode.right);
        int curPath = curNode.val;
        if (left > 0) curPath += left;
        if (right > 0) curPath += right;
        maxPath = Math.max(maxPath, curPath);

    }

    public static int maxPathSum(TreeNode root) {
        findMaxPathOfNode(root);
        maxPath = Integer.MIN_VALUE;
        findMaxPath(root);
        return maxPath;
    }
}