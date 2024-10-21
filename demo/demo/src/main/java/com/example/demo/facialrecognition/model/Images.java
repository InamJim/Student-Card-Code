package com.example.demo.facialrecognition.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    public Images() {}

    public Images(String userId, byte[] imageData) {
        this.userId = userId;
        this.imageData = imageData;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public byte[] getImageData() { return imageData; }
    public void setImageData(byte[] imageData) { this.imageData = imageData; }
}
