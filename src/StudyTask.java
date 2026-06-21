import java.io.Serializable;
public class StudyTask  implements Serializable {
    String title;
    String dueDate;
    int priority;
    boolean completed;

    public StudyTask(String title, String dueDate, int priority) {
        this.title    = title;
        this.dueDate  = dueDate;
        this.priority = priority;
        this.completed = false; // tasks start as not completed
    }

}
