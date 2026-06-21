public class ExamPrep extends StudyTask {

    String examName;     
    int studyHoursNeeded; 

     
    public ExamPrep(String title, String dueDate, int priority, String examName, int studyHoursNeeded) {
        super(title, dueDate, priority);
        this.examName = examName;
        this.studyHoursNeeded = studyHoursNeeded;
    }

    
    
