package com.demo.quickcount.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    private String id;
    private String name;
    private Long totalSurvey;
    private Double percentage;
}
