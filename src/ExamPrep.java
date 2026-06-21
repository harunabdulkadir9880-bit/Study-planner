public class ExamPrep extends StudyTask {

    String examName;     
    int studyHoursNeeded; 

     
    public ExamPrep(String title, String dueDate, int priority, String examName, int studyHoursNeeded) {
        super(title, dueDate, priority);
        this.examName = examName;
        this.studyHoursNeeded = studyHoursNeeded;
    }
public class ExamPrep extends StudyTask {

    String examName;     
    int studyHoursNeeded; 

    

    
    @Override
    public void showDetails() {
        System.out.println("---- Exam Prep Task ----");
        super.showDetails();
        System.out.println("Exam Name      : " + examName);
        System.out.println("Study Hours    : " + studyHoursNeeded);
        System.out.println("-------------------------");
    }

 
    @Override
    public String getTaskType() {
        return "ExamPrep";
    }
}
    
    
