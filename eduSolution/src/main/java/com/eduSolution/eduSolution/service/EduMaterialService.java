package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.EduMaterialRepository;
import com.eduSolution.eduSolution.repository.SectionRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EduMaterialService {
    @Autowired
    private EduMaterialRepository eduMaterialRepository;

    @Autowired
    private SectionRepository sectionRepository;

    public EduMaterial saveEduMaterial (EduMaterial eduMaterial){
//        eduMaterial.setSection(sectionRepository.findById(eduMaterial.getSection().getId()).orElse(null));
        return eduMaterialRepository.save(eduMaterial);
    }

    public List<EduMaterial> saveEduMaterials (List <EduMaterial> eduMaterials){
        return eduMaterialRepository.saveAll(eduMaterials);
    }
    public  EduMaterial getEduMaterialById (int id){
        return eduMaterialRepository.findById(id).orElse(null);
    }

    public  List<EduMaterial> getEduMaterials (){
        return eduMaterialRepository.findAll();
    }

    public  EduMaterial getEduMaterialByName (String name){
        return eduMaterialRepository.findByName(name);
    }

//    public List<EduMaterial> getEduMaterialsBySection(int sectionId) {
//        return eduMaterialRepository.findBySectionId(sectionId);
//    }

    public EduMaterial updateEduMaterial (EduMaterial eduMaterial){
        EduMaterial existingSemester = eduMaterialRepository.findById(eduMaterial.getId()).orElse(null);
        existingSemester.setName(eduMaterial.getName());
        existingSemester.setDescription(eduMaterial.getDescription());
//        Set<Course> courses = new HashSet<>();
//        for (Course course : semester.getCourses()) {
//            Iterable<Course> coursesById = courseRepository.findAllById(Collections.singleton(course.getId()));
//            coursesById.forEach(courses::add);
//        }
//        existingSemester.setCourses(courses);
        return eduMaterialRepository.save(existingSemester);
    }

    public DeleteResponseDTO deleteEduMaterial(int id){
//        List<Course> coursesBySemesterId = courseRepository.findBySemesterId(id);
//        if (!coursesBySemesterId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje kurs w tym semestrze");
//        }
        EduMaterial eduMaterial = eduMaterialRepository.findById(id).orElse(null);
        eduMaterialRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return eduMaterial != null ? new DeleteResponseDTO(eduMaterial.getId(), eduMaterial.getName()) : null;
    }
}
