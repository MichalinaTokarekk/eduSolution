package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/section-controller")
@RestController
public class SectionController {
    @Autowired
    private SectionService sectionService;
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

    @PutMapping("/updateSection")
    public Section updateSection (@RequestBody Section section) {
        return sectionService.updateSection(section);
    }

    @DeleteMapping("/deleteSection/{id}")
    public DeleteResponseDTO deleteSection(@PathVariable int id) {
        return sectionService.deleteSection(id);
    }
}