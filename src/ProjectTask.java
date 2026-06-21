public class ProjectTask extends StudyTask {

    String groupMembers;  
    int percentComplete;  

    public ProjectTask(String title, String dueDate, int priority, String groupMembers, int percentComplete) {
        super(title, dueDate, priority);
        this.groupMembers = groupMembers;
        this.percentComplete = percentComplete;
    }
    

}

