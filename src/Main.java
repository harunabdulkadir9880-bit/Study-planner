import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        FileHelper.setupFiles();
        DbHelper.setupDatabase();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("\nWelcome to the Personal Study Planner!");

        while (choice != 6) {

            printMainMenu();

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (choice == 1) {
                menuAddTask(scanner);

            } else if (choice == 2) {
                menuDisplayAllTasks(scanner);

            } else if (choice == 3) {
                menuDeleteTask(scanner);

            } else if (choice == 4) {
                menuMarkCompleted(scanner);

            } else if (choice == 5) {
                menuFileProperties(scanner);

            } else if (choice == 6) {
                System.out.println("Goodbye! Happy studying!");

            } else {
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        scanner.close();
    }

    static void printMainMenu() {
        System.out.println("\n         MAIN MENU         ");
        System.out.println("1. Add Task");
        System.out.println("2. Display All Tasks");
        System.out.println("3. Delete Task");
        System.out.println("4. Mark Task as Completed");
        System.out.println("5. Show File Properties");
        System.out.println("6. Exit");
        System.out.println("                            ");
        System.out.print("Enter your choice: ");
    }

}
