package com.demo.quickcount.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDetailResponse {
    private String id;
    private String provinceId;
    private String cityId;
    private String districtId;
    private String villageId;
    private String tpsNo;
    private String typeCount;
    private Long totalCount;
}
