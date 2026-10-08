import java.util.*;

public class Q15_OrgChart {

    static class Employee {
        int id;
        String name;
        int managerId;
        List<Employee> reports = new ArrayList<>();

        Employee(int id, String name, int managerId) {
            this.id = id;
            this.name = name;
            this.managerId = managerId;
        }
    }

    public static void printLevelOrder(Employee root) {
        if (root == null) return;
        Queue<Employee> q = new LinkedList<>();
        q.add(root);

        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            System.out.print("L" + level + ": ");
            for (int i = 0; i < size; i++) {
                Employee e = q.poll();
                System.out.print(e.name + " ");
                q.addAll(e.reports);
            }
            System.out.println();
            level++;
        }
    }

    public static Employee findLCA(Employee root, int id1, int id2) {
        if (root == null || root.id == id1 || root.id == id2) return root;

        List<Employee> matches = new ArrayList<>();
        for (Employee child : root.reports) {
            Employee res = findLCA(child, id1, id2);
            if (res != null) matches.add(res);
        }

        if (matches.size() == 2) return root;
        return matches.isEmpty() ? null : matches.get(0);
    }

    public static void main(String[] args) {
        Map<Integer, Employee> map = new HashMap<>();
        int[][] raw = {{1, 0}, {2, 1}, {3, 1}, {4, 2}, {5, 2}, {6, 3}, {7, 4}};
        String[] names = {"", "Asha", "Ravi", "Meera", "Karan", "Neha", "Arjun", "Pooja"};

        for (int[] r : raw) map.put(r[0], new Employee(r[0], names[r[0]], r[1]));

        Employee root = null;
        for (Employee e : map.values()) {
            if (e.managerId == 0) root = e;
            else if (map.containsKey(e.managerId)) map.get(e.managerId).reports.add(e);
        }

        printLevelOrder(root);
        Employee lca = findLCA(root, 7, 5);
        System.out.println("Common manager (Pooja, Neha): " + (lca != null ? lca.name : "None"));
    }
}
