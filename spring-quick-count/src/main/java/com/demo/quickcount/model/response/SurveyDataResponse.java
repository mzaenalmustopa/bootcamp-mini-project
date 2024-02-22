package com.demo.quickcount.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDataResponse {
    private String id;
    private String surveyDetailId;
    private String surveyId;
    private Long totalSuara;
}
