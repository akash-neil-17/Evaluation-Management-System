package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AptitudeAndInterviewModel {
    private String fullName;
    private String batchName;
    private Double aptitudeTest;
    private Double interview;
    private Double total;
}
