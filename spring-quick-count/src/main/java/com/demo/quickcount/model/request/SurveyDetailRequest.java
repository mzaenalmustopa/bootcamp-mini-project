package com.demo.quickcount.model.request;

import com.demo.quickcount.model.response.SurveyDataResponse;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDetailRequest {
    private String id;
    private String provinceId;
    private String cityId;
    private String districtId;
    private String villageId;
    private String tpsNo;
    private String typeCount;
    private Long totalCount;
    private List<SurveyDataRequest> surveyData = new ArrayList<>();
}
