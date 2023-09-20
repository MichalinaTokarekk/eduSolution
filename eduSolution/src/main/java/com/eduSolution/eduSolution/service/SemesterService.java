package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public Semester saveSemester (Semester semester){
        return semesterRepository.save(semester);
    }

    public List<Semester> saveSemesters (List <Semester> genres){
        return semesterRepository.saveAll(genres);
    }
    public  Semester getSemesterById (int id){
        return semesterRepository.findById(id).orElse(null);
    }

    public  List<Semester> getSemesters (){
        return semesterRepository.findAll();
    }

    public  Semester getSemesterByName (String name){
        return semesterRepository.findByName(name);
    }

    public Semester updateSemester (Semester semester){
        Semester existingGenre = semesterRepository.findById(semester.getId()).orElse(null);
        existingGenre.setName(semester.getName());
        return semesterRepository.save(existingGenre);
    }

    public DeleteResponseDTO deleteSemester(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        Semester semester = semesterRepository.findById(id).orElse(null);
        semesterRepository.deleteById(id);
//        return "Semestr " + name + " został usunięty";
        return semester != null ? new DeleteResponseDTO(semester.getId(), semester.getName()) : null;
    }
}
