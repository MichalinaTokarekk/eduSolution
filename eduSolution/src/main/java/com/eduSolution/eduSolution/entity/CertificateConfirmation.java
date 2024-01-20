package com.eduSolution.eduSolution.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class CertificateConfirmation {
    @Id
    @GeneratedValue
    private Long id;
    boolean gained;
    private double percentageScore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "class_group_id")
    private ClassGroup classGroup;

    public void setPercentageScore(double percentageScore) {
        if (percentageScore >= 0 && percentageScore <= 100) {
            this.percentageScore = percentageScore;
        } else {
            throw new IllegalArgumentException("PercentageScore musi być większe od 0 i mniejsze od 100.");
        }
    }

}
