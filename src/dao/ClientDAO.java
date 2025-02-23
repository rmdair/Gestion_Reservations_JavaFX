package dao;

import models.Client;
import config.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    //-------------------- Create a client --------------------//
    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO Client (LastName, FirstName, Address, Email, PhoneNumber) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getLastName());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPhoneNumber());
            stmt.executeUpdate();
        }
    }

    
    //-------------------- Find a client by ID --------------------//
    public Client findByClientId(int clientId) throws SQLException {
        String sql = "SELECT * FROM Client WHERE ClientID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                        rs.getInt("ClientID"),
                        rs.getString("LastName"),
                        rs.getString("FirstName"),
                        rs.getString("Address"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber")
                    );
                }
            }
        }
        return null; // if no client is found
    }

    
    // -------------------- Retrieve all clients -------------------- //
    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(new Client(
                    rs.getInt("ClientID"),
                    rs.getString("LastName"),
                    rs.getString("FirstName"),
                    rs.getString("Address"),
                    rs.getString("Email"),
                    rs.getString("PhoneNumber")
                ));
            }
        }
        return clients;
    }

    
    //-------------------- Update a client --------------------//
    public void update(Client client) throws SQLException {
        String sql = "UPDATE Client SET LastName = ?, FirstName = ?, Address = ?, Email = ?, PhoneNumber = ? WHERE ClientID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getLastName());
            stmt.setString(2, client.getFirstName());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPhoneNumber());
            stmt.setInt(6, client.getClientId());
            stmt.executeUpdate();
        }
    }
    
    
    //-------------------- Delete a client by ID --------------------//
    public void deleteByClientId(int clientId) throws SQLException {
        String sql = "DELETE FROM Client WHERE ClientID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            stmt.executeUpdate();
        }
    }
    
    
    //-------------------- Retrieve the last client ID in the table --------------------//
    public int getLastClientId() throws SQLException {
        String sql = "SELECT MAX(ClientID) AS max_id FROM Client";
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

