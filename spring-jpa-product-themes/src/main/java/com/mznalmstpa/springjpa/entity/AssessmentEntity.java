package com.mznalmstpa.springjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_assessment")
public class AssessmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE", length = 20, unique = true)
    private String code;

    @Column(name = "NAME", length = 1028)
    private String name;

    @Column(name = "PARENT_ID", insertable = false, updatable = false)
    private Long parentId;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<AssessmentEntity> child = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private AssessmentEntity parent;

    @OneToMany(mappedBy = "assessment", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssessmentOptionEntity> options = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    public AssessmentEntity(String code, String name, CategoryEntity category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }

    public AssessmentEntity(String code, String name, AssessmentEntity parent, CategoryEntity category) {
        this.code = code;
        this.name = name;
        this.parent = parent;
        this.category = category;
    }

    public void addOption(AssessmentOptionEntity option){
        this.options.add(option);
        option.setAssessment(this);
    }
}
