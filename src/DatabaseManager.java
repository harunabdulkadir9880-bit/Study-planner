import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager {

    public static void addTask(StudyTask newTask) {

        String sql = "INSERT INTO tasks (task_type, title, due_date, priority, completed, "
                + "subject, exam_name, study_hours, group_members, percent_complete) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setString(1, newTask.getTaskType());
            ps.setString(2, newTask.title);
            ps.setString(3, newTask.dueDate);
            ps.setInt(4, newTask.priority);
            ps.setBoolean(5, newTask.completed);


            ps.setString(6, null);
            ps.setString(7, null);
            ps.setObject(8, null);
            ps.setString(9, null);
            ps.setObject(10, null);

            if (newTask instanceof DailyTask) {
                DailyTask d = (DailyTask) newTask;
                ps.setString(6, d.subject);

            } else if (newTask instanceof ExamPrep) {
                ExamPrep e = (ExamPrep) newTask;
                ps.setString(7, e.examName);
                ps.setInt(8, e.studyHoursNeeded);

            } else if (newTask instanceof ProjectTask) {
                ProjectTask p = (ProjectTask) newTask;
                ps.setString(9, p.groupMembers);
                ps.setInt(10, p.percentComplete);
            }

            ps.executeUpdate();
            System.out.println("Task added successfully (Database).");

        } catch (SQLException e) {
            System.out.println("Error adding task to database: " + e.getMessage());
        }
    }


}
