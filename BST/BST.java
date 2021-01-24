import java.util.List;
import java.util.Stack;

public class BST {

    public static class Node {
        int data = 0;
        Node left = null;
        Node right = null;

        Node(int data) {
            this.data = data;
        }

    }

    public static Node construct(int[] arr, int si, int ei) {

        if (ei < si)
            return null;

        int mid = (si + ei) / 2;

        Node node = new Node(arr[mid]);
        node.left = construct(arr, si, mid - 1);
        node.right = construct(arr, mid + 1, ei);

        return node;

    }

    public static void display(Node node) {
        if (node == null)
            return;
        String str = "";
        str += node.left != null ? node.left.data : ".";
        str += " <- " + node.data + " -> ";
        str += node.right != null ? node.right.data : ".";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int height(Node node) {
        return node == null ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    public static int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    public static boolean find(Node node, int data) {
        Node curr = node;
        while (curr != null) {
            if (curr.data == data)
                return true;

            else if (curr.data > data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return false;
    }

    public static Node LCA(Node node, int p, int q) {
        Node curr = node;
        while (curr != null) {
            if (p < curr.data && q < curr.data)
                curr = curr.left;
            else if (p > curr.data && q > curr.data)
                curr = curr.right;
            else {
                return find(curr, p) && find(curr, q) ? curr : null;
            }
        }
        return null;
    }

    public static void searchInRange(Node node, int a, int b, List<Integer> ans) {

        if (node == null)
            return;
        searchInRange(node.left, a, b, ans);
        if (node.data >= a && node.data <= b)
            ans.add(node.data);
        searchInRange(node.right, a, b, ans);
    }

    // leftBoundary is -infinity and rightBoundary is +infinity
    static int idx = 0;

    public static Node treeUsingPreOrder(int[] arr, int leftBoundary, int rightBoundary) {

        if (idx == arr.length || arr[idx] < leftBoundary || arr[idx] > rightBoundary)
            return null;

        Node node = new Node(arr[idx++]);
        node.left = treeUsingPreOrder(arr, leftBoundary, node.data);
        node.right = treeUsingPreOrder(arr, node.data, rightBoundary);
        return node;

    }

    public static int heightTreeUsingPreorder(int[] arr, int lb, int rb) {

        if (idx == arr.length || arr[idx] < lb || arr[idx] > rb)
            return -1;

        int data = arr[idx++];
        int lh = heightTreeUsingPreorder(arr, lb, data);
        int rh = heightTreeUsingPreorder(arr, data, rb);
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {

        int[] arr = new int[15];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (i + 1) * 10;
        }

    }
}