package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.GradeRepository;
import com.eduSolution.eduSolution.repository.TypeOfTestingKnowledgeRespository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeOfTestingKnowledgeRespository typeOfTestingKnowledgeRespository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SemesterService semesterService;


    public Grade saveGrade (Grade grade){
        grade.setStudent(userRepository.findById(grade.getStudent().getId()).orElse(null));
        grade.setTeacher(userRepository.findById(grade.getTeacher().getId()).orElse(null));
        grade.setTypeOfTestingKnowledge(typeOfTestingKnowledgeRespository.findById(grade.getTypeOfTestingKnowledge().getId()).orElse(null));
        grade.setFinalValue(false);

        return gradeRepository.save(grade);
    }

    public Grade saveFinalGrade (Grade grade) {
        grade.setStudent(userRepository.findById(grade.getStudent().getId()).orElse(null));
        grade.setTeacher(userRepository.findById(grade.getTeacher().getId()).orElse(null));
        grade.setFinalValue(true);

        boolean finalGradeExists = gradeRepository.existsByStudentAndIsFinalValue(grade.getStudent(), true);

        if (finalGradeExists && grade.isFinalValue()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można dodać drugiej oceny końcowej!!!!!!!");        }
        return gradeRepository.save(grade);
    }

    public List<Grade> saveGrades (List <Grade> grades){

        return gradeRepository.saveAll(grades);
    }
    public  Grade getGradeById (long id){
        return gradeRepository.findById(id).orElse(null);
    }

    public  List<Grade> getGrades (){
        return gradeRepository.findAll();
    }

//    public List<Grade> findByStudentIdAndClassGroupeId(Integer studentId, Integer classGroupId) {
//        return gradeRepository.findByStudentIdAndClassGroupeId(studentId, classGroupId);
//    }

//
    public List<Grade> findAllByStudentAndClassGroup(Integer studentId, Integer classGroupId) {
        return gradeRepository.findAllByStudentAndClassGroup(studentId, classGroupId);
    }

//    public List<Grade> findAllByStudent(Integer studentId) {
//        return gradeRepository.findAllByStudent(studentId);
//
//    }
//
    public Grade getGradeByAnswerId(Integer answerId) {
        return gradeRepository.findByAnswerId(answerId);
    }

    public Grade updateGrade (Grade grade){
        Grade existingGrade = gradeRepository.findById(grade.getId()).orElse(null);
        existingGrade.setValue(grade.getValue());
        existingGrade.setDescription(grade.getDescription());
        existingGrade.setTypeOfTestingKnowledge(grade.getTypeOfTestingKnowledge());
        return gradeRepository.save(existingGrade);
    }

    public Grade updateFinalGrade (Grade grade){
        Grade existingGrade = gradeRepository.findById(grade.getId()).orElse(null);
        existingGrade.setValue(grade.getValue());
        existingGrade.setFinalValue(true);
        return gradeRepository.save(existingGrade);
    }

    public DeleteResponseLongDTO deleteGrade(long id){
//        List<Course> coursesBySemesterId = courseRepository.findBySemesterId(id);
//        if (!coursesBySemesterId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje kurs w tym semestrze");
//        }
        Grade grade = gradeRepository.findById(id).orElse(null);
        gradeRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return grade != null ? new DeleteResponseLongDTO(grade.getId(), grade.getDescription()) : null;
    }
}
