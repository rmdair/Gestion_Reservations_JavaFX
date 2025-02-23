package dao;

import models.Reservation;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    //-------------------- Create a new reservation --------------------//
    public void create(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO Reservation (ClientID, TravelAgentID, FlightID, ReservationDate, Class, " +
                     "PaymentType, Status, BookerLastName, BookerFirstName, BookerPhoneNumber) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, reservation.getClientId());
            stmt.setInt(2, reservation.getTravelAgentId());
            stmt.setInt(3, reservation.getFlightId());
            stmt.setDate(4, Date.valueOf(reservation.getReservationDate()));
            stmt.setString(5, reservation.getFlightClass());
            stmt.setString(6, reservation.getPaymentType());
            stmt.setString(7, reservation.getStatus());
            stmt.setString(8, reservation.getBookerLastName());
            stmt.setString(9, reservation.getBookerFirstName());
            stmt.setString(10, reservation.getBookerPhoneNumber());
            stmt.executeUpdate();

            // Retrieve the auto-generated ID from the database
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reservation.setReservationId(generatedKeys.getInt(1));
                }
            }
        }
    }

    //-------------------- Find a reservation by ID --------------------//
    public Reservation findByReservationId(int reservationId) throws SQLException {
        String sql = "SELECT * FROM Reservation WHERE ReservationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("ClientID"),
                        rs.getInt("TravelAgentID"),
                        rs.getInt("FlightID"),
                        rs.getDate("ReservationDate").toLocalDate(),
                        rs.getString("Class"),
                        rs.getString("PaymentType"),
                        rs.getString("Status"),
                        rs.getString("BookerLastName"),
                        rs.getString("BookerFirstName"),
                        rs.getString("BookerPhoneNumber")
                    );
                }
            }
        }
        return null; // if no reservation is found
    }

    //-------------------- Retrieve all reservations --------------------//
    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reservations.add(new Reservation(
                    rs.getInt("ReservationID"),
                    rs.getInt("ClientID"),
                    rs.getInt("TravelAgentID"),
                    rs.getInt("FlightID"),
                    rs.getDate("ReservationDate").toLocalDate(),
                    rs.getString("Class"),
                    rs.getString("PaymentType"),
                    rs.getString("Status"),
                    rs.getString("BookerLastName"),
                    rs.getString("BookerFirstName"),
                    rs.getString("BookerPhoneNumber")
                ));
            }
        }
        return reservations;
    }

    //-------------------- Update a reservation --------------------//
    public void update(Reservation reservation) throws SQLException {
        String sql = "UPDATE Reservation SET ClientID = ?, TravelAgentID = ?, FlightID = ?, ReservationDate = ?, " +
                     "Class = ?, PaymentType = ?, Status = ?, BookerLastName = ?, " +
                     "BookerFirstName = ?, BookerPhoneNumber = ? WHERE ReservationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getClientId());
            stmt.setInt(2, reservation.getTravelAgentId());
            stmt.setInt(3, reservation.getFlightId());
            stmt.setDate(4, Date.valueOf(reservation.getReservationDate()));
            stmt.setString(5, reservation.getFlightClass());
            stmt.setString(6, reservation.getPaymentType());
            stmt.setString(7, reservation.getStatus());
            stmt.setString(8, reservation.getBookerLastName());
            stmt.setString(9, reservation.getBookerFirstName());
            stmt.setString(10, reservation.getBookerPhoneNumber());
            stmt.setInt(11, reservation.getReservationId());
            stmt.executeUpdate();
        }
    }

    //-------------------- Delete a reservation by ID --------------------//
    public void deleteByReservationId(int reservationId) throws SQLException {
        String sql = "DELETE FROM Reservation WHERE ReservationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.executeUpdate();
        }
    }

    //-------------------- Retrieve the last Reservation ID --------------------//
    public int getLastReservationId() throws SQLException {
        String sql = "SELECT MAX(ReservationID) AS max_id FROM Reservation";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("max_id"); // last used ID
            }
        }
        return 0; // if no records exist
    }
}
