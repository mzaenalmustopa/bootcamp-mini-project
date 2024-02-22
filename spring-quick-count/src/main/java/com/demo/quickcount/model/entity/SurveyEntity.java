package com.demo.quickcount.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_survey")
public class SurveyEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "total_survey", nullable = false)
    private Long totalSurvey;

    @Column(name = "percentage", nullable = false)
    private Double percentage;
}
