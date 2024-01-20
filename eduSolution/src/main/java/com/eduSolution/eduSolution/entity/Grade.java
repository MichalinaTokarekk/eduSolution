package com.eduSolution.eduSolution.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double value;

    private boolean isFinalValue;

    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "typeOfTestingKnowledge_id")
    private TypeOfTestingKnowledge typeOfTestingKnowledge;


    @ManyToOne
    @JoinColumn(name = "classGroup_id")
    private ClassGroup classGroup;

    @OneToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Grade(int id) {
        this.id = id;
    }

    public void setValue(double value) {
        if (value > 0 && value < 7) {
            this.value = value;
        } else {
            throw new IllegalArgumentException("Wartość 'value' musi być większa od 0 i mniejsza od 7.");
        }
    }
}
