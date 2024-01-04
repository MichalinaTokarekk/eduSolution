package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.HomePageImage;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.HomePageImageRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageImageService {
    @Autowired
    private HomePageImageRepository homePageImageRepository;

    public HomePageImage saveImage(HomePageImage image) {
        return homePageImageRepository.save(image);
    }

    public List<HomePageImage> saveImages(List<HomePageImage> images) {
        return homePageImageRepository.saveAll(images);
    }

    public HomePageImage getImageById(Long id) {
        return homePageImageRepository.findById(id).orElse(null);
    }

    public List<HomePageImage> getAllImages() {
        return homePageImageRepository.findAll();
    }

    public HomePageImage updateImage(HomePageImage image) {
        HomePageImage existingImage = homePageImageRepository.findById(image.getId()).orElse(null);
        if (existingImage != null) {
            existingImage.setUrl(image.getUrl());
            return homePageImageRepository.save(existingImage);
        } else {
            return null; // lub rzuć odpowiedni wyjątek
        }
    }

    public DeleteResponseLongDTO deleteImage(Long id) {
        HomePageImage image = homePageImageRepository.findById(id).orElse(null);
        if (image != null) {
            homePageImageRepository.deleteById(id);
            return new DeleteResponseLongDTO(image.getId(), image.getUrl());
        } else {
            return null; // lub rzuć odpowiedni wyjątek
        }
    }
}
