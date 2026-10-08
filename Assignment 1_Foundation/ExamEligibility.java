public class ExamEligibility {
    public static void main(String[] args) {
        
        char[] attendance = {'P', 'P', 'A', 'P', 'P', 'P', 'A', 'P'};
        
        int totalDays = attendance.length;
        int presentCount = 0;
        int currentStreak = 0;
        int maxStreak = 0;

        for (int i = 0; i < totalDays; i++) {
            if (attendance[i] == 'P') {
                presentCount++;
                currentStreak++;
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                }
            } else {
                currentStreak = 0;
            }
        }

        double percentage = (double) presentCount / totalDays * 100;
        System.out.println("Total Present: " + presentCount);
        System.out.println("Attendance Percentage: " + percentage + "%");
        System.out.println("Max Present Streak: " + maxStreak);

        if (percentage >= 75.0) {
            System.out.println("Status: Eligible for Exam");
        } else {
            System.out.println("Status: Not Eligible");
        }
    }
}