public class ProjectTask extends StudyTask {

    String groupMembers;  
    int percentComplete;  

    public ProjectTask(String title, String dueDate, int priority, String groupMembers, int percentComplete) {
        super(title, dueDate, priority);
        this.groupMembers = groupMembers;
        this.percentComplete = percentComplete;
    }
     @Override
    public void showDetails() {
        System.out.println("---- Project Task ----");
        super.showDetails();
        System.out.println("Group Members  : " + groupMembers);
        System.out.println("Percent Done   : " + percentComplete + "%");
        System.out.println("-----------------------");
    }
     @Override
    public String getTaskType() {
        return "ProjectTask";
    }

}

