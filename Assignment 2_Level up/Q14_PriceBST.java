import java.util.ArrayList;
import java.util.List;

public class Q14_PriceBST {

    static class Node {
        int price;
        Node left, right;
        Node(int price) { this.price = price; }
    }

    public static Node insertIterative(Node root, int price) {
        Node newNode = new Node(price);
        if (root == null) return newNode;

        Node curr = root, parent = null;
        while (curr != null) {
            parent = curr;
            if (price < curr.price) curr = curr.left;
            else curr = curr.right;
        }

        if (price < parent.price) parent.left = newNode;
        else parent.right = newNode;

        return root;
    }

    public static void rangeSearch(Node root, int lo, int hi, List<Integer> result) {
        if (root == null) return;
        if (root.price > lo) rangeSearch(root.left, lo, hi, result);
        if (root.price >= lo && root.price <= hi) result.add(root.price);
        if (root.price < hi) rangeSearch(root.right, lo, hi, result);
    }

    public static Integer floor(Node root, int key) {
        Integer res = null;
        while (root != null) {
            if (root.price == key) return root.price;
            if (root.price > key) {
                root = root.left;
            } else {
                res = root.price;
                root = root.right;
            }
        }
        return res;
    }

    public static Integer ceiling(Node root, int key) {
        Integer res = null;
        while (root != null) {
            if (root.price == key) return root.price;
            if (root.price < key) {
                root = root.right;
            } else {
                res = root.price;
                root = root.left;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] prices = {1200, 450, 2500, 300, 800, 1800, 3200, 650, 999};
        Node root = null;
        for (int p : prices) root = insertIterative(root, p);

        List<Integer> inRange = new ArrayList<>();
        rangeSearch(root, 500, 1500, inRange);
        System.out.println("Range (500, 1500): " + inRange);
        System.out.println("Floor 1000: " + floor(root, 1000));
        System.out.println("Ceiling 2000: " + ceiling(root, 2000));
    }
}