package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.HomePageImage;
import com.eduSolution.eduSolution.service.HomePageImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/homePageImage-controller")
@RestController
public class HomePageImageController {
    @Autowired
    private HomePageImageService homePageImageService;


    @PostMapping("/saveHomePageImage")
    public ResponseEntity<HomePageImage> saveHomePageImage(@RequestBody HomePageImage image) {
        HomePageImage savedImage = homePageImageService.saveImage(image);
        return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
    }

    @PostMapping("/saveHomePageImages")
    public ResponseEntity<List<HomePageImage>> saveHomePageImages(@RequestBody List<HomePageImage> images) {
        List<HomePageImage> savedImages = homePageImageService.saveImages(images);
        return new ResponseEntity<>(savedImages, HttpStatus.CREATED);
    }

    @GetMapping("getHomePageImage/{id}")
    public ResponseEntity<HomePageImage> getHomePageImageById(@PathVariable Long id) {
        HomePageImage image = homePageImageService.getImageById(id);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllHomePageImages")
    public ResponseEntity<List<HomePageImage>> getAllHomePageImages() {
        List<HomePageImage> images = homePageImageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PutMapping("/updateHomePageImage")
    public ResponseEntity<HomePageImage> updateHomePageImage(@RequestBody HomePageImage image) {
        HomePageImage updatedImage = homePageImageService.updateImage(image);
        if (updatedImage != null) {
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteHomePageImage/{id}")
    public ResponseEntity<DeleteResponseLongDTO> deleteHomePageImage(@PathVariable Long id) {
        DeleteResponseLongDTO response = homePageImageService.deleteImage(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
