import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class TextFileManager {


    public static void saveAllTasks(ArrayList<StudyTask> list) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FileHelper.textFile, false));

            for (int i = 0; i < list.size(); i++) {
                StudyTask task = list.get(i);

                writer.println(task.getTaskType());
                writer.println(task.title);
                writer.println(task.dueDate);
                writer.println(task.priority);
                writer.println(task.completed);

              
                if (task instanceof DailyTask) {
                    DailyTask d = (DailyTask) task;
                    writer.println(d.subject);

                } else if (task instanceof ExamPrep) {
                    ExamPrep e = (ExamPrep) task;
                    writer.println(e.examName);
                    writer.println(e.studyHoursNeeded);

                } else if (task instanceof ProjectTask) {
                    ProjectTask p = (ProjectTask) task;
                    writer.println(p.groupMembers);
                    writer.println(p.percentComplete);
                }
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving to text file: " + e.getMessage());
        }
    }

    
    public static ArrayList<StudyTask> loadAllTasks() {
        ArrayList<StudyTask> list = new ArrayList<StudyTask>();

        try {
            File file = new File(FileHelper.textFile);

            if (!file.exists() || file.length() == 0) {
                return list;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String type      = scanner.nextLine();
                String title     = scanner.nextLine();
                String dueDate   = scanner.nextLine();
                int priority     = Integer.parseInt(scanner.nextLine());
                boolean completed = Boolean.parseBoolean(scanner.nextLine());

                StudyTask task = null;

                if (type.equals("DailyTask")) {
                    String subject = scanner.nextLine();
                    task = new DailyTask(title, dueDate, priority, subject);

                } else if (type.equals("ExamPrep")) {
                    String examName = scanner.nextLine();
                    int hours = Integer.parseInt(scanner.nextLine());
                    task = new ExamPrep(title, dueDate, priority, examName, hours);

                } else if (type.equals("ProjectTask")) {
                    String members = scanner.nextLine();
                    int percent = Integer.parseInt(scanner.nextLine());
                    task = new ProjectTask(title, dueDate, priority, members, percent);
                }

                if (task != null) {
                    if (completed) {
                        task.markCompleted();
                    }
                    list.add(task);
                }
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Error loading from text file: " + e.getMessage());
        }

        return list;
    }

    
    public static void addTask(StudyTask newTask) {
        ArrayList<StudyTask> list = loadAllTasks();
        list.add(newTask);
        saveAllTasks(list);
        System.out.println("Task added successfully (Text File).");
    }

    
    public static void displayAllTasks() {
        ArrayList<StudyTask> list = loadAllTasks();

        if (list.size() == 0) {
            System.out.println("No tasks found in text file.");
            return;
        }

        System.out.println("\n===== All Tasks (Text File) =====");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).showDetails(); // Polymorphism happens here!
        }
    }
public static void deleteTask(String title) {
        ArrayList<StudyTask> list = loadAllTasks();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).title.equalsIgnoreCase(title)) {
                list.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            saveAllTasks(list);
            System.out.println("Task deleted successfully (Text File).");
        } else {
            System.out.println("Task not found with title: " + title);
        }
    }

  
    public static void markTaskCompleted(String title) {
        ArrayList<StudyTask> list = loadAllTasks();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).title.equalsIgnoreCase(title)) {
                list.get(i).markCompleted();
                found = true;
                break;
            }
        }

        if (found) {
            saveAllTasks(list);
            System.out.println("Task marked as completed (Text File).");
        } else {
            System.out.println("Task not found with title: " + title);
        }
    }
}
