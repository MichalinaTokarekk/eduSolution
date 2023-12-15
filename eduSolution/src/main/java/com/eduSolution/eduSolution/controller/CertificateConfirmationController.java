package com.eduSolution.eduSolution.controller;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.CertificateConfirmation;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.service.CertificateConfirmationService;
import com.eduSolution.eduSolution.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/certificateConfirmation-controller")
@RestController
public class CertificateConfirmationController {
    @Autowired
    private CertificateConfirmationService certificateConfirmationService;
    @PostMapping("/addCertificateConfirmation")
    public CertificateConfirmation addCertificateConfirmation (@RequestBody CertificateConfirmation certificateConfirmation){
        return certificateConfirmationService.saveCertificateConfirmation(certificateConfirmation);
    }
    @PostMapping("/addCertificateConfirmations")
    public List<CertificateConfirmation> addCertificateConfirmations (@RequestBody List<CertificateConfirmation> certificateConfirmations){
        return certificateConfirmationService.saveCertificateConfirmations(certificateConfirmations);
    }
    @GetMapping("/certificateConfirmations")
    public List<CertificateConfirmation> findAllCertificateConfirmations() {
        return certificateConfirmationService.getCertificateConfirmations();
    }
    @GetMapping ("/certificateConfirmation/{id}")
    public CertificateConfirmation findCertificateConfirmationById(@PathVariable int id) {
        return certificateConfirmationService.getCertificateConfirmationById(id);
    }

    @GetMapping("/findCertificateConfirmationByUserIdAndClassGroupId/{userId}/{classGroupId}")
    public CertificateConfirmation findCertificateConfirmationByUserIdAndClassGroupId(@PathVariable Integer userId, @PathVariable Integer classGroupId) {
        return certificateConfirmationService.findCertificateConfirmationByUserIdAndClassGroupId(userId, classGroupId);
    }

    @PutMapping("/updateCertificateConfirmation")
    public CertificateConfirmation updateCertificateConfirmation (@RequestBody CertificateConfirmation certificateConfirmation) {
        return certificateConfirmationService.updateCertificateConfirmation(certificateConfirmation);
    }

    @DeleteMapping("/deleteCertificateConfirmation/{id}")
    public String deleteCertificateConfirmation(@PathVariable int id) {
        return certificateConfirmationService.deleteCertificateConfirmation(id);
    }
}
