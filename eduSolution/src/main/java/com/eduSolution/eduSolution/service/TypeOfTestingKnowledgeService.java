package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.entity.TypeOfTestingKnowledge;
import com.eduSolution.eduSolution.repository.HomeworkTestRepository;
import com.eduSolution.eduSolution.repository.SectionRepository;
import com.eduSolution.eduSolution.repository.TypeOfTestingKnowledgeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfTestingKnowledgeService {

    @Autowired
    private TypeOfTestingKnowledgeRespository typeOfTestingKnowledgeRespository;

    @Autowired
    private SectionRepository sectionRepository;

    public TypeOfTestingKnowledge saveTypeOfTestingKnowledge (TypeOfTestingKnowledge typeOfTestingKnowledge){
        return typeOfTestingKnowledgeRespository.save(typeOfTestingKnowledge);
    }

    public List<TypeOfTestingKnowledge> saveTypesOfTestingKnowledge (List <TypeOfTestingKnowledge> typesOfTestingKnowledge){
        return typeOfTestingKnowledgeRespository.saveAll(typesOfTestingKnowledge);
    }
    public  TypeOfTestingKnowledge getTypeOfTestingKnowledgeById (int id){
        return typeOfTestingKnowledgeRespository.findById(id).orElse(null);
    }

    public  List<TypeOfTestingKnowledge> getTypesOfTestingKnowledge (){
        return typeOfTestingKnowledgeRespository.findAll();
    }

    public  TypeOfTestingKnowledge getTypeOfTestingKnowledgeByName (String name){
        return typeOfTestingKnowledgeRespository.findByName(name);
    }

//    public List<HomeworkTest> getHomeworkTestsBySectionId(int sectionId) {
//        return homeworkTestRepository.findBySectionId(sectionId);
//    }

    public TypeOfTestingKnowledge updateTypeOfTestingKnowledge (TypeOfTestingKnowledge typeOfTestingKnowledge){
        TypeOfTestingKnowledge existingTypeOfTestingKnowledge = typeOfTestingKnowledgeRespository.findById(typeOfTestingKnowledge.getId()).orElse(null);
        existingTypeOfTestingKnowledge.setName(typeOfTestingKnowledge.getName());

        return typeOfTestingKnowledgeRespository.save(existingTypeOfTestingKnowledge);
    }

    public DeleteResponseDTO deleteTypeOfTestingKnowledge(int id){
//        List<Course> coursesBySemesterId = courseRepository.findBySemesterId(id);
//        if (!coursesBySemesterId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje kurs w tym semestrze");
//        }
        TypeOfTestingKnowledge typeOfTestingKnowledge = typeOfTestingKnowledgeRespository.findById(id).orElse(null);
        typeOfTestingKnowledgeRespository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return typeOfTestingKnowledge != null ? new DeleteResponseDTO(typeOfTestingKnowledge.getId(), typeOfTestingKnowledge.getName()) : null;
    }
}
