import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ObjectFileManager {

   
    public static void saveAllTasks(ArrayList<StudyTask> list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileHelper.objectFile, false));

         
            oos.writeObject(list);

            oos.close();

        } catch (Exception e) {
            System.out.println("Error saving to object file: " + e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    public static ArrayList<StudyTask> loadAllTasks() {
        ArrayList<StudyTask> list = new ArrayList<StudyTask>();

        try {
            File file = new File(FileHelper.objectFile);

            if (!file.exists() || file.length() == 0) {
                return list;
            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

          
            list = (ArrayList<StudyTask>) ois.readObject();

            ois.close();

        } catch (Exception e) {
            System.out.println("Error loading from object file: " + e.getMessage());
        }

        return list;
    }


    public static void addTask(StudyTask newTask) {
        ArrayList<StudyTask> list = loadAllTasks();
        list.add(newTask);
        saveAllTasks(list);
        System.out.println("Task added successfully (Object File).");
    }

   
    public static void displayAllTasks() {
        ArrayList<StudyTask> list = loadAllTasks();

        if (list.size() == 0) {
            System.out.println("No tasks found in object file.");
            return;
        }

        System.out.println("\n===== All Tasks (Object File) =====");
        for (int i = 0; i < list.size(); i++) {
          
            list.get(i).showDetails(); 
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
            System.out.println("Task deleted successfully (Object File).");
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
            System.out.println("Task marked as completed (Object File).");
        } else {
            System.out.println("Task not found with title: " + title);
        }
    }
}