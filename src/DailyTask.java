public class DailyTask extends StudyTask {

    String subject;

    public DailyTask(String title, String dueDate, int priority, String subject) {
        super(title, dueDate, priority);
        this.subject = subject;
    }

}
