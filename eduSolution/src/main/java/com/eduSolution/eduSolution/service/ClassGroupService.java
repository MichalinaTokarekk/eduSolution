package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassGroupService {
    @Autowired
    private ClassGroupRepository classgroupRepository;

    @Autowired
    private SemesterRepository semesterRepository;

    public ClassGroup saveClassGroup (ClassGroup group){
        Semester semester = semesterRepository.findById(group.getSemester().getId()).orElse(null);
        group.setSemester(semester);
        return classgroupRepository.save(group);
    }

    public List<ClassGroup> saveClassGroups (List <ClassGroup> classGroup){
        return classgroupRepository.saveAll(classGroup);
    }
    public  ClassGroup getClassGroupById (int id){
        return classgroupRepository.findById(id).orElse(null);
    }

    public  List<ClassGroup> getClassGroups (){
        return classgroupRepository.findAll();
    }

    public  ClassGroup getClassGroupByName (String name){
        return classgroupRepository.findByName(name);
    }

    public ClassGroup updateClassGroup (ClassGroup classGroup){
        ClassGroup existingClassGroup = classgroupRepository.findById(classGroup.getId()).orElse(null);
        existingClassGroup.setName(classGroup.getName());
        existingClassGroup.setSemester(classGroup.getSemester());
        return classgroupRepository.save(existingClassGroup);
    }

    public DeleteResponseDTO deleteClassGroup(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        ClassGroup classGroup = classgroupRepository.findById(id).orElse(null);
        classgroupRepository.deleteById(id);
//        return "Grupa " + name + " został usunięty";
        return classGroup != null ? new DeleteResponseDTO(classGroup.getId(), classGroup.getName()) : null;
    }
}
