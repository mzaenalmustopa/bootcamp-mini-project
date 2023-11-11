package com.mznalmstpa.springjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_assessment_option")
public class AssessmentOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "OPTION_NAME" , length = 128)
    private String optionName;

    @Column(name = "OPTION_VALUE")
    private Double optionValue;

    @Column(name = "ASSESSMENT_ID", insertable = false, updatable = false)
    private Long assessmentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ASSESSMENT_ID")
    private AssessmentEntity assessment;

    public AssessmentOptionEntity(String optionName, Double optionValue, AssessmentEntity assessment) {
        this.optionName = optionName;
        this.optionValue = optionValue;
        this.assessment = assessment;
    }
}
