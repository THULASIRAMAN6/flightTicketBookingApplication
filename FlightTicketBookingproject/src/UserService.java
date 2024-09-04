// User Registration and Authentication

import java.sql.*;

public class UserService {
    public boolean registerUser(String name, String email, String password) {
        if (name == null || email == null || password == null || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        String query = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);  // Password should be hashed in real applications
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);  // Password should be hashed in real applications
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
