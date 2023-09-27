package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public Section saveSection (Section section){
        return sectionRepository.save(section);
    }

    public List<Section> saveSections (List <Section> sections){
        return sectionRepository.saveAll(sections);
    }
    public  Section getSectionById (int id){
        return sectionRepository.findById(id).orElse(null);
    }

    public  List<Section> getSections (){
        return sectionRepository.findAll();
    }

    public  Section getSectionByName (String name){
        return sectionRepository.findByName(name);
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
