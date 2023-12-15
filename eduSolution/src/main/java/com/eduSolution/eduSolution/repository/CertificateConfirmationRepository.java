package com.eduSolution.eduSolution.repository;

import com.eduSolution.eduSolution.entity.Answer;
import com.eduSolution.eduSolution.entity.CertificateConfirmation;
import com.eduSolution.eduSolution.entity.ClassGroup;
import com.eduSolution.eduSolution.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CertificateConfirmationRepository extends JpaRepository<CertificateConfirmation,Long> {
    @Query("SELECT cc FROM CertificateConfirmation cc WHERE cc.user.id = :userId AND cc.classGroup.id = :classGroupId")
    CertificateConfirmation findCertificateConfirmationByUserIdAndClassGroupId(@Param("userId") Integer userId, @Param("classGroupId") Integer classGroupId);


}
