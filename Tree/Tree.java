import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }
    }

    static int idx = 0;

    public static Node construct(int[] arr) {

        if (idx >= arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx++]);
        node.left = construct(arr);
        node.right = construct(arr);

        return node;

    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((node.left == null ? "." : node.left.data + ""));
        sb.append(" <- " + node.data + " -> ");
        sb.append((node.right == null ? "." : node.right.data + ""));
        System.out.println(sb);
        display(node.left);
        display(node.right);

    }

    public static int size(Node node) {

        return node == null ? 0 : size(node.left) + size(node.right) + 1;

    }

    public static int height(Node node) {

        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;

    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        return find(node.left, data) || find(node.right, data);
    }

    public static boolean nodeToRootPath(Node node, int data, ArrayList<Node> ans) {

        if (node == null)
            return false;

        if (node.data == data) {
            ans.add(node);
            return true;
        }

        boolean res = nodeToRootPath(node.left, data, ans) || nodeToRootPath(node.right, data, ans);
        if (res) {
            ans.add(node);
        }
        return res;

    }

    public static boolean rootToNodePath(Node node, int data, ArrayList<Node> ans) {

        if (node == null)
            return false;

        if (node.data == data) {
            ans.add(node);
            return true;
        }
        ans.add(node);

        boolean res = rootToNodePath(node.left, data, ans) || rootToNodePath(node.right, data, ans);
        if (!res) {
            ans.remove(ans.size() - 1);
        }
        return res;
    }

    public static void kDown(Node node, Node block, int k, ArrayList<Integer> ans) {

        if (node == null || node == block || k < 0)
            return;

        if (k == 0)
            ans.add(node.data);

        kDown(node.left, block, k - 1, ans);
        kDown(node.right, block, k - 1, ans);

    }

    public static void kFar(Node node, int data, int k) {
        ArrayList<Node> list = new ArrayList<>();
        nodeToRootPath(node, data, list);

        Node prev = null;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            kDown(list.get(i), prev, k - i, ans);
        }
        for (int e : ans) {
            System.out.println(e);
        }
    }

    public static int kFar2(Node node, int data, int k, ArrayList<Integer> ans) {

        if (node == null)
            return -1;

        if (node.data == data) {
            kDown(node, null, k, ans);
            return 1;
        }

        int ld = kFar2(node.left, data, k, ans);
        if (ld != -1) {
            kDown(node, node.left, k - ld, ans);
            return ld + 1;
        }

        int rd = kFar2(node.right, data, k, ans);
        if (rd != -1) {
            kDown(node, node.right, k - rd, ans);
            return rd + 1;
        }
        return -1;
    }

    // Calculate left diameter(ld), right diameter(rd), left height(lh) and
    // right height(rh) then the
    // diameter will be max of ld,rd,lh+rh+2;

    public static int diameter_01(Node node) {

        if (node == null)
            return 0;

        int ld = diameter_01(node.left);
        int rd = diameter_01(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    // For optimizing diameter claculate and store diameter and height of all
    // locations

    public static int[] diameter_02(Node node) {

        if (node == null)
            return new int[] { 0, -1 };

        int[] lres = diameter_02(node.left);
        int[] rres = diameter_02(node.right);

        int dia = Math.max(Math.max(lres[0], rres[0]), lres[1] + rres[1] + 2);

        int hei = Math.max(lres[1], rres[1]) + 1;

        return new int[] { dia, hei };

    }

    // Leaf to Leaf max path sum

    static int maxLL = -(int) 1e8;

    public static int leafToLeaf(Node node) {

        if (node == null)
            return -(int) 1e8;
        if (node.left == null && node.right == null)
            return node.data;
        int nodeToLeafLeft = leafToLeaf(node.left);
        int nodeToLeafright = leafToLeaf(node.right);

        if (node.left != null && node.right != null) { // Update maxLL only when you are sure that the node is a leaf
                                                       // node otherwise just update max leaf to node sum (*)
            maxLL = Math.max(maxLL, nodeToLeafLeft + nodeToLeafright + node.data);
        }

        return Math.max(nodeToLeafLeft, nodeToLeafright) + node.data; // (*)

    }

    public static void BFS_01(Node node) {

        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);

        while (que.size() != 0) {
            Node vtx = que.removeFirst();
            System.out.print(vtx.data + " ");
            if (vtx.left != null)
                que.addLast(vtx.left);
            if (vtx.right != null)
                que.addLast(vtx.right);

        }

    }

    public static void BFS_02(Node node) {

        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        que.add(null);

        while (que.size() != 1) {
            Node vtx = que.removeFirst();
            System.out.print(vtx.data + " ");
            if (vtx.left != null)
                que.addLast(vtx.left);
            if (vtx.right != null)
                que.addLast(vtx.right);

            if (que.getFirst() == null) {
                System.out.println();
                que.removeFirst();
                que.addLast(null);
            }

        }

    }

    public static void BFS_03(Node node) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(node);
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                Node vtx = que.removeFirst();
                System.out.print(vtx.data + " ");
                if (vtx.left != null)
                    que.addLast(vtx.left);
                if (vtx.right != null)
                    que.addLast(vtx.right);
            }
            System.out.println();
            level++;
        }
    }

    public static List<Integer> leftView(Node node) {

        if (node == null)
            return new ArrayList<>();

        LinkedList<Node> que = new LinkedList<>();
        que.add(node);
        List<Integer> ans = new ArrayList<>();

        while (que.size() != 0) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                Node vtx = que.removeFirst();
                if (i == 0) {
                    ans.add(vtx.data);
                }
                if (vtx.left != null)
                    que.add(vtx.left);
                if (vtx.right != null)
                    que.add(vtx.right);
            }
        }
        return ans;
    }

    public static List<Integer> rightView(Node node) {

        if (node == null)
            return new ArrayList<>();

        LinkedList<Node> que = new LinkedList<>();
        que.add(node);
        List<Integer> ans = new ArrayList<>();

        while (que.size() != 0) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                Node vtx = que.removeFirst();
                if (i == size - 1) {
                    ans.add(vtx.data);
                }
                if (vtx.left != null)
                    que.add(vtx.left);
                if (vtx.right != null)
                    que.add(vtx.right);
            }
        }
        return ans;
    }

    // helper for vertical order

    public static void width(Node node, int level, int[] maxMin) {

        if (node == null)
            return;

        maxMin[0] = Math.max(maxMin[0], level);
        maxMin[1] = Math.min(maxMin[1], level);

        width(node.left, level - 1, maxMin);
        width(node.right, level + 1, maxMin);

    }

    // helper for vertical order
    public static class pair {
        Node node = null;
        int val = 0;

        pair(Node node, int val) {
            this.node = node;
            this.val = val;
        }
    }

    public static List<List<Integer>> verticalOrder(Node node) {

        if (node == null)
            return new ArrayList<>();
        int[] maxMin = new int[2];
        width(node, 0, maxMin);
        int n = maxMin[0] - maxMin[1] + 1; // Calculate width of tree from min max max values of tree extremes
        List<List<Integer>> ans = new ArrayList<>(n); // List<List<Integer>> for vertical order
        for (int i = 0; i < n; i++) {
            ans.add(new ArrayList<>());
        }

        LinkedList<pair> que = new LinkedList<>();

        que.addLast(new pair(node, -maxMin[1])); // que contains node and its position

        while (que.size() != 0) { // Simple level order traversal
            int size = que.size();
            while (size-- > 0) {

                pair vtx = que.removeFirst();
                ans.get(vtx.val).add(vtx.node.data);

                if (vtx.node.left != null) {
                    que.addLast(new pair(vtx.node.left, vtx.val - 1));
                }
                if (vtx.node.right != null) {
                    que.addLast(new pair(vtx.node.right, vtx.val + 1));
                }

            }
        }
        return ans;
    }

    // To find the top view first find the vertical order traversal of a tree. Then
    // all the first elements in List<List<Integer>>
    // will be the top view

    public static void topView(Node root) {
        List<Integer> top = new ArrayList<>();
        List<List<Integer>> ans = verticalOrder(root);

        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                if (j == 0) {
                    top.add(ans.get(i).get(j));
                } else {
                    continue;
                }
            }
        }
        for (int e : top) {
            System.out.print(e + " ");
        }
    }

    // To find the bottom view first find the vertical order traversal of a tree.
    // Then
    // all the last elements in List<List<Integer>>
    // will be the top view

    public static void bottomView(Node root) {
        List<Integer> top = new ArrayList<>();
        List<List<Integer>> ans = verticalOrder(root);

        for (int i = 0; i < ans.size(); i++) {
            for (int j = 0; j < ans.get(i).size(); j++) {
                if (j == ans.get(i).size() - 1) {
                    top.add(ans.get(i).get(j));
                } else {
                    continue;
                }
            }
        }
        for (int e : top) {
            System.out.print(e + " ");
        }
    }

    public static void construct() {
        int[] arr = { 10, 20, 40, -1, -1, 50, 80, -1, -1, 90, -1, -1, 30, 60, 100, -1, -1, -1, 70, 110, -1, -1, 120, -1,
                -1 };
        Node root = construct(arr);
        display(root);
        System.out.println();
        // topView(root);
        // bottomView(root);

    }

    public static void main(String[] args) {
        construct();
    }
}