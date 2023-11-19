package com.eduSolution.eduSolution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ClassGroup {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    private int studentsLimit;

    private String year;

    @Enumerated(EnumType.STRING)
    private ClassGroupStatus classGroupStatus;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable (name = "classGroups_to_users",
//            joinColumns = {@JoinColumn(name = "classGroup_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    private Set<User> users = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable (name = "classGroups_to_lessons",
//            joinColumns = {@JoinColumn(name = "classGroup_id")},
//            inverseJoinColumns = {@JoinColumn(name = "lesson_id")})
//    private Set<Lesson> lessons = new HashSet<>();


//    @PreRemove
//    private void removeRelations() {
//        lessons.clear();
//    }

    public ClassGroup(int id) {
        this.id = id;
    }
}
