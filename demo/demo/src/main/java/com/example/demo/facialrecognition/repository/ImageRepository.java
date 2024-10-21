package com.example.demo.facialrecognition.repository;

import com.example.demo.facialrecognition.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {
    Images findByUserId(String userId);
}
