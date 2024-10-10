package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JFrame {

    public SignUpPage() {
        // Set up the frame
        setTitle("CardPro Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create the panel with a background image
        JPanel panel = new BackgroundPanel(new ImageIcon("C:/Users/cc adderley/Documents/CardPro/CPUT.jpg").getImage());
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the CardPro logo at the top
         ImageIcon logoIcon = new ImageIcon("C:/Users/cc adderley/Documents/CardPro/CardPro Logo 2.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Scale down the logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Center the logo
        panel.add(logoLabel, gbc);
        
        // Create and add components
        JLabel welcomeLabel = new JLabel("Sign Up for CardPro");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridy = 1; // Place welcome label below the logo
        panel.add(welcomeLabel, gbc);

        // Name field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        gbc.gridy = 2; // Adjust the position for the name label
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Right-align the labels
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Left-align the text fields
        panel.add(nameField, gbc);

        // Surname field
        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(surnameLabel, gbc);

        JTextField surnameField = new JTextField(15);
        surnameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(surnameField, gbc);

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password (min 8 characters):");
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordField, gbc);

        // Sign Up button
        JButton signUpButton = new JButton("Sign Up");
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(signUpButton, gbc);

        // Add ActionListener to the Sign Up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate the password length
                String password = new String(passwordField.getPassword());
                if (password.length() < 8) {
                    JOptionPane.showMessageDialog(SignUpPage.this, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Perform the signup action (e.g., save to a database, etc.)
                    JOptionPane.showMessageDialog(SignUpPage.this, "Sign Up Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Redirect to the login page
                    dispose(); // Close the signup page
                    new LoginPage(); // Open the login page
                }
            }
        });

        // Add panel to frame and set visibility
        add(panel);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    // Assume BackgroundPanel is defined elsewhere
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image image) {
            this.backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new SignUpPage();
    }
}
