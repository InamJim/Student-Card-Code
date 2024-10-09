/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.mycput.cardpro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author milzg
 */
public class AccessDBConnection {
    
   
    // Method to establish a connection to the Access Database
    public static Connection getConnection() {
        Connection connection = null;
        
        
        try {
            // Load the UCanAccess JDBC driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // Specify the path to your .accdb file
            String databaseURL = "jdbc:ucanaccess://C:\\Users\\milzg\\OneDrive\\Documents\\School Stuff\\Project 2\\CardPro/CardPro.accdb"; // Update the path

            // Establish the connection
            connection = DriverManager.getConnection(databaseURL);
        } catch (ClassNotFoundException e) {
            System.out.println("UCanAccess driver not found. Make sure it's added to the project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection to the database failed.");
            e.printStackTrace();
        }
        return connection;
    }
}
