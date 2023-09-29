package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.EduMaterialRepository;
import com.eduSolution.eduSolution.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EduMaterialRepository eduMaterialRepository;

    public Section saveSection(Section section) {
        section.setCourse(courseRepository.findById(section.getCourse().getId()).orElse(null));
        Set<EduMaterial> eduMaterials = new HashSet<>();
        for (EduMaterial author : section.getEduMaterials()) {
            Iterable<EduMaterial> authorsById = eduMaterialRepository.findAllById(Collections.singleton(author.getId()));
            authorsById.forEach(eduMaterials::add);
        }
        section.setEduMaterials(eduMaterials);
        return sectionRepository.save(section);
    }

    public List<Section> saveSections(List<Section> sections) {
        return sectionRepository.saveAll(sections);
    }

    public Section getSectionById(int id) {
        return sectionRepository.findById(id).orElse(null);
    }

    public List<Section> getSections() {
        return sectionRepository.findAll();
    }

    public Section getSectionByName(String name) {
        return sectionRepository.findByName(name);
    }

    public List<Section> getSectionsByCourse(int courseId) {
        return sectionRepository.findByCourseId(courseId);
    }


    public Section updateSection (Section section){
        Section existingSection = sectionRepository.findById(section.getId()).orElse(null);
        existingSection.setName(section.getName());
        return sectionRepository.save(existingSection);
    }

    public DeleteResponseDTO deleteSection(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        Section section = sectionRepository.findById(id).orElse(null);
        sectionRepository.deleteById(id);
//        return "Kurs " + name + " został usunięty";
        return section != null ? new DeleteResponseDTO(section.getId(), section.getName()) : null;
    }
}
