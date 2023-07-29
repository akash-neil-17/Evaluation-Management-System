package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinalScoreModel {
    private String fullName;
    private String batchName;
    private Double dailyTask;
    private Double miniProject;
    private Double midtermProject;
    private Double finalProject;
    private Double aptitudeAndInterview;
    private Double managerEvaluation;
    private Double total;
}
