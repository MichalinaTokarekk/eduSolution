package com.eduSolution.eduSolution.entity;

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
public class EduMaterial {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = -1)
    private String description;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

//    @ManyToOne
//    @JoinColumn(name = "section_id")
//    private Section section;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "eduMaterials_to_sections",
            joinColumns = {@JoinColumn(name = "eduMaterial_id")},
            inverseJoinColumns = {@JoinColumn(name = "section_id")})
    private Set<Section> sections = new HashSet<>();

    public EduMaterial() {
    }

    public EduMaterial(int id) {
        this.id = id;
    }

    @PreRemove
    private void removeRelations() {
        sections.clear();
    }
}
