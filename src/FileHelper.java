import java.io.File;
import java.util.Date;

public class FileHelper {

    public static String dataFolder = "data";
    public static String textFile   = "data/tasks.txt";
    public static String binaryFile = "data/tasks.dat";
    public static String objectFile = "data/tasks.obj";

public static void setupFiles() {
    try {
        File data = new File(dataFolder);
        if (!data.exists()) {
            data.mkdir();
            System.out.println("Created folder: " + dataFolder);
        }
        File txt = new File(textFile);
        if (!txt.exists()) {
           txt.createNewFile();
           System.out.println("Created file: " + textFile);
        }
        File dat = new File(binaryFile);
        if (!dat.exists()) {
           dat.createNewFile();
           System.out.println("Created file: " + binaryFile);
        }
    catch (Exception e) {
     System.out.println("Error setting up files: " + e.getMessage());
        }
public static void showFileProperties(String filePath) {
    File file = new File(filePath);

    System.out.println("\n--- File Properties: " + filePath + " ---");
}
