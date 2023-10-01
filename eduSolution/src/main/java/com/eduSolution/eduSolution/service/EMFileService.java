package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.repository.EMFileRepository;
import com.eduSolution.eduSolution.repository.EduMaterialRepository;
import com.eduSolution.eduSolution.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class EMFileService {
    @Autowired
    private EMFileRepository emFileRepository;

    @Autowired
    private EduMaterialRepository eduMaterialRepository;

    public String uploadFile(MultipartFile file) throws IOException {
        EMFile fileData = emFileRepository.save(EMFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes())).build());
        if (fileData!=null) {
            return "File uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

//    public byte[] downloadFile(String fileName) {
//        Optional<EMFile> dbFileData = emFileRepository.findByName(fileName);
//        byte[] files = FileUtils.decompressFile(dbFileData.orElseThrow().getFileData());
//        return files;
//    }

    public ResponseEntity<byte[]> downloadFileById(int fileId) {
        Optional<EMFile> dbFileData = emFileRepository.findById(fileId);

        if (dbFileData.isPresent()) {
            EMFile emFile = dbFileData.get();
            byte[] fileContent = FileUtils.decompressFile(emFile.getFileData());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", emFile.getName());

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } else {
            // Tutaj możesz obsłużyć sytuację, gdy plik o danym ID nie został znaleziony
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public EMFile saveEMFile (EMFile emFile){
        return emFileRepository.save(emFile);
    }
//
//    public List<EMFile> saveEMFiles (List <EMFile> emFiles){
//        return emFileRepository.saveAll(emFiles);
//    }
    public  EMFile getEMFileById (int id){
        return emFileRepository.findById(id).orElse(null);
    }
//
//    public  List<EMFile> getEMFiles (){
//        return emFileRepository.findAll();
//    }
//
//    public Optional<EMFile> getEMFileByName (String name){
//        return emFileRepository.findByName(name);
//    }
//

    public List<EMFile> getEMFilesByEduMaterialId(int sectionId) {
        return emFileRepository.findByEduMaterialsId(sectionId);
    }

    public EMFile updateEMFile (EMFile emFile){
        EMFile existingEMFile = emFileRepository.findById(emFile.getId()).orElse(null);
//        existingCourse.setName(emFile.getName());
//        existingCourse.setType(emFile.getType());
        Set<EduMaterial> eduMaterials = new HashSet<>();
        for (EduMaterial eduMaterial : emFile.getEduMaterials()) {
            Iterable<EduMaterial> eduMaterialsById = eduMaterialRepository.findAllById(Collections.singleton(eduMaterial.getId()));
            eduMaterialsById.forEach(eduMaterials::add);
        }
        existingEMFile.setEduMaterials(eduMaterials);
        return emFileRepository.save(existingEMFile);
    }
//
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
