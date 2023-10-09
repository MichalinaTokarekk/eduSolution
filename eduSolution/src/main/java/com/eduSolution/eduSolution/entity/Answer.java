package com.eduSolution.eduSolution.entity;

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

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String answerContent;
    private String comment;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "homeworkTest_id")
    private HomeworkTest homeworkTest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Answer(int id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    private AnswerStatus answerStatus;
}
