package com.example.demo.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // Assuming 'users' table already exists in the cardprodb database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Assuming your 'id' column is of type Long in your database

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;  // Assuming you have a unique user ID

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "student_nr")
    private String studentNr;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "campus")
    private String campus;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "year_of_study")
    private Integer yearOfStudy;

    @Column(name = "password", nullable = false)
    private String password;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "BLOB")
    private byte[] profilePicture;  // This will store the image data as a BLOB in the database

    // Default constructor
    public User() {}

    // Constructor with parameters
    public User(String userId, String name, String surname, String studentNr, String idNumber,
                String campus, String email, Integer yearOfStudy, String password) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.studentNr = studentNr;
        this.idNumber = idNumber;
        this.campus = campus;
        this.email = email;
        this.yearOfStudy = yearOfStudy;
        this.password = password;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentNr() {
        return studentNr;
    }

    public void setStudentNr(String studentNr) {
        this.studentNr = studentNr;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(Integer yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
}
