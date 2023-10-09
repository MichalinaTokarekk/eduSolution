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

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classGroup")
    private Set<User> users = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "classGroups_to_courses",
            joinColumns = {@JoinColumn(name = "classGroup_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private Set<Course> courses = new HashSet<>();

    @PreRemove
    private void removeRelations() {
        courses.clear();
    }

    public ClassGroup(int id) {
        this.id = id;
    }


//
//    @Transient
//    private List<Integer> userIds;
//
//    @PostLoad
//    private void onLoad() {
//        userIds = users.stream().map(User::getId).collect(Collectors.toList());
//    }
}
