
-- 
-- CREATE TABLE IF NOT EXISTS Flights (
--     flight_id INT AUTO_INCREMENT PRIMARY KEY,
--     flight_number VARCHAR(50) NOT NULL,
--     departure_city VARCHAR(100) NOT NULL,
--     arrival_city VARCHAR(100) NOT NULL,
--     departure_time TIMESTAMP NOT NULL,
--     arrival_time TIMESTAMP NOT NULL,
--     available_seats INT NOT NULL CHECK (available_seats >= 0),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- -- 4. Create Bookings Table
-- CREATE TABLE IF NOT EXISTS Bookings (
--     booking_id INT AUTO_INCREMENT PRIMARY KEY,
--     user_id INT NOT NULL,
--     flight_id INT NOT NULL,
--     seat_number VARCHAR(10) NOT NULL,
--     booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE,
--     FOREIGN KEY (flight_id) REFERENCES Flights(flight_id) ON DELETE CASCADE
-- );

-- -- 5. Insert Sample Data into Users Table
-- INSERT INTO Users (name, email, password) VALUES 
-- ('John Doe', 'john@example.com', 'password123'),
-- ('Jane Smith', 'jane@example.com', 'password123'),
-- ('Alice Johnson', 'alice@example.com', 'password123');

-- -- 6. Insert Sample Data into Flights Table
-- INSERT INTO Flights (flight_number, departure_city, arrival_city, departure_time, arrival_time, available_seats) VALUES 
-- ('AI101', 'New York', 'Los Angeles', '2024-09-01 10:00:00', '2024-09-01 14:00:00', 150),
-- ('AI102', 'San Francisco', 'Chicago', '2024-09-02 09:00:00', '2024-09-02 13:00:00', 120),
-- ('AI103', 'Boston', 'Miami', '2024-09-03 07:00:00', '2024-09-03 11:00:00', 100);

-- -- 7. Insert Sample Data into Bookings Table
-- -- Assuming booking by John Doe (user_id=1) on flight AI101 (flight_id=1)
-- INSERT INTO Bookings (user_id, flight_id, seat_number) VALUES 
-- (1, 1, '12A'),
-- (2, 2, '15C'),
-- (3, 3, '8B');

-- -- 8. Add Indexes (optional, for better performance)
-- CREATE INDEX idx_departure_city ON Flights(departure_city);
-- CREATE INDEX idx_arrival_city ON Flights(arrival_city);
-- CREATE INDEX idx_departure_time ON Flights(departure_time);
-- CREATE INDEX idx_user_id ON Bookings(user_id);
-- CREATE INDEX idx_flight_id ON Bookings(flight_id);


-- SELECT 
--     u.user_id,
--     u.name AS user_name,
--     u.email AS user_email,
--     f.flight_id,
--     f.flight_number,
--     f.departure_city,
--     f.arrival_city,
--     f.departure_time,
--     f.arrival_time,
--     f.available_seats,
--     b.booking_id,
--     b.seat_number,
--     b.booking_date
-- FROM 
--     Users u
-- LEFT JOIN 
--     Bookings b ON u.user_id = b.user_id
-- LEFT JOIN 
--     Flights f ON b.flight_id = f.flight_id;