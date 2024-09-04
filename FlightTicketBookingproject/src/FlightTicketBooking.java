import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class FlightTicketBooking{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        FlightService flightService = new FlightService();
        BookingService bookingService = new BookingService();
        PaymentService paymentService = new PaymentService();

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Search Flights");
            System.out.println("4. Book Flight");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Register
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter email:");
                    String email = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();
                    if (userService.registerUser(name, email, password)) {
                        System.out.println("Registration successful.");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;

                case 2:
                    // Login
                    System.out.println("Enter email:");
                    email = scanner.nextLine();
                    System.out.println("Enter password:");
                    password = scanner.nextLine();
                    if (userService.authenticateUser(email, password)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;

                case 3:
                    // Search Flights
                    System.out.println("Enter departure city:");
                    String departureCity = scanner.nextLine();
                    System.out.println("Enter arrival city:");
                    String arrivalCity = scanner.nextLine();
                    System.out.println("Enter departure time (YYYY-MM-DD HH:MM:SS):");
                    String departureTimeStr = scanner.nextLine();
                    Timestamp departureTime = Timestamp.valueOf(departureTimeStr);

                    List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity, departureTime);
                    if (flights.isEmpty()) {
                        System.out.println("No flights found.");
                    } else {
                        for (Flight flight : flights) {
                            System.out.println("Flight ID: " + flight.getFlightId());
                            System.out.println("Flight Number: " + flight.getFlightNumber());
                            System.out.println("Departure City: " + flight.getDepartureCity());
                            System.out.println("Arrival City: " + flight.getArrivalCity());
                            System.out.println("Departure Time: " + flight.getDepartureTime());
                            System.out.println("Arrival Time: " + flight.getArrivalTime());
                            System.out.println("Available Seats: " + flight.getAvailableSeats());
                            System.out.println();
                        }
                    }
                    break;

                case 4:
                    // Book Flight
                    System.out.println("Enter user ID:");
                    int userId = scanner.nextInt();
                    System.out.println("Enter flight ID:");
                    int flightId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter seat number:");
                    String seatNumber = scanner.nextLine();

                    if (bookingService.bookFlight(userId, flightId, seatNumber)) {
                        System.out.println("Booking successful.");
                        System.out.println("Enter payment amount:");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        System.out.println("Enter payment currency (e.g., USD):");
                        String currency = scanner.nextLine();
                        System.out.println("Enter payment source (e.g., card token):");
                        String source = scanner.nextLine();

                        if (paymentService.processPayment(userId, amount, currency, source)) {
                            System.out.println("Payment successful.");
                        } else {
                            System.out.println("Payment failed.");
                        }
                    } else {
                        System.out.println("Booking failed.");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
