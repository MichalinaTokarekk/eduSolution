package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.repository.EduMaterialRepository;
import com.eduSolution.eduSolution.repository.HomeworkTestRepository;
import com.eduSolution.eduSolution.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HomeworkTestService {
    @Autowired
    private HomeworkTestRepository homeworkTestRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public HomeworkTest saveHomeworkTest (HomeworkTest homeworkTest){
        homeworkTest.setSection(sectionRepository.findById(homeworkTest.getSection().getId()).orElse(null));
        return homeworkTestRepository.save(homeworkTest);
    }

    public HomeworkTest addHomeworkTest (HomeworkTest homeworkTest, int sectionId) {
        Section section = sectionRepository.findById(sectionId).orElse(null);
        homeworkTest.setSection((Section) section);
        return homeworkTestRepository.save(homeworkTest);
    }

    public List<HomeworkTest> saveHomeworkTests (List <HomeworkTest> homeworkTests){
        return homeworkTestRepository.saveAll(homeworkTests);
    }
    public  HomeworkTest getHomeworkTestById (int id){
        return homeworkTestRepository.findById(id).orElse(null);
    }

    public  List<HomeworkTest> getHomeworkTests (){
        return homeworkTestRepository.findAll();
    }

    public  HomeworkTest getHomeworkTestByName (String name){
        return homeworkTestRepository.findByName(name);
    }

    public List<HomeworkTest> getHomeworkTestsBySectionId(int sectionId) {
        return homeworkTestRepository.findBySectionId(sectionId);
    }

    public HomeworkTest updateHomeworkTest (HomeworkTest homeworkTest){
        HomeworkTest existingHomeworkTest = homeworkTestRepository.findById(homeworkTest.getId()).orElse(null);
        existingHomeworkTest.setName(homeworkTest.getName());
        existingHomeworkTest.setTaskContent(homeworkTest.getTaskContent());

        return homeworkTestRepository.save(existingHomeworkTest);
    }

    public DeleteResponseDTO deleteHomeworkTest(int id){
//        List<Course> coursesBySemesterId = courseRepository.findBySemesterId(id);
//        if (!coursesBySemesterId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje kurs w tym semestrze");
//        }
        HomeworkTest homeworkTest = homeworkTestRepository.findById(id).orElse(null);
        homeworkTestRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return homeworkTest != null ? new DeleteResponseDTO(homeworkTest.getId(), homeworkTest.getName()) : null;
    }
}
