import java.util.ArrayList;
import java.util.List;

public class Q13_HackathonTeamBuilder {

    static class Employee {
        String name;
        int cost;
        Employee(String name, int cost) { this.name = name; this.cost = cost; }
    }

    static int callCountPruned = 0;

    public static void findTeams(List<Employee> emps, int k, int budget, int idx, List<Employee> current, int currentCost) {
        callCountPruned++;

        if (current.size() == k) {
            System.out.print("Valid Team: ");
            for (Employee e : current) System.out.print(e.name + " ");
            System.out.println("(cost " + currentCost + ")");
            return;
        }

        if (idx == emps.size()) return;

        if (currentCost > budget || (current.size() + (emps.size() - idx) < k)) {
            return;
        }

        if (currentCost + emps.get(idx).cost <= budget) {
            current.add(emps.get(idx));
            findTeams(emps, k, budget, idx + 1, current, currentCost + emps.get(idx).cost);
            current.remove(current.size() - 1);
        }

        findTeams(emps, k, budget, idx + 1, current, currentCost);
    }

    public static void main(String[] args) {
        List<Employee> emps = new ArrayList<>();
        emps.add(new Employee("A", 4));
        emps.add(new Employee("B", 3));
        emps.add(new Employee("C", 5));
        emps.add(new Employee("D", 2));
        emps.add(new Employee("E", 6));

        findTeams(emps, 3, 10, 0, new ArrayList<>(), 0);
        System.out.println("Pruned Recursive Calls: " + callCountPruned);
    }
}