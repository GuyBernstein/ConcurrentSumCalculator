import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Constants
        final String ENTER_MEMBERS_PROMPT = "Enter the number of members in the array (n): ";
        final String ENTER_PROCESSES_PROMPT = "Enter the number of processes (m): ";
        final int MIN_RANDOM_NUMBER = 1;
        final int MAX_RANDOM_NUMBER = 100;
        final String ARRAY_FORMAT = "The array with (n = %d) random numbers:\n%s\nThe task with (m = %d) processes:\n";
        final String NO_MEMBERS_MESSAGE = "Final sum: No members";
        final String FINAL_SUM_MESSAGE = "Final sum: %d";
        final int NO_MEMBERS = -1;
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Read the number of members in the array (n) from the user
        int n = readPositiveInteger(scanner, ENTER_MEMBERS_PROMPT);
        // Read the number of processes (m) from the user
        int m = readPositiveInteger(scanner, ENTER_PROCESSES_PROMPT);

        // Create an array to store the initial members
        Integer[] initialMembers = new Integer[n];
        // Populate the array with random numbers between MIN_RANDOM_NUMBER and MAX_RANDOM_NUMBER
        for (int i = 0; i < n; i++) {
            initialMembers[i] = (int) (Math.random() *
                    (MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER + 1)) + MIN_RANDOM_NUMBER;
        }
        // Print the initial array and the number of processes
        System.out.printf(ARRAY_FORMAT, n, Arrays.toString(initialMembers), m);

        // Create a MemberPool object with the initial members
        NumberPool numberPool = new NumberPool(initialMembers);

        // Create an array to store the threads (processes)
        Thread[] processes = new Thread[m];
        // Create and start each thread with a SummaryProcess runnable
        for (int i = 0; i < m; i++) {
            processes[i] = new Thread(new SumCalculatorThread(numberPool));
        }
        for (int i = m-1; i >=0; i--) {
            processes[i].start();
        }

        // Wait for all threads to complete their execution
        for (int i = 0; i < m; i++) {
            try {
                processes[i].join();
            } catch (InterruptedException e) {
                System.out.println("main interrupted" );
            }
        }

        // Get the final sum from the memberPool
        int finalSum = numberPool.getSum();
        // Print the final sum, or NO_MEMBERS_MESSAGE if the sum is equal to NO_MEMBERS
        if (finalSum == NO_MEMBERS) {
            System.out.println(NO_MEMBERS_MESSAGE);
        } else {
            System.out.printf(FINAL_SUM_MESSAGE, finalSum);
        }
    }

    private static int readPositiveInteger(Scanner scanner, String prompt) {//function to read the input correctly
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. " + prompt);
                scanner.next();
            }
            value = scanner.nextInt();
        } while (value <= 0);
        return value;
    }

}
