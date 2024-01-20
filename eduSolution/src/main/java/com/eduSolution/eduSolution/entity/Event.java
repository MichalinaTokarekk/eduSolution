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
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Event {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name="CONTENT", length=1000)
    private String description;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startEventTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endEventTime;
    @Column
    private String eventDate;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Event(int id) {
        this.id = id;
    }

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable (name = "sections_to_eduMaterials",
//            joinColumns = {@JoinColumn(name = "section_id")},
//            inverseJoinColumns = {@JoinColumn(name = "eduMaterial_id")})
//    private Set<EduMaterial> eduMaterials = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "event_classgroup", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "classgroup_id"))
    private Set<ClassGroup> classGroups = new HashSet<>();
}
