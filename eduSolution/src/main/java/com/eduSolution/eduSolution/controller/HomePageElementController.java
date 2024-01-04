package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.HomePageElement;
import com.eduSolution.eduSolution.entity.HomePageImage;
import com.eduSolution.eduSolution.service.HomePageElementService;
import com.eduSolution.eduSolution.service.HomePageImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/homePageElement-controller")
@RestController
public class HomePageElementController {

    @Autowired
    private HomePageElementService homePageElementService;

    @PostMapping("/saveHomePageElement")
    public ResponseEntity<HomePageElement> saveHomePageElement(@RequestBody HomePageElement homePageElement, @RequestBody List<HomePageImage> selectedImages) {
        HomePageElement savedHomePageElement = homePageElementService.saveHomePageElement(homePageElement, selectedImages);
        return new ResponseEntity<>(savedHomePageElement, HttpStatus.CREATED);
    }

    @PostMapping("/saveHomePageElements")
    public ResponseEntity<List<HomePageElement>> saveHomePageElements(@RequestBody List<HomePageElement> homePageElements) {
        List<HomePageElement> savedHomePageElements = homePageElementService.saveHomePageElements(homePageElements);
        return new ResponseEntity<>(savedHomePageElements, HttpStatus.CREATED);
    }

    @GetMapping("getHomePageElementById/{id}")
    public ResponseEntity<HomePageElement> getHomePageElementById(@PathVariable Long id) {
        HomePageElement homePageElement = homePageElementService.getHomePageElementById(id);
        if (homePageElement != null) {
            return new ResponseEntity<>(homePageElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllHomePageElements")
    public ResponseEntity<List<HomePageElement>> getAllHomePageElements() {
        List<HomePageElement> images = homePageElementService.getAllHomePageElement();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    @PutMapping("/updateHomePageElement")
    public ResponseEntity<HomePageElement> updateHomePageElement(@RequestBody HomePageElement homePageImage) {
        HomePageElement updatedHomePageElement = homePageElementService.updateImage(homePageImage);
        if (updatedHomePageElement != null) {
            return new ResponseEntity<>(updatedHomePageElement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteHomePageElement/{id}")
    public ResponseEntity<DeleteResponseLongDTO> deleteHomePageElement(@PathVariable Long id) {
        DeleteResponseLongDTO response = homePageElementService.deleteImage(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
