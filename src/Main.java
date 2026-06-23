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
    static int askStorageType(Scanner scanner) {
        System.out.println("\nChoose storage type:");
        System.out.println("1. Text File");
        System.out.println("2. Binary File");
        System.out.println("3. Object File (Serialization)");
        System.out.println("4. Database (JDBC / MySQL)");
        System.out.print("Enter choice: ");

        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid input. Defaulting to text file.");
            return 1;
        }
    }
    static void menuAddTask(Scanner scanner) {
        System.out.println("\n--- Add New Task ---");
        System.out.println("What type of task is this?");
        System.out.println("1. Daily Task");
        System.out.println("2. Exam Prep");
        System.out.println("3. Project Task");
        System.out.print("Enter choice: ");

        int taskTypeChoice;
        try {
            taskTypeChoice = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Invalid input.");
            return;
        }

        try {
            System.out.print("Enter Title             : ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter Due Date (e.g. 2026-06-25): ");
            String dueDate = scanner.nextLine().trim();

            System.out.print("Enter Priority (1=Low, 2=Medium, 3=High): ");
            int priority = Integer.parseInt(scanner.nextLine().trim());


            TaskValidator.validate(title, priority);

            StudyTask newTask = null;

            if (taskTypeChoice == 1) {
                System.out.print("Enter Subject           : ");
                String subject = scanner.nextLine().trim();
                newTask = new DailyTask(title, dueDate, priority, subject);

            } else if (taskTypeChoice == 2) {
                System.out.print("Enter Exam Name         : ");
                String examName = scanner.nextLine().trim();
                System.out.print("Enter Study Hours Needed: ");
                int hours = Integer.parseInt(scanner.nextLine().trim());
                newTask = new ExamPrep(title, dueDate, priority, examName, hours);

            } else if (taskTypeChoice == 3) {
                System.out.print("Enter Group Members      : ");
                String members = scanner.nextLine().trim();
                System.out.print("Enter Percent Complete (0-100): ");
                int percent = Integer.parseInt(scanner.nextLine().trim());
                newTask = new ProjectTask(title, dueDate, priority, members, percent);

            } else {
                System.out.println("Invalid task type.");
                return;
            }

            int storageType = askStorageType(scanner);

            if (storageType == 1) {
                TextFileManager.addTask(newTask);
            } else if (storageType == 2) {
                BinaryFileManager.addTask(newTask);
            } else if (storageType == 3) {
                ObjectFileManager.addTask(newTask);
            } else if (storageType == 4) {
                DatabaseManager.addTask(newTask);
            } else {
                System.out.println("Invalid storage type.");
            }

        } catch (InvalidtaskException e) {
            System.out.println("Could not add task: " + e.getMessage());
        } catch (NumberFormatException e) {

            System.out.println("Please enter numbers where required (priority, hours, percent).");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    static void menuDisplayAllTasks(Scanner scanner) {
        int storageType = askStorageType(scanner);

        if (storageType == 1) {
            TextFileManager.displayAllTasks();
        } else if (storageType == 2) {
            BinaryFileManager.displayAllTasks();
        } else if (storageType == 3) {
            ObjectFileManager.displayAllTasks();
        } else if (storageType == 4) {
            DatabaseManager.displayAllTasks();
        } else {
            System.out.println("Invalid storage type.");
        }
    }

    static void menuDeleteTask(Scanner scanner) {
        System.out.println("\n--- Delete Task ---");
        System.out.print("Enter the title of the task to delete: ");
        String title = scanner.nextLine().trim();

        int storageType = askStorageType(scanner);

        if (storageType == 1) {
            TextFileManager.deleteTask(title);
        } else if (storageType == 2) {
            BinaryFileManager.deleteTask(title);
        } else if (storageType == 3) {
            ObjectFileManager.deleteTask(title);
        } else if (storageType == 4) {
            DatabaseManager.deleteTask(title);
        } else {
            System.out.println("Invalid storage type.");
        }
    }




}
