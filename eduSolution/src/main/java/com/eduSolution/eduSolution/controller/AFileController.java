package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.AFile;
import com.eduSolution.eduSolution.entity.HTFile;
import com.eduSolution.eduSolution.service.AFileService;
import com.eduSolution.eduSolution.service.HTFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/aFile-controller")
@RestController
public class AFileController {
    @Autowired
    private AFileService aFileService;

    @PostMapping("/{answerId}")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable int answerId) throws IOException {
        String uploadFile = aFileService.uploadFile(file, answerId);
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
        return aFileService.downloadFileById(fileId);
    }

    @PostMapping("/addAFile")
    public AFile addEMFile (@RequestBody AFile aFile){
        return aFileService.saveAFile(aFile);
    }
    //    @PostMapping("/addEMFiles")
//    public List<EMFile> addEMFiles (@RequestBody List<EMFile> emFiles){
//        return emFileService.saveEMFiles(emFiles);
//    }
//    @GetMapping("/emFiles")
//    public List<EMFile> findAllEMFiles() {
//        return emFileService.getEMFiles();
//    }
    @GetMapping ("/aFile/{id}")
    public AFile findAFileById(@PathVariable int id) {
        return aFileService.getAFileById(id);
    }


//    @GetMapping ("/emFileeName/{name}")
//    public EMFile findEMFileByName(@PathVariable String name) {
//        return emFileService.getEMFileByName(name);
//    }
//

    @GetMapping (value = "/aFilesByAnswerId/{answerId}")
    public List<AFile> findAFilessByAnswerId(@PathVariable int answerId) {
        return aFileService.getAFilesByAnswerId(answerId);
    }

    @PutMapping("/updateAFile")
    public AFile updateAFile (@RequestBody AFile aFile) {
        return aFileService.updateAFile(aFile);
    }
    //
    @DeleteMapping("/deleteAFile/{id}")
    public DeleteResponseDTO deleteAFile(@PathVariable int id) {
        return aFileService.deleteAFile(id);
    }
}
