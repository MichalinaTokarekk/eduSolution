package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.HomePageElement;
import com.eduSolution.eduSolution.entity.HomePageImage;
import com.eduSolution.eduSolution.repository.HomePageElementRepository;
import com.eduSolution.eduSolution.repository.HomePageImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageElementService {
    @Autowired
    private HomePageElementRepository homePageElementRepository;
    @Autowired
    private HomePageImageRepository homePageImageRepository;

    public HomePageElement saveHomePageElement(HomePageElement homePageElement, List<HomePageImage> selectedImages) {
        if (selectedImages != null) {
            for (HomePageImage image : selectedImages) {
                image.setHomePageElement(homePageElement);
            }

            // Zapisz tylko wybrane obrazy
            homePageImageRepository.saveAll(selectedImages);

            // Ustaw wybrane obrazy w elemencie
            homePageElement.setImages(selectedImages);
        }

        // Następnie zapisz element, który teraz ma ustawione wybrane obrazy
        return homePageElementRepository.save(homePageElement);
    }

    public List<HomePageElement> saveHomePageElements(List<HomePageElement> homePageElements) {
        return homePageElementRepository.saveAll(homePageElements);
    }

    public HomePageElement getHomePageElementById(Long id) {
        return homePageElementRepository.findById(id).orElse(null);
    }

    public List<HomePageElement> getAllHomePageElement() {
        return homePageElementRepository.findAll();
    }

    public HomePageElement updateImage(HomePageElement homePageElement) {
        HomePageElement existingHomePageElement = homePageElementRepository.findById(homePageElement.getId()).orElse(null);
        existingHomePageElement.setTitle(existingHomePageElement.getTitle());
        existingHomePageElement.setDescription(existingHomePageElement.getDescription());
        return homePageElementRepository.save(existingHomePageElement);

    }

    public DeleteResponseLongDTO deleteImage(Long id) {
        HomePageElement homePageElement = homePageElementRepository.findById(id).orElse(null);
        if (homePageElement != null) {
            homePageElementRepository.deleteById(id);
            return new DeleteResponseLongDTO(homePageElement.getId(), homePageElement.getTitle());
        } else {
            return null; // lub rzuć odpowiedni wyjątek
        }
    }
}
