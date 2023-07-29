package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEvaluationModel {
    private String taskName;
    private Long submissionID;
    private String typeName;
    private String fullName;
    private String batchName;
    private Double requirementUnderstanding;
    private Double expectedOutput;
    private Double codeQuality;
    private Double presentation;
    private Double liveCoding;
    private Double total;
}
