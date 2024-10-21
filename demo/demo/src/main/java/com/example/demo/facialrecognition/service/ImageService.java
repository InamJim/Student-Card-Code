package com.example.demo.facialrecognition.service;

import com.example.demo.facialrecognition.model.Images;
import com.example.demo.facialrecognition.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Images saveImage(MultipartFile file, String userId) throws IOException {
        Images image = new Images(userId, file.getBytes());
        return imageRepository.save(image);
    }

    public Optional<Images> getImageByUserId(String userId) {
        return Optional.ofNullable(imageRepository.findByUserId(userId));
    }

    public Images getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
