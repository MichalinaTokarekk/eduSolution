package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.entity.*;
import com.eduSolution.eduSolution.repository.CertificateConfirmationRepository;
import com.eduSolution.eduSolution.repository.ClassGroupRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateConfirmationService {

    @Autowired
    private CertificateConfirmationRepository certificateConfirmationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassGroupRepository classGroupRepository;

    public CertificateConfirmation saveCertificateConfirmation (CertificateConfirmation certificateConfirmation){
        User user = certificateConfirmation.getUser();
        if(user != null) {
            user = userRepository.findById(user.getId()).orElse(null);
            certificateConfirmation.setUser(user);
        }

        ClassGroup classGroup = certificateConfirmation.getClassGroup();
        if(classGroup != null) {
            classGroup = classGroupRepository.findById(classGroup.getId()).orElse(null);
            certificateConfirmation.setClassGroup(classGroup);
        }
//        certificateConfirmation.setGained(false);
        return certificateConfirmationRepository.save(certificateConfirmation);
    }

    public List<CertificateConfirmation> saveCertificateConfirmations (List <CertificateConfirmation> certificateConfirmations){
        return certificateConfirmationRepository.saveAll(certificateConfirmations);
    }
    public CertificateConfirmation getCertificateConfirmationById (long id){
        return certificateConfirmationRepository.findById(id).orElse(null);
    }

    public List<CertificateConfirmation> getCertificateConfirmations (){
        return certificateConfirmationRepository.findAll();
    }

    public CertificateConfirmation findCertificateConfirmationByUserIdAndClassGroupId(Integer userId, Integer classGroupId) {
        return certificateConfirmationRepository.findCertificateConfirmationByUserIdAndClassGroupId(userId, classGroupId);
    }


    public CertificateConfirmation updateCertificateConfirmation (CertificateConfirmation certificateConfirmation){
        CertificateConfirmation existingCertificateConfirmation = certificateConfirmationRepository.findById(certificateConfirmation.getId()).orElse(null);
        existingCertificateConfirmation.setGained(certificateConfirmation.isGained());
        existingCertificateConfirmation.setPercentageScore(certificateConfirmation.getPercentageScore());

        User user = certificateConfirmation.getUser();
        if(user != null) {
            user = userRepository.findById(user.getId()).orElse(null);
            existingCertificateConfirmation.setUser(user);
        }

        ClassGroup classGroup = certificateConfirmation.getClassGroup();
        if(classGroup != null) {
            classGroup = classGroupRepository.findById(classGroup.getId()).orElse(null);
            existingCertificateConfirmation.setClassGroup(classGroup);
        }

        return certificateConfirmationRepository.save(existingCertificateConfirmation);
    }

    public String deleteCertificateConfirmation(long id){
        CertificateConfirmation certificateConfirmation = certificateConfirmationRepository.findById(id).orElse(null);
        certificateConfirmationRepository.deleteById(id);
        return "Potwierdzenie otrzymania certyfikatu  został usunięty";
    }
}
