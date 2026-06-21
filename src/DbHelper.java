import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {

    public static String host     = "127.0.0.1";
    public static String port     = "3306";
    public static String dbName   = "study planner database";
    public static String username = "root";
    public static String password = "Slim2026@";

    public static String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(dbUrl, username, password);
        return conn;
    }

    public static void setupDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS tasks ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "task_type VARCHAR(50) NOT NULL, "
                    + "title VARCHAR(150) NOT NULL, "
                    + "due_date VARCHAR(50), "
                    + "priority INT NOT NULL, "
                    + "completed BOOLEAN NOT NULL, "
                    + "subject VARCHAR(100), "
                    + "exam_name VARCHAR(100), "
                    + "study_hours INT, "
                    + "group_members VARCHAR(200), "
                    + "percent_complete INT"
                    + ")";

            stmt.execute(sql);
            System.out.println("Database is ready (table 'tasks' exists).");

        } catch (SQLException e) {
            System.out.println("Error setting up database: " + e.getMessage());
        }
    }





}

