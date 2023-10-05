package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.HTFile;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.repository.EMFileRepository;
import com.eduSolution.eduSolution.repository.EduMaterialRepository;
import com.eduSolution.eduSolution.repository.HTFileRepository;
import com.eduSolution.eduSolution.repository.HomeworkTestRepository;
import com.eduSolution.eduSolution.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HTFileService {

    @Autowired
    private HTFileRepository htFileRepository;

    @Autowired
    private HomeworkTestRepository homeworkTestRepository;

    public String uploadFile(MultipartFile file, int homeworkTestId) throws IOException {
        HomeworkTest homeworkTest = homeworkTestRepository.findById(homeworkTestId).orElse(null);
        HTFile fileData = htFileRepository.save(HTFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes()))
                .homeworkTest(homeworkTest).build());
//        if (fileData!=null) {
//            return "File uploaded successfully : " + file.getOriginalFilename();
//        }
        HTFile savedFile = htFileRepository.save(fileData);
        if (savedFile != null) {
            int fileId = savedFile.getId();
//            updateEMFile(savedFile);
            return "File uploaded successfully. File ID: " + fileId;


        }
        return null;
    }

//    public byte[] downloadFile(String fileName) {
//        Optional<EMFile> dbFileData = emFileRepository.findByName(fileName);
//        byte[] files = FileUtils.decompressFile(dbFileData.orElseThrow().getFileData());
//        return files;
//    }

    public ResponseEntity<byte[]> downloadFileById(int fileId) {
        Optional<HTFile> dbFileData = htFileRepository.findById(fileId);

        if (dbFileData.isPresent()) {
            HTFile htFile = dbFileData.get();
            byte[] fileContent = FileUtils.decompressFile(htFile.getFileData());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", htFile.getName());

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } else {
            // Tutaj możesz obsłużyć sytuację, gdy plik o danym ID nie został znaleziony
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public HTFile saveHTFile (HTFile htFile){
//        Set<EduMaterial> eduMaterials = new HashSet<>();
//        for (EduMaterial eduMaterial : emFile.getEduMaterials()) {
//            Iterable<EduMaterial> eduMaterialsById = eduMaterialRepository.findAllById(Collections.singleton(eduMaterial.getId()));
//            eduMaterialsById.forEach(eduMaterials::add);
//        }
//        emFile.setEduMaterials(eduMaterials);
        htFile.setHomeworkTest(homeworkTestRepository.findById(htFile.getHomeworkTest().getId()).orElse(null));
        return htFileRepository.save(htFile);
    }
    //
//    public List<EMFile> saveEMFiles (List <EMFile> emFiles){
//        return emFileRepository.saveAll(emFiles);
//    }
    public  HTFile getHTFileById (int id){
        return htFileRepository.findById(id).orElse(null);
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

    public List<HTFile> getHTFilesByEduMaterialId(int homeworkTestId) {
        return htFileRepository.findByHomeworkTestId(homeworkTestId);
    }

    public HTFile updateHTFile (HTFile htFile){
        HTFile existingHTFile = htFileRepository.findById(htFile.getId()).orElse(null);
        existingHTFile.setHomeworkTest(homeworkTestRepository.findById(htFile.getHomeworkTest().getId()).orElse(null));
        return htFileRepository.save(existingHTFile);
    }
    //
    public DeleteResponseDTO deleteHTFile(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        HTFile htFile = htFileRepository.findById(id).orElse(null);
        htFileRepository.deleteById(id);
//        return "Kurs " + name + " został usunięty";
        return htFile != null ? new DeleteResponseDTO(htFile.getId(), htFile.getName()) : null;
    }
}
