class Patient {
    String name;
    Patient prev;
    Patient next;

    Patient(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

public class HospitalWaitingList {
    Patient head;
    Patient tail;

    void addPatient(String name) {
        Patient newPatient = new Patient(name);
        if (head == null) {
            head = tail = newPatient;
        } else {
            tail.next = newPatient;
            newPatient.prev = tail;
            tail = newPatient;
        }
    }

    void displayForward() {
        System.out.println("\nWaiting List (Start to End):");
        Patient temp = head;
        while (temp != null) {
            System.out.print(temp.name + " <-> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        HospitalWaitingList list = new HospitalWaitingList();
        list.addPatient("Rahul");
        list.addPatient("Priya");
        list.addPatient("Amit");

        list.displayForward();
    }
}