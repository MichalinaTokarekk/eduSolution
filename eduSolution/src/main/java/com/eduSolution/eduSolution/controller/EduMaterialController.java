package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.EduMaterial;
import com.eduSolution.eduSolution.entity.Section;
import com.eduSolution.eduSolution.entity.Semester;
import com.eduSolution.eduSolution.service.EduMaterialService;
import com.eduSolution.eduSolution.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/edu-material-controller")
@RestController
public class EduMaterialController {
    @Autowired
    private EduMaterialService eduMaterialService;
    @PostMapping("/addEduMaterial")
    public EduMaterial addEduMaterial (@RequestBody EduMaterial eduMaterial){
        return eduMaterialService.saveEduMaterial(eduMaterial);
    }
    @PostMapping("/addEduMaterials")
    public List<EduMaterial> addEduMaterials (@RequestBody List<EduMaterial> eduMaterials){
        return eduMaterialService.saveEduMaterials(eduMaterials);
    }
    @GetMapping("/eduMaterials")
    public List<EduMaterial> findAllEduMaterials() {
        return eduMaterialService.getEduMaterials();
    }
    @GetMapping ("/eduMaterial/{id}")
    public EduMaterial findSemesterById(@PathVariable int id) {
        return eduMaterialService.getEduMaterialById(id);
    }
    @GetMapping ("/eduMaterialName/{name}")
    public EduMaterial findEduMaterialByTitle(@PathVariable String name) {
        return eduMaterialService.getEduMaterialByName(name);
    }
//    @GetMapping (value = "/eduMaterialsBySection/{sectionId}")
//    public List<EduMaterial> findEduMaterialsBySectionId(@PathVariable int sectionId) {
//        return eduMaterialService.getEduMaterialsBySection(sectionId);
//    }

    @PutMapping("/updateEduMaterial")
    public EduMaterial updateEduMaterial (@RequestBody EduMaterial eduMaterial) {
        return eduMaterialService.updateEduMaterial(eduMaterial);
    }

    @DeleteMapping("/deleteEduMaterial/{id}")
    public DeleteResponseDTO deleteEduMaterial(@PathVariable int id) {
        return eduMaterialService.deleteEduMaterial(id);
    }
}
