class Coach {
    String name;
    Coach next;

    Coach(String name) {
        this.name = name;
        this.next = null;
    }
}

public class TrainCoach {
    Coach head;

    void addCoach(String name) {
        Coach newCoach = new Coach(name);
        if (head == null) {
            head = newCoach;
            return;
        }
        Coach temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newCoach;
    }

    void displayTrain() {
        Coach temp = head;
        System.out.print("Engine -> ");
        while (temp != null) {
            System.out.print("[" + temp.name + "] -> ");
            temp = temp.next;
        }
        System.out.println("End");
    }

    public static void main(String[] args) {
        TrainCoach train = new TrainCoach();
        train.addCoach("S1");
        train.addCoach("S2");
        train.addCoach("S3");

        System.out.println("Train Structure:");
        train.displayTrain();
    }
}