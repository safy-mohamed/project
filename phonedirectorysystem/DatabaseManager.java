
package com.mycompany.phonedirectorysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {
    
    // SQLite database connection
    private Connection connection;

    // Constructor to initialize the database connection
    public DatabaseManager() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Connect to the SQLite database (replace "your_database.db" with your database file name)
            connection = DriverManager.getConnection("jdbc:sqlite:phoneDirectoryDB.db");
             System.out.println("connection succeeded");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("connection Failed" + ex);
        }
    }

    // Method to add a new contact to the database
    public boolean addContact(String firstName, String lastName, String email, String mobileNumber, String jobTitle) {
        try {
            // Establish connection to the database
             connection = DriverManager.getConnection("jdbc:sqlite:C:\\safy\\PhoneDirectorySystem\\phoneDirectoryDB.db");

            // Prepare the SQL statement
            String query = "INSERT INTO Contact ([First Name], [Last Name], [Email], [Job Title], [Mobile Number]) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set parameters for the SQL statement
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, mobileNumber);
            preparedStatement.setString(5, jobTitle);

            // Execute the SQL statement
            preparedStatement.executeUpdate();

            // Close the connection
            connection.close();
            
            // Notify success
            System.out.println("Contact added successfully!");
            return true;
        } catch (SQLException e) {
            // Handle any errors that may occur
            e.printStackTrace();
            // Notify failure
            System.err.println("Error adding contact to the database!");
        }
        return false;
    }

    // Method to delete a contact from the database by mobile number
   public boolean deleteContact(String mobileNumber) {
    try {
        // Prepare the SQL statement for deletion
        String sql = "DELETE FROM Contact WHERE [Mobile Number] = ?";
        // Log the SQL statement before execution
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.INFO, "Executing SQL statement: {0}", sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the mobile number parameter
            statement.setString(1, mobileNumber);
            // Execute the statement to delete the contact
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0; // If rowsDeleted > 0, contact deleted successfully
        }
    } catch (SQLException ex) {
        // Log any SQL exception that occurs
        Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, "Error deleting contact: {0}", ex.getMessage());
        return false; // Error deleting contact
    }
}


    // Method to update a contact in the database
    public boolean updateContact(String originalFirstName, String originalMobileNumber, String newFirstName, String newMobileNumber) {
    try {
        // Prepare the SQL statement for updating
        String sql = "UPDATE Contact SET [First Name] = ?, [Mobile Number] = ? WHERE [First Name] = ? AND [Mobile Number] = ?";
        int rowsUpdated;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newFirstName);
            statement.setString(2, newMobileNumber);
            statement.setString(3, originalFirstName);
            statement.setString(4, originalMobileNumber);
            // Execute the statement to update the contact
            rowsUpdated = statement.executeUpdate();
            // Close the statement
        }

        return rowsUpdated > 0; // If rowsUpdated > 0, contact updated successfully
    } catch (SQLException ex) {
        ex.printStackTrace(); // Print the stack trace for debugging
        return false; // Error updating contact
    }
}






    // Method to search for contacts by first name or mobile number
    public List<Contact> searchContacts(String keyword) {
        List<Contact> contacts = new ArrayList<>();
        try {
            // Prepare the SQL statement for searching
            String sql = "SELECT * FROM Contact WHERE [First Name] LIKE ? OR [Mobile Number] = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + keyword + "%");
                statement.setString(2, keyword);
                
                // Iterate over the results and create Contact objects
                try ( // Execute the statement and retrieve the results
                        ResultSet resultSet = statement.executeQuery()) {
                    // Iterate over the results and create Contact objects
                    while (resultSet.next()) {
                        Contact contact = new Contact(
                                resultSet.getString("First Name"),
                                resultSet.getString("Last Name"),
                                resultSet.getString("Email"),
                                resultSet.getString("Mobile Number"),
                                resultSet.getString("Job Title")
                        );
                        contacts.add(contact);
                    }
                    // Close the result set and statement
                }
            }
        } catch (SQLException ex) {
        }
        return contacts;
    }

    // Method to retrieve all contacts from the database
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            // Prepare the SQL statement for selecting all contacts
            String sql = "SELECT * FROM Contact";
            // Execute the statement and retrieve the results
            try (PreparedStatement statement = connection.prepareStatement(sql); // Execute the statement and retrieve the results
                    ResultSet resultSet = statement.executeQuery()) {
                
                // Iterate over the results and create Contact objects
                while (resultSet.next()) {
                    Contact contact = new Contact(
                            resultSet.getString("First Name"),
                            resultSet.getString("Last Name"),
                            resultSet.getString("Email"),
                            resultSet.getString("Mobile Number"),
                            resultSet.getString("Job Title")
                    );
                    contacts.add(contact);
                }
                
            }
        } catch (SQLException ex) {
        }
        return contacts;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
        }
    }

   

    
}
