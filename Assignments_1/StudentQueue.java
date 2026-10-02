import java.util.ArrayList;
import java.util.Scanner;
public class StudentQueue {
    
    public static void main(String[] args) {
        
        ArrayList<Integer> queue = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        queue.add(105);
        queue.add(112);
        queue.add(108);
        queue.add(101);
        queue.add(115);

        System.out.println("Initial Queue: "+queue);

        if(queue.size()>0){
            int studentSubmitted = queue.remove(0);
            System.out.println("Student "+studentSubmitted+" submits.");
        }

        System.out.println("Queue becomes: "+queue);



        System.out.println("Search: Enter student Id: ");
        int searchId = sc.nextInt();

        if(queue.contains(searchId)){
            System.out.println("Output: Student "+searchId+" is waiting.");
        }else{
            System.out.println("Output: Student "+searchId+" is not waiting.");
        }

        System.out.println("Current number of students: "+queue.size());


    }
}
