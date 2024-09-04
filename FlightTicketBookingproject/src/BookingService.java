// Flight Booking with Validation

import java.sql.*;
import java.util.Date;

public class BookingService {
    public boolean bookFlight(int userId, int flightId, String seatNumber) {
        if (userId <= 0 || flightId <= 0 || seatNumber == null || seatNumber.isEmpty()) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        String bookingQuery = "INSERT INTO Bookings (user_id, flight_id, seat_number, booking_date) VALUES (?, ?, ?, ?)";
        String updateFlightQuery = "UPDATE Flights SET available_seats = available_seats - 1 WHERE flight_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement bookingStmt = conn.prepareStatement(bookingQuery);
             PreparedStatement updateFlightStmt = conn.prepareStatement(updateFlightQuery)) {

            conn.setAutoCommit(false);  // Start transaction

            // Insert booking
            bookingStmt.setInt(1, userId);
            bookingStmt.setInt(2, flightId);
            bookingStmt.setString(3, seatNumber);
            bookingStmt.setTimestamp(4, new Timestamp(new Date().getTime()));
            bookingStmt.executeUpdate();

            // Update flight seat availability
            updateFlightStmt.setInt(1, flightId);
            int rowsUpdated = updateFlightStmt.executeUpdate();

            if (rowsUpdated == 0) {
                conn.rollback();
                return false;  // No seats available
            }

            conn.commit();  // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
