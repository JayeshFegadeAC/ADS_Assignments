import java.util.LinkedList;
import java.util.Queue;

public class PrinterQueue {
    public static void main(String[] args) {
        Queue<String> printQueue = new LinkedList<>();

        // Adding print jobs
        printQueue.add("Document1.pdf");
        printQueue.add("ProjectReport.docx");
        printQueue.add("Photo.png");

        System.out.println("--- Processing Printer Queue ---");

        while (!printQueue.isEmpty()) {
            String currentDoc = printQueue.poll();
            System.out.println("Printing: " + currentDoc);
        }

        System.out.println("All print jobs completed!");
    }
}