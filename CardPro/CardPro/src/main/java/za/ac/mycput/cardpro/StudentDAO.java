/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.mycput.cardpro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author milzg
 */
public class StudentDAO {

    private Connection connection;

    // Constructor to initialize the DAO with a database connection
    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new student into the database
    public void insertStudent(int studentNumber, String firstName, String lastName, String courseName, String homeAddress, String emailAddress, String phoneNumber) {
        String insertSQL = "INSERT INTO Student (studentNumber, firstName, lastName, courseName, homeAddress, emailAddress, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            // Set the parameters for the prepared statement
            preparedStatement.setInt(1, studentNumber);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, courseName);
            preparedStatement.setString(5, homeAddress);
            preparedStatement.setString(6, emailAddress);
            preparedStatement.setString(7, phoneNumber);

            // Execute the insert statement
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all students from the database
    public void getAllStudents() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Create a statement object to execute SQL queries
            statement = connection.createStatement();

            // Execute the SQL query
            String query = "SELECT * FROM Student";
            resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                int studentNumber = resultSet.getInt("studentNumber");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                System.out.println("Student: " + firstName + " " + lastName + " (ID: " + studentNumber + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the result set and statement
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
