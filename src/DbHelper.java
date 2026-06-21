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
}
