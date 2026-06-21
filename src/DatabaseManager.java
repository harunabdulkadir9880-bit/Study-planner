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

    public static ArrayList<StudyTask> loadAllTasks() {

        ArrayList<StudyTask> list = new ArrayList<StudyTask>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type      = rs.getString("task_type");
                String title     = rs.getString("title");
                String dueDate   = rs.getString("due_date");
                int priority     = rs.getInt("priority");
                boolean completed = rs.getBoolean("completed");

                StudyTask task = null;

                if (type.equals("DailyTask")) {
                    String subject = rs.getString("subject");
                    task = new DailyTask(title, dueDate, priority, subject);

                } else if (type.equals("ExamPrep")) {
                    String examName = rs.getString("exam_name");
                    int hours = rs.getInt("study_hours");
                    task = new ExamPrep(title, dueDate, priority, examName, hours);

                } else if (type.equals("ProjectTask")) {
                    String members = rs.getString("group_members");
                    int percent = rs.getInt("percent_complete");
                    task = new ProjectTask(title, dueDate, priority, members, percent);
                }

                if (task != null) {
                    if (completed) {
                        task.markCompleted();
                    }
                    list.add(task);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error loading tasks from database: " + e.getMessage());
        }

        return list;
    }

    public static void displayAllTasks() {
        ArrayList<StudyTask> list = loadAllTasks();

        if (list.size() == 0) {
            System.out.println("No tasks found in database.");
            return;
        }

        System.out.println("\n===== All Tasks (Database) =====");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).showDetails(); // Polymorphism happens here!
        }
    }

    public static void deleteTask(String title) {

        String sql = "DELETE FROM tasks WHERE title = ?";

        try (Connection conn = DbHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            int rowsChanged = ps.executeUpdate();

            if (rowsChanged > 0) {
                System.out.println("Task deleted successfully (Database).");
            } else {
                System.out.println("Task not found with title: " + title);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting task from database: " + e.getMessage());
        }
    }




}
