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
public class Section {

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

    @ManyToOne
    @JoinColumn(name = "classGroup_id")
    private ClassGroup classGroup;

//    @JsonIgnore
//    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
//    private Set<EduMaterial> eduMaterials = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable (name = "sections_to_eduMaterials",
//            joinColumns = {@JoinColumn(name = "section_id")},
//            inverseJoinColumns = {@JoinColumn(name = "eduMaterial_id")})
//    private Set<EduMaterial> eduMaterials = new HashSet<>();

    public Section() {
    }

    public Section(int id) {
        this.id = id;
    }

}
