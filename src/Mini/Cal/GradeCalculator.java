package Mini.Cal;

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of subjects:");
        int no = scanner.nextInt();

        int totalMarks = 0;

        System.out.println("Enter marks for each subject:");
        for (int i = 1; i <= no; i++) {
            System.out.print("Subject " + i + ": ");
            int marks = scanner.nextInt();
            totalMarks += marks;
        }

        System.out.println("Total Marks: " + totalMarks);

        double averagePercentage = (double) totalMarks / no;
        System.out.println("Average Percentage: " + averagePercentage + "%");

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
