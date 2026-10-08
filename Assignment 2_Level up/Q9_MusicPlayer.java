public class Q9_MusicPlayer {

    static class Node {
        String song;
        Node prev, next;
        Node(String song) { this.song = song; }
    }

    private Node head = null;
    private Node current = null;
    private boolean repeat = false;

    public void add(String song) {
        Node newNode = new Node(song);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
            current = head;
        } else {
            Node tail = head.prev;
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = head;
            head.prev = newNode;
        }
    }

    public void next() {
        if (current == null) return;
        if (!repeat && current.next == head) return;
        current = current.next;
    }

    public void prev() {
        if (current == null) return;
        if (!repeat && current == head) return;
        current = current.prev;
    }

    public void playNext(String song) {
        if (current == null) { add(song); return; }
        Node newNode = new Node(song);
        Node nextNode = current.next;

        current.next = newNode;
        newNode.prev = current;
        newNode.next = nextNode;
        nextNode.prev = newNode;
    }

    public void removeCurrent() {
        if (current == null) return;
        if (current.next == current) {
            head = null; current = null;
            return;
        }
        Node prevNode = current.prev;
        Node nextNode = current.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        if (current == head) head = nextNode;
        current = nextNode;
    }

    public void setRepeat(boolean r) { this.repeat = r; }

    public void printState() {
        if (head == null) { System.out.println("Empty"); return; }
        Node temp = head;
        StringBuilder sb = new StringBuilder();
        do {
            if (temp == current) sb.append("[").append(temp.song).append("] ");
            else sb.append(temp.song).append(" ");
            temp = temp.next;
        } while (temp != head);
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        Q9_MusicPlayer player = new Q9_MusicPlayer();
        player.add("A"); player.add("B"); player.add("C"); player.add("D");
        player.printState();

        player.next();
        player.playNext("X");
        player.printState();

        player.next();
        player.removeCurrent();
        player.printState();
    }
}