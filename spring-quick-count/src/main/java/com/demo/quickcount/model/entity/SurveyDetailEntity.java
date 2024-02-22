package com.demo.quickcount.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_survey_detail")
public class SurveyDetailEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "province_id", length = 36, nullable = false)
    private String provinceId;

    @Column(name = "city_id", length = 36, nullable = false)
    private String cityId;

    @Column(name = "district_id", length = 36, nullable = false)
    private String districtId;

    @Column(name = "village_id", length = 36, nullable = false)
    private String villageId;

    @Column(name = "tps_no", length = 36, nullable = false)
    private String tpsNo;

    @Column(name = "type_count", length = 36, nullable = false)
    private String typeCount;

    @Column(name = "total_count")
    private Long totalCount;

    @OneToMany(mappedBy = "surveyDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SurveyDataEntity> surveyData = new ArrayList<>();

    public void addSurveyData(SurveyDataEntity surveyDataEntity) {
        this.surveyData.add(surveyDataEntity);
        surveyDataEntity.setSurveyDetail(this);
    }
}
