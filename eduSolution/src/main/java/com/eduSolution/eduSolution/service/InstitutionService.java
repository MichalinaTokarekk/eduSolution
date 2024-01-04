package com.eduSolution.eduSolution.service;

import com.eduSolution.eduSolution.dto.DeleteResponseDTO;
import com.eduSolution.eduSolution.dto.DeleteResponseLongDTO;
import com.eduSolution.eduSolution.entity.Course;
import com.eduSolution.eduSolution.entity.Institution;
import com.eduSolution.eduSolution.repository.CourseRepository;
import com.eduSolution.eduSolution.repository.InstitutionRepository;
import com.eduSolution.eduSolution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionService {
    @Autowired
    private InstitutionRepository institutionRepository;

    public Institution saveInstitution (Institution institution){
        return institutionRepository.save(institution);
    }
    public List<Institution> saveInstitutions (List <Institution> institutions){
        return institutionRepository.saveAll(institutions);
    }
    public  Institution getInstitutionById (long id){
        return institutionRepository.findById(id).orElse(null);
    }

    public  List<Institution> getInstitutions (){
        return institutionRepository.findAll();
    }

    public  Institution getCourseByAddress (String address){
        return institutionRepository.findByAddress(address);
    }


    public Institution updateInstitution (Institution institution){
        Institution existingInstitution = institutionRepository.findById(institution.getId()).orElse(null);
        existingInstitution.setAddress(institution.getAddress());
        return institutionRepository.save(existingInstitution);
    }

    public DeleteResponseLongDTO deleteInstitution(long id){
        Institution institution = institutionRepository.findById(id).orElse(null);
        institutionRepository.deleteById(id);
        return institution != null ? new DeleteResponseLongDTO(institution.getId(), institution.getAddress()) : null;
    }
}
