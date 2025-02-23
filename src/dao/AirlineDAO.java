package dao;

import models.Airline;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO {
    
    // The only function used by the graphical interface is the third one
    // (retrieves pre-registered airlines from the database).
    // The other methods are provided for reference if the airlines table 
    // needs to be modified (it is assumed to be managed exclusively by an administration).

    //-------------------- Create a new airline --------------------//
    public void create(Airline airline) throws SQLException {
        String sql = "INSERT INTO Airline (Name, CountryOfOrigin) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, airline.getName());
            stmt.setString(2, airline.getCountryOfOrigin());
            stmt.executeUpdate();
        }
    }

    //-------------------- Find an airline by ID --------------------//
    public Airline findByAirlineId(int airlineId) throws SQLException {
        String sql = "SELECT * FROM Airline WHERE AirlineID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, airlineId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Airline(
                        rs.getInt("AirlineID"),
                        rs.getString("Name"),
                        rs.getString("CountryOfOrigin")
                    );
                }
            }
        }
        return null; // if no airline is found
    }

    //-------------------- Retrieve all airlines --------------------//
    public List<Airline> findAll() throws SQLException {
        List<Airline> airlines = new ArrayList<>();
        String sql = "SELECT * FROM Airline";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                airlines.add(new Airline(
                    rs.getInt("AirlineID"),
                    rs.getString("Name"),
                    rs.getString("CountryOfOrigin")
                ));
            }
        }
        return airlines;
    }
    
    //-------------------- Delete an airline by ID --------------------//
    public void deleteByAirlineId(int airlineId) throws SQLException {
        String sql = "DELETE FROM Airline WHERE AirlineID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, airlineId);
            stmt.executeUpdate();
        }
    }
}
