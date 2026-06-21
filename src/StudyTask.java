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

    public void markCompleted() {
        this.completed = true;
    }

    public void showDetails() {
        System.out.println("Title    : " + title);
        System.out.println("Due Date : " + dueDate);
        System.out.println("Priority : " + priority);
        System.out.println("Completed: " + completed);
    }
    public String getTaskType() {
        return "StudyTask";
    }

}
