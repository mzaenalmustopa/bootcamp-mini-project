package com.demo.quickcount.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDataRequest {
    private String id;
    private String surveyDetailId;
    private String surveyId;
    private Long totalSuara;
}
