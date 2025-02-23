package dao;

import models.Flight;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    
    // The only function used by the graphical interface is the third one 
    // (retrieves only pre-registered flights from the database).
    // The other methods are provided for reference if the flight table 
    // needs to be modified (it is assumed to be managed exclusively by an administration).

    //-------------------- Create a new flight --------------------//
    public void create(Flight flight) throws SQLException {
        String sql = "INSERT INTO Flight (AirlineID, DepartureLocation, ArrivalLocation, DepartureDate, DepartureTime, ArrivalDate, ArrivalTime) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, flight.getAirlineId());
            stmt.setString(2, flight.getDepartureLocation());
            stmt.setString(3, flight.getArrivalLocation());
            stmt.setDate(4, Date.valueOf(flight.getDepartureDate()));
            stmt.setTime(5, Time.valueOf(flight.getDepartureTime()));
            stmt.setDate(6, Date.valueOf(flight.getArrivalDate()));
            stmt.setTime(7, Time.valueOf(flight.getArrivalTime()));
            stmt.executeUpdate();
        }
    }

    //-------------------- Find a flight by ID --------------------//
    public Flight findByFlightId(int flightId) throws SQLException {
        String sql = "SELECT * FROM Flight WHERE FlightID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, flightId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Flight(
                        rs.getInt("FlightID"),
                        rs.getInt("AirlineID"),
                        rs.getString("DepartureLocation"),
                        rs.getString("ArrivalLocation"),
                        rs.getDate("DepartureDate").toLocalDate(),
                        rs.getTime("DepartureTime").toLocalTime(),
                        rs.getDate("ArrivalDate").toLocalDate(),
                        rs.getTime("ArrivalTime").toLocalTime()
                    );
                }
            }
        }
        return null; // if no flight is found
    }

    //-------------------- Retrieve all flights --------------------//
    public List<Flight> findAll() throws SQLException {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM Flight";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                flights.add(new Flight(
                    rs.getInt("FlightID"),
                    rs.getInt("AirlineID"),
                    rs.getString("DepartureLocation"),
                    rs.getString("ArrivalLocation"),
                    rs.getDate("DepartureDate").toLocalDate(),
                    rs.getTime("DepartureTime").toLocalTime(),
                    rs.getDate("ArrivalDate").toLocalDate(),
                    rs.getTime("ArrivalTime").toLocalTime()
                ));
            }
        }
        return flights;
    }
    
    //-------------------- Delete a flight by ID --------------------//
    public void deleteByFlightId(int flightId) throws SQLException {
        String sql = "DELETE FROM Flight WHERE FlightID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, flightId);
            stmt.executeUpdate();
        }
    }
}
