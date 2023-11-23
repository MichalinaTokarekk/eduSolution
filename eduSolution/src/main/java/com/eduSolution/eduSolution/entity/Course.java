package com.eduSolution.eduSolution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Course {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private double amountToPay;

    private double cashAdvance;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    @Column(length = 1000)
    private String description;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Course() {
    }

    public Course(int id) {
        this.id = id;
    }

}
