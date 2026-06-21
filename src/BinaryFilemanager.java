import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BinaryFileManager {

    
    public static void saveAllTasks(ArrayList<StudyTask> list) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(FileHelper.binaryFile, false));

            for (int i = 0; i < list.size(); i++) {
                StudyTask task = list.get(i);

                
                dos.writeUTF(task.getTaskType());
                dos.writeUTF(task.title);
                dos.writeUTF(task.dueDate);
                dos.writeInt(task.priority);
                dos.writeBoolean(task.completed);

                
                if (task instanceof DailyTask) {
                    DailyTask d = (DailyTask) task;
                    dos.writeUTF(d.subject);

                } else if (task instanceof ExamPrep) {
                    ExamPrep e = (ExamPrep) task;
                    dos.writeUTF(e.examName);
                    dos.writeInt(e.studyHoursNeeded);

                } else if (task instanceof ProjectTask) {
                    ProjectTask p = (ProjectTask) task;
                    dos.writeUTF(p.groupMembers);
                    dos.writeInt(p.percentComplete);
                }
            }

            dos.close();
            
        } catch (Exception e) {
            System.out.println("Error saving to binary file: " + e.getMessage());
        }
    }

    
    public static ArrayList<StudyTask> loadAllTasks() {
        ArrayList<StudyTask> list = new ArrayList<StudyTask>();

        try {
            File file = new File(FileHelper.binaryFile);

            if (!file.exists() || file.length() == 0) {
                return list;
            }
        DataInputStream dis = new DataInputStream(new FileInputStream(file));

            while (dis.available() > 0) {
                String type      = dis.readUTF();
                String title     = dis.readUTF();
                String dueDate   = dis.readUTF();
                int priority     = dis.readInt();
                boolean completed = dis.readBoolean();

                StudyTask task = null;

                if (type.equals("DailyTask")) {
                    String subject = dis.readUTF();
                    task = new DailyTask(title, dueDate, priority, subject);

                } 
                 else if (type.equals("ExamPrep")) {
                    String examName = dis.readUTF();
                    int hours = dis.readInt();
                    task = new ExamPrep(title, dueDate, priority, examName, hours);

                } else if (type.equals("ProjectTask")) {
                    String members = dis.readUTF();
                    int percent = dis.readInt();
                    task = new ProjectTask(title, dueDate, priority, members, percent);
                }
            if (task != null) {
                    if (completed) {
                        task.markCompleted();
                    }
                    list.add(task);
                }
            }

            dis.close();

        } catch (Exception e) {
            System.out.println("Error loading from binary file: " + e.getMessage());
        }

        return list;
    }

    
    public static void addTask(StudyTask newTask) {
        ArrayList<StudyTask> list = loadAllTasks();
        list.add(newTask);
        saveAllTasks(list);
        System.out.println("Task added successfully (Binary File).");
    }
           


