package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.HTFile;
import com.eduSolution.eduSolution.service.EMFileService;
import com.eduSolution.eduSolution.service.HTFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/htFile-controller")
@RestController
public class HTFileController {
    @Autowired
    private HTFileService htFileService;

    @PostMapping("/{homeworkTestId}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int homeworkTestId) throws IOException {
        String uploadFile = htFileService.uploadFile(file, homeworkTestId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFile);
    }

//    @GetMapping("/{fileName}")
//    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
//        byte[] fileData = emFileService.downloadFile(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("application/doc; application/pdf; image/png"))
//                .body(fileData);
//    }


    @GetMapping("/{fileId}")
    public ResponseEntity<byte[]> downloadFileById(@PathVariable int fileId) {
        return htFileService.downloadFileById(fileId);
    }

    @PostMapping("/addHTFile")
    public HTFile addEMFile (@RequestBody HTFile htFile){
        return htFileService.saveHTFile(htFile);
    }
    //    @PostMapping("/addEMFiles")
//    public List<EMFile> addEMFiles (@RequestBody List<EMFile> emFiles){
//        return emFileService.saveEMFiles(emFiles);
//    }
//    @GetMapping("/emFiles")
//    public List<EMFile> findAllEMFiles() {
//        return emFileService.getEMFiles();
//    }
    @GetMapping ("/htFile/{id}")
    public HTFile findEMFileById(@PathVariable int id) {
        return htFileService.getHTFileById(id);
    }


//    @GetMapping ("/emFileeName/{name}")
//    public EMFile findEMFileByName(@PathVariable String name) {
//        return emFileService.getEMFileByName(name);
//    }
//

    @GetMapping (value = "/htFilesByHomeworkTestId/{homeworkTestId}")
    public List<HTFile> findHTFilessByHomeworkTestId(@PathVariable int homeworkTestId) {
        return htFileService.getHTFilesByEduMaterialId(homeworkTestId);
    }

    @PutMapping("/updateHTFile")
    public HTFile updateHTFile (@RequestBody HTFile htFile) {
        return htFileService.updateHTFile(htFile);
    }
    //
    @DeleteMapping("/deleteHTFile/{id}")
    public DeleteResponseDTO deleteHTFile(@PathVariable int id) {
        return htFileService.deleteHTFile(id);
    }
}
