package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.EMPicture;
import com.eduSolution.eduSolution.repository.EMFileRepository;
import com.eduSolution.eduSolution.repository.EMPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMPictureService {
    @Autowired
    private EMPictureRepository emPictureRepository;

    public EMPicture saveEMPicture (EMPicture emPicture){
        return emPictureRepository.save(emPicture);
    }

    public List<EMPicture> saveEMPictures (List <EMPicture> emPictures){
        return emPictureRepository.saveAll(emPictures);
    }
    public  EMPicture getEMPictureById (int id){
        return emPictureRepository.findById(id).orElse(null);
    }

    public  List<EMPicture> getEMPictures (){
        return emPictureRepository.findAll();
    }

    public  EMPicture getEMPictureByName (String name){
        return emPictureRepository.findByName(name);
    }

    public EMPicture updateEMPicture (EMPicture emPicture){
        EMPicture existingEMPicture = emPictureRepository.findById(emPicture.getId()).orElse(null);
        existingEMPicture.setName(emPicture.getName());
        existingEMPicture.setFilePath(emPicture.getFilePath());
        existingEMPicture.setDescription(emPicture.getDescription());
        return emPictureRepository.save(existingEMPicture);
    }

    public DeleteResponseDTO deleteEMFile(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        EMPicture emPicture = emPictureRepository.findById(id).orElse(null);
        emPictureRepository.deleteById(id);
//        return "Kurs " + name + " został usunięty";
        return emPicture != null ? new DeleteResponseDTO(emPicture.getId(), emPicture.getName()) : null;
    }
}
