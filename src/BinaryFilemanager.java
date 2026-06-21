import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BinaryFilemanager {public static void saveAllTasks(ArrayList<StudyTask> list) {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(FileHelper.binaryFile, false));

            for (int i = 0; i < list.size(); i++) {
                StudyTask task = list.get(i);
}
