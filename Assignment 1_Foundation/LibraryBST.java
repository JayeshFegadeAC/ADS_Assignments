class BookNode {
    int bookId;
    String title;
    BookNode left, right;

    BookNode(int id, String title) {
        this.bookId = id;
        this.title = title;
        left = right = null;
    }
}

public class LibraryBST {
    BookNode root;

    void insert(int id, String title) {
        root = insertRec(root, id, title);
    }

    BookNode insertRec(BookNode root, int id, String title) {
        if (root == null) {
            root = new BookNode(id, title);
            return root;
        }
        if (id < root.bookId) {
            root.left = insertRec(root.left, id, title);
        } else if (id > root.bookId) {
            root.right = insertRec(root.right, id, title);
        }
        return root;
    }

    // Inorder Traversal (Sorted display)
    void displayInOrder(BookNode root) {
        if (root != null) {
            displayInOrder(root.left);
            System.out.println("ID: " + root.bookId + " | Title: " + root.title);
            displayInOrder(root.right);
        }
    }

    public static void main(String[] args) {
        LibraryBST library = new LibraryBST();

        library.insert(103, "Java Programming");
        library.insert(101, "Data Structures");
        library.insert(105, "Algorithm Design");

        System.out.println("--- Library Catalogue (Sorted by Book ID) ---");
        library.displayInOrder(library.root);
    }
}