/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    public LoginPage() {
        // Set up the frame
        setTitle("CardPro Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create the panel with a background image
        JPanel panel = new BackgroundPanel(new ImageIcon("C:/Users/cc adderley/Documents/CardPro/CPUT.jpg").getImage());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Add the CardPro logo
         ImageIcon logoIcon = new ImageIcon("C:/Users/cc adderley/Documents/CardPro/CardPro Logo 2.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Scale down the logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the logo
        panel.add(logoLabel, gbc);

        // Create and add components
        JLabel welcomeLabel = new JLabel("Welcome to CardPro");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        // Email field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(15);
        emailField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Login button
        JButton loginButton = new JButton("Login");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        // Sign Up button
        JButton signUpButton = new JButton("Sign Up here");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(signUpButton, gbc);

        // Add ActionListener to the Sign Up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to the SignUp page
                dispose(); // Close the login page
                new SignUpPage(); // Open the signup page
            }
        });

        // Add panel to frame and set visibility
        add(panel);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}

