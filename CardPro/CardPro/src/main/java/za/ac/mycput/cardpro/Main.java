/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.mycput.cardpro;

import java.sql.Connection;

/**
 *
 * @author milzg
 */
public class Main {
    public static void main (String [] Args){
        
        Connection connection = null;
        StudentDAO studentDAO = null;

        try {
            // Get the connection using the AccessDBConnection class
            connection = AccessDBConnection.getConnection();

            // Check if the connection is successful
            if (connection != null) {
                System.out.println("Connection established successfully!");

                // Create an instance of StudentDAO with the established connection
                studentDAO = new StudentDAO(connection);
                
                 // Example: Inserting a new student
                studentDAO.insertStudent(1052, "Lance", "QWER", "ghfgd","123 dfdggh", "jfdghgfhfhm", "123-456-7890");
                
                // Call the method to retrieve and display all students
                studentDAO.getAllStudents();
            } else {
                System.out.println("Failed to establish a connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connection in the finally block
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
