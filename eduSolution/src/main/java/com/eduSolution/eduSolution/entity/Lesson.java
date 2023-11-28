package com.eduSolution.eduSolution.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Lesson {
    @Id
    @GeneratedValue
    private int id;
    private String name;

//    private ArrayList<String> dates;

    @ElementCollection
    @CollectionTable(name = "lesson_dates", joinColumns = @JoinColumn(name = "lesson_id"))
    @Column(name = "lesson_date")
    private List<String> dates;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startLessonTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endLessonTime;

    private String dayName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "class_group_id")
    private ClassGroup classGroup;

    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
