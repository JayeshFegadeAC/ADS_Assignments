import java.util.Scanner;

public class RailwayBooking {
    public static void main(String[] args) {
        // 0 means Available, 1 means Booked
        int[] seats = new int[5]; 
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Railway Berth Booking System ---");
        
        while (true) {
            System.out.println("\nCurrent Seat Status:");
            for (int i = 0; i < seats.length; i++) {
                System.out.println("Seat " + (i + 1) + ": " + (seats[i] == 0 ? "Available" : "Booked"));
            }

            System.out.print("\nEnter seat number to book (1-5) or 0 to exit: ");
            int seatNo = sc.nextInt();

            if (seatNo == 0) {
                break;
            }

            if (seatNo < 1 || seatNo > 5) {
                System.out.println("Invalid seat number!");
            } else if (seats[seatNo - 1] == 1) {
                System.out.println("Seat already booked!");
            } else {
                seats[seatNo - 1] = 1;
                System.out.println("Seat " + seatNo + " booked successfully!");
            }
        }
        sc.close();
    }
}