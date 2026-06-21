public class DailyTask extends StudyTask {

    String subject;

    public DailyTask(String title, String dueDate, int priority, String subject) {
        super(title, dueDate, priority);
        this.subject = subject;
    }

    @Override
    public void showDetails() {
        System.out.println("---- Daily Task ----");
        super.showDetails(); // this calls the parent version first
        System.out.println("Subject  : " + subject);
        System.out.println("---------------------");
    }
    @Override
    public String getTaskType() {
        return "DailyTask";
    }
}
