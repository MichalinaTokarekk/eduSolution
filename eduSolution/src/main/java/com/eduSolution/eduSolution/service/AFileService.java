package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.AFile;
import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.HTFile;
import com.eduSolution.eduSolution.entity.HomeworkTest;
import com.eduSolution.eduSolution.repository.AFileRepository;
import com.eduSolution.eduSolution.repository.AnswerRepository;
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
public class AFileService {
    @Autowired
    private AFileRepository aFileRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public String uploadFile(MultipartFile file, int answerId) throws IOException {
        Answer answer = answerRepository.findById(answerId).orElse(null);
        AFile fileData = aFileRepository.save(AFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes()))
                .answer(answer).build());
//        if (fileData!=null) {
//            return "File uploaded successfully : " + file.getOriginalFilename();
//        }
        AFile savedFile = aFileRepository.save(fileData);
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
        Optional<AFile> dbFileData = aFileRepository.findById(fileId);

        if (dbFileData.isPresent()) {
            AFile aFile = dbFileData.get();
            byte[] fileContent = FileUtils.decompressFile(aFile.getFileData());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", aFile.getName());

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } else {
            // Tutaj możesz obsłużyć sytuację, gdy plik o danym ID nie został znaleziony
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    public AFile saveAFile (AFile aFile){
//        Set<EduMaterial> eduMaterials = new HashSet<>();
//        for (EduMaterial eduMaterial : emFile.getEduMaterials()) {
//            Iterable<EduMaterial> eduMaterialsById = eduMaterialRepository.findAllById(Collections.singleton(eduMaterial.getId()));
//            eduMaterialsById.forEach(eduMaterials::add);
//        }
//        emFile.setEduMaterials(eduMaterials);
        aFile.setAnswer(answerRepository.findById(aFile.getAnswer().getId()).orElse(null));
        return aFileRepository.save(aFile);
    }
    //
//    public List<EMFile> saveEMFiles (List <EMFile> emFiles){
//        return emFileRepository.saveAll(emFiles);
//    }
    public  AFile getAFileById (int id){
        return aFileRepository.findById(id).orElse(null);
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

    public List<AFile> getAFilesByAnswerId(int answerId) {
        return aFileRepository.findByAnswerId(answerId);
    }

    public AFile updateAFile (AFile aFile){
        AFile existingAFile = aFileRepository.findById(aFile.getId()).orElse(null);
        existingAFile.setAnswer(answerRepository.findById(aFile.getAnswer().getId()).orElse(null));
        return aFileRepository.save(existingAFile);
    }
    //
    public DeleteResponseDTO deleteAFile(int id){
//        List<Book> booksByGenreId = bookRepository.findByGenreId(id);
//        if (!booksByGenreId.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nie można usunąć tego pola. Istnieje książa o takim gatunku");
//        }
        AFile aFile = aFileRepository.findById(id).orElse(null);
        aFileRepository.deleteById(id);
//        return "Kurs " + name + " został usunięty";
        return aFile != null ? new DeleteResponseDTO(aFile.getId(), aFile.getName()) : null;
    }
}
