import java.util.ArrayList;
import java.util.List;

public class Q12_DiskAnalyzer {

    static class FsNode {
        String name;
        long size;
        List<FsNode> children = new ArrayList<>();
        boolean isFolder;

        FsNode(String name, long size, boolean isFolder) {
            this.name = name;
            this.size = size;
            this.isFolder = isFolder;
        }
    }

    public static long calculateTotalSize(FsNode node) {
        if (!node.isFolder) return node.size;
        long sum = 0;
        for (FsNode child : node.children) {
            sum += calculateTotalSize(child);
        }
        node.size = sum;
        return sum;
    }

    public static void printTree(FsNode node, String indent) {
        System.out.println(indent + node.name + " (" + node.size + " KB)");
        if (node.isFolder) {
            for (FsNode child : node.children) {
                printTree(child, indent + "  ");
            }
        }
    }

    public static void findPath(FsNode node, String target, String currentPath) {
        String fullPath = currentPath + "/" + node.name;
        if (node.name.equals(target)) {
            System.out.println("Found: " + fullPath);
        }
        if (node.isFolder) {
            for (FsNode child : node.children) {
                findPath(child, target, fullPath);
            }
        }
    }

    public static void main(String[] args) {
        FsNode root = new FsNode("project", 0, true);
        FsNode src = new FsNode("src", 0, true);
        src.children.add(new FsNode("Main.java", 12, false));
        src.children.add(new FsNode("Utils.java", 8, false));

        FsNode model = new FsNode("model", 0, true);
        model.children.add(new FsNode("User.java", 5, false));
        src.children.add(model);

        root.children.add(src);
        root.children.add(new FsNode("build.gradle", 2, false));

        calculateTotalSize(root);
        printTree(root, "");
        findPath(root, "User.java", "");
    }
}