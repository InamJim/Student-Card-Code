package com.example.demo.facialrecognition.controller;

import com.example.demo.facialrecognition.model.Images;
import com.example.demo.facialrecognition.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/api/facial-recognition")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("userId") String userId) {
        try {
            // Convert MultipartFile to byte[] to save in the database
            byte[] imageData = file.getBytes();

            // Check if the user already has an image, and if so, update it
            Optional<Images> existingImageOptional = imageService.getImageByUserId(userId);
            if (existingImageOptional.isPresent()) {
                Images existingImage = existingImageOptional.get();
                existingImage.setImageData(imageData);  // Replace the image data
                imageService.saveImage(file, userId);  // Save updated image
            } else {
                // No existing image, so create a new one
                Images newImage = new Images(userId, imageData);
                imageService.saveImage(file, userId);  // Save new image
            }

            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Images image = imageService.getImageById(id);

        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.png\"")
                    .body(image.getImageData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
