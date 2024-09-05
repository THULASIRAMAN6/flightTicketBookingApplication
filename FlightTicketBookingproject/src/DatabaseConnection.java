// Database Connection

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "// // //";
    private static final String USER = "// // //";
    private static final String PASSWORD = "// // //";

    public static Connection getConnection() {

        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found.", e);
        }

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
