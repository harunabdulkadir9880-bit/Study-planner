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
           


