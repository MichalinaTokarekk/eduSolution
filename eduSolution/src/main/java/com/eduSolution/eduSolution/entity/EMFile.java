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
public class EMFile {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String filePath;

//    @Lob
//    @Column(nullable = false)
//    private byte[] fileData;
    private String mimeType;
    private long fileSize;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedBy
    private String updatedBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable (name = "emFiles_to_eduMaterials",
            joinColumns = {@JoinColumn(name = "emFile_id")},
            inverseJoinColumns = {@JoinColumn(name = "eduMaterial_id")})
    private Set<EduMaterial> eduMaterials = new HashSet<>();

    public EMFile() {
    }

    public EMFile(int id) {
        this.id = id;
    }

    @PreRemove
    private void removeRelations() {
        eduMaterials.clear();
    }
}
