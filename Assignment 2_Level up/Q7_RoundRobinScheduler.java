import java.util.*;

public class Q7_RoundRobinScheduler {

    static class Process {
        String id;
        int burst;
        int remaining;
        int completionTime;

        Process(String id, int burst) {
            this.id = id;
            this.burst = burst;
            this.remaining = burst;
        }
    }

    public static void simulate(List<Process> processes, int quantum) {
        Queue<Process> readyQueue = new LinkedList<>(processes);
        int currentTime = 0;

        System.out.print("Timeline: | ");

        while (!readyQueue.isEmpty()) {
            Process p = readyQueue.poll();
            int runTime = Math.min(p.remaining, quantum);
            int startTime = currentTime;
            currentTime += runTime;
            p.remaining -= runTime;

            System.out.print(p.id + " " + startTime + "-" + currentTime + " | ");

            if (p.remaining > 0) {
                readyQueue.add(p);
            } else {
                p.completionTime = currentTime;
            }
        }
        System.out.println("\n");

        System.out.println("Process\tBurst\tCompletion\tTurnaround\tWaiting");
        double totalWaiting = 0;
        for (Process p : processes) {
            int turnaround = p.completionTime;
            int waiting = turnaround - p.burst;
            totalWaiting += waiting;
            System.out.println(p.id + "\t" + p.burst + "\t" + p.completionTime + "\t\t" + turnaround + "\t\t" + waiting);
        }
        System.out.printf("Average Waiting Time = %.2f\n", (totalWaiting / processes.size()));
    }

    public static void main(String[] args) {
        List<Process> procs = Arrays.asList(
            new Process("P1", 5),
            new Process("P2", 3),
            new Process("P3", 1)
        );
        simulate(procs, 2);
    }
}