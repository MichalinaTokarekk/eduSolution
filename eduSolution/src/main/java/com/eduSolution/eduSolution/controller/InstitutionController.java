package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Institution;
import com.eduSolution.eduSolution.service.CourseService;
import com.eduSolution.eduSolution.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/institution-controller")
@RestController
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;
    @PostMapping("/addInstitution")
    public Institution addInstitution (@RequestBody Institution institution){
        return institutionService.saveInstitution(institution);
    }
    @PostMapping("/addInstitutions")
    public List<Institution> addInstitution (@RequestBody List<Institution> institutions){
        return institutionService.saveInstitutions(institutions);
    }
    @GetMapping("/institution")
    public List<Institution> findAllInstitutions() {
        return institutionService.getInstitutions();
    }
    @GetMapping ("/institution/{id}")
    public Institution findInstitutionById(@PathVariable int id) {
        return institutionService.getInstitutionById(id);
    }
    @GetMapping ("/findInstitutionByAddress/{name}")
    public Institution findInstitutionByAddress(@PathVariable String address) {
        return institutionService.getCourseByAddress(address);
    }


    @PutMapping("/updateInstitution")
    public Institution updateInstitution (@RequestBody Institution institution) {
        return institutionService.updateInstitution(institution);
    }

    @DeleteMapping("/deleteInstitution/{id}")
    public DeleteResponseLongDTO deleteInstitution(@PathVariable long id) {
        return institutionService.deleteInstitution(id);
    }
}
