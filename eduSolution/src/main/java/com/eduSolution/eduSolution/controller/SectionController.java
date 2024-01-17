package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.repository.SectionRepository;
import com.eduSolution.eduSolution.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/section-controller")
@RestController
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private SectionRepository sectionRepository;

    @PostMapping("/addSection")
    public Section addSection (@RequestBody Section section){
        return sectionService.saveSection(section);
    }
    @PostMapping("/addSections")
    public List<Section> addSections (@RequestBody List<Section> sections){
        return sectionService.saveSections(sections);
    }




    @GetMapping("/sections")
    public List<Section> findAllSections() {
        return sectionService.getSections();
    }
    @GetMapping ("/section/{id}")
    public Section findSectionById(@PathVariable int id) {
        return sectionService.getSectionById(id);
    }
    @GetMapping ("/sectionName/{name}")
    public Section findSectionByName(@PathVariable String name) {
        return sectionService.getSectionByName(name);
    }

    @GetMapping (value = "/sectionsByClassGroupId/{classGroupId}")
    public List<Section> findSectionsByClassGroupId(@PathVariable int classGroupId) {
        return sectionService.getSectionsByClassGroup(classGroupId);
    }
    @GetMapping("/findNameById/{id}")
    public ResponseEntity<String> findNameById(@PathVariable Optional<Integer> id) {
        if (id.isPresent()) {
            String sectionName = sectionService.findNameById(id.get());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            return new ResponseEntity<>(sectionName, headers, HttpStatus.OK);
        } else {
            // Obsługa przypadku, gdy id nie jest dostarczane
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID is missing");
        }
    }


    @PutMapping("/updateSection")
    public Section updateSection (@RequestBody Section section) {
        return sectionService.updateSection(section);
    }

    @PutMapping("/updateEduMaterial")
    public Section updateEduMaterial(@RequestBody Section section) {
        return sectionService.updateEduMaterial(section);
    }

    @DeleteMapping("/deleteSection/{id}")
    public DeleteResponseDTO deleteSection(@PathVariable int id) {
        return sectionService.deleteSection(id);
    }
}
