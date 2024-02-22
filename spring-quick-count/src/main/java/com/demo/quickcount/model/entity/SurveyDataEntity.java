package com.demo.quickcount.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_survey_data")
public class SurveyDataEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "survey_detail_id", length = 36)
    private String surveyDetailId;

    @ManyToOne
    @JoinColumn(name = "survey_detail_id", insertable = false, updatable = false)
    private SurveyDetailEntity surveyDetail;

    @Column(name = "survey_id", length = 36)
    private String surveyId;

    @ManyToOne
    @JoinColumn(name = "survey_id", insertable = false, updatable = false)
    private SurveyEntity survey;

    @Column(name = "total_suara", nullable = false)
    private Long totalSuara;
}
