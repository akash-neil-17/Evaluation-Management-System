package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinalProjectModel {
    private String fullName;
    private String batchName;
    private Double requirementUnderstanding;
    private Double expectedOutput;
    private Double codeQuality;
    private Double presentation;
    private Double liveCoding;
    private Double srs;
    private Double wbs;
    private Double ppt;
    private Double designDocument;
    private Double total;
}
