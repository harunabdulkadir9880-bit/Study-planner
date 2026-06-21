import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {

    public static String host     = "localhost";
    public static String port     = "3306";
    public static String dbName   = "study_planner_db";
    public static String username = "root";
    public static String password = "password";

    public static String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;


}
