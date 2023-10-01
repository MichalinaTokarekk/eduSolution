package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.EMFile;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.service.CourseService;
import com.eduSolution.eduSolution.service.EMFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/emFile-controller")
@RestController
public class EMFileController {
    @Autowired
    private EMFileService emFileService;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        String uploadFile = emFileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFile);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) {
        byte[] fileData = emFileService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("application/doc; application/pdf; image/png"))
                .body(fileData);
    }


    @PostMapping("/addEMFile")
    public EMFile addEMFile (@RequestBody EMFile emFile){
        return emFileService.saveEMFile(emFile);
    }
//    @PostMapping("/addEMFiles")
//    public List<EMFile> addEMFiles (@RequestBody List<EMFile> emFiles){
//        return emFileService.saveEMFiles(emFiles);
//    }
//    @GetMapping("/emFiles")
//    public List<EMFile> findAllEMFiles() {
//        return emFileService.getEMFiles();
//    }
    @GetMapping ("/emFile/{id}")
    public EMFile findEMFileById(@PathVariable int id) {
        return emFileService.getEMFileById(id);
    }
//    @GetMapping ("/emFileeName/{name}")
//    public EMFile findEMFileByName(@PathVariable String name) {
//        return emFileService.getEMFileByName(name);
//    }
//

    @GetMapping (value = "/emFilesByEduMaterialId/{eduMaterialId}")
    public List<EMFile> findEduMaterialsBySectionId(@PathVariable int eduMaterialId) {
        return emFileService.getEMFilesByEduMaterialId(eduMaterialId);
    }

    @PutMapping("/updateEMFile")
    public EMFile updateEMFile (@RequestBody EMFile emFile) {
        return emFileService.updateEMFile(emFile);
    }
//
    @DeleteMapping("/deleteEMFile/{id}")
    public DeleteResponseDTO deleteEMFile(@PathVariable int id) {
        return emFileService.deleteEMFile(id);
    }
}
