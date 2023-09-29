package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.EMFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMFileService {
    @Autowired
    private EMFileRepository emFileRepository;

    public EMFile saveEMFile (EMFile emFile){
        return emFileRepository.save(emFile);
    }

    public List<EMFile> saveEMFiles (List <EMFile> emFiles){
        return emFileRepository.saveAll(emFiles);
    }
    public  EMFile getEMFileById (int id){
        return emFileRepository.findById(id).orElse(null);
    }

    public  List<EMFile> getEMFiles (){
        return emFileRepository.findAll();
    }

    public  EMFile getEMFileByName (String name){
        return emFileRepository.findByName(name);
    }

    public EMFile updateEMFile (EMFile emFile){
        EMFile existingCourse = emFileRepository.findById(emFile.getId()).orElse(null);
        existingCourse.setName(emFile.getName());
        existingCourse.setFilePath(emFile.getFilePath());
        return emFileRepository.save(existingCourse);
    }

    public DeleteResponseDTO deleteEMFile(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        EMFile emFile = emFileRepository.findById(id).orElse(null);
        emFileRepository.deleteById(id);
//        return "Kurs " + name + " został usunięty";
        return emFile != null ? new DeleteResponseDTO(emFile.getId(), emFile.getName()) : null;
    }
}
