public class Q10_LargeNumberAddition {

    static class Node {
        int val;
        Node next;
        Node(int val) { this.val = val; }
    }

    public static Node addListsRecursive(Node l1, Node l2) {
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        if (len1 < len2) { l1 = padZeros(l1, len2 - len1); }
        else if (len2 < len1) { l2 = padZeros(l2, len1 - len2); }

        Wrapper result = addHelper(l1, l2);
        if (result.carry > 0) {
            Node head = new Node(result.carry);
            head.next = result.node;
            return head;
        }
        return result.node;
    }

    static class Wrapper {
        Node node;
        int carry;
        Wrapper(Node node, int carry) { this.node = node; this.carry = carry; }
    }

    private static Wrapper addHelper(Node l1, Node l2) {
        if (l1 == null && l2 == null) return new Wrapper(null, 0);

        Wrapper sub = addHelper(l1.next, l2.next);
        int sum = l1.val + l2.val + sub.carry;
        Node curr = new Node(sum % 10);
        curr.next = sub.node;
        return new Wrapper(curr, sum / 10);
    }

    private static int getLength(Node head) {
        int len = 0;
        while (head != null) { len++; head = head.next; }
        return len;
    }

    private static Node padZeros(Node head, int count) {
        Node curr = head;
        for (int i = 0; i < count; i++) {
            Node z = new Node(0);
            z.next = curr;
            curr = z;
        }
        return curr;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? "->" : ""));
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node l1 = new Node(7); l1.next = new Node(2); l1.next.next = new Node(4); l1.next.next.next = new Node(3);
        Node l2 = new Node(5); l2.next = new Node(6); l2.next.next = new Node(4);

        System.out.print("Result: ");
        printList(addListsRecursive(l1, l2));
    }
}