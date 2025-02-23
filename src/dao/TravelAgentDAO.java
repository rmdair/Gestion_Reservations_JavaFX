package dao;

import models.TravelAgent;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelAgentDAO {
    
    // The only function used by the graphical interface is the third one
    // (displays only pre-registered travel agents from the database).
    // The other methods are provided for reference if the travel agents table 
    // needs to be modified (it is assumed to be managed exclusively by an administration).

    //-------------------- Create a new travel agent --------------------//
    public void create(TravelAgent travelAgent) throws SQLException {
        String sql = "INSERT INTO TravelAgent (LastName, FirstName, Email, PhoneNumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, travelAgent.getLastName());
            stmt.setString(2, travelAgent.getFirstName());
            stmt.setString(3, travelAgent.getEmail());
            stmt.setString(4, travelAgent.getPhoneNumber());
            stmt.executeUpdate();
        }
    }

    //-------------------- Find a travel agent by ID --------------------//
    public TravelAgent findByTravelAgentId(int travelAgentId) throws SQLException {
        String sql = "SELECT * FROM TravelAgent WHERE TravelAgentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, travelAgentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TravelAgent(
                        rs.getInt("TravelAgentID"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber")
                    );
                }
            }
        }
        return null; // if no travel agent is found
    }

    //-------------------- Retrieve all travel agents --------------------//
    public List<TravelAgent> findAll() throws SQLException {
        List<TravelAgent> travelAgents = new ArrayList<>();
        String sql = "SELECT * FROM TravelAgent";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                travelAgents.add(new TravelAgent(
                    rs.getInt("TravelAgentID"),
                    rs.getString("LastName"),
                    rs.getString("FirstName"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber")
                ));
            }
        }
        return travelAgents;
    }
    
    //-------------------- Delete a travel agent by ID --------------------//
    public void deleteByTravelAgentId(int travelAgentId) throws SQLException {
        String sql = "DELETE FROM TravelAgent WHERE TravelAgentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, travelAgentId);
            stmt.executeUpdate();
        }
    }
}
