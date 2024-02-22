package com.demo.quickcount.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequest {
    private String id;
    private String name;
    private Long totalSurvey;
    private Double percentage;
}
