// Load required modules
const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const path = require('path');

// Create an instance of the Express application
const app = express();

// Middleware to parse JSON bodies
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Serve static files from the "public" directory
app.use(express.static(path.join(__dirname, 'public')));

// MySQL database connection
const connection = mysql.createConnection({
    host: 'localhost', // Your MySQL host
    user: 'root',      // Your MySQL username
    password: '',      // Your MySQL password
    database: 'user_management' // Your MySQL database name
});

// Connect to the database
connection.connect(err => {
    if (err) {
        console.error('Error connecting to the database:', err);
        return;
    }
    console.log('Connected to the MySQL database.');
});

// Define a route for user registration
app.post('/register', (req, res) => {
    const { first_name, last_name, email, password } = req.body;

    // SQL query to insert user data into the database
    const sql = 'INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)';
    connection.query(sql, [first_name, last_name, email, password], (err, result) => {
        if (err) {
            console.error('Error inserting data:', err);
            return res.status(500).send('Database error');
        }
        res.status(201).send('User registered successfully');
    });
});

// Start the server
const PORT = 3000; // Define the port to listen on
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
