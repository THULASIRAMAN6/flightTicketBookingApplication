import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    public List<Flight> searchFlights(String departureCity, String arrivalCity, Timestamp departureTime) {
        if (departureCity == null || arrivalCity == null || departureTime == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM Flights WHERE departure_city = ? AND arrival_city = ? AND departure_time >= ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, departureCity);
            stmt.setString(2, arrivalCity);
            stmt.setTimestamp(3, departureTime);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                flights.add(new Flight(
                    rs.getInt("flight_id"),
                    rs.getString("flight_number"),
                    rs.getString("departure_city"),
                    rs.getString("arrival_city"),
                    rs.getTimestamp("departure_time"),
                    rs.getTimestamp("arrival_time"),
                    rs.getInt("available_seats")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}
