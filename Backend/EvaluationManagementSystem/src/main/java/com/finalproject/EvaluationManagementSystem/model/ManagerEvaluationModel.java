package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerEvaluationModel {
    private String fullName;
    private String batchName;
    private Double bjitTools;
    private Double officeRules;
    private Double sincerityAndHardWork;
    private Double qualityMindset;
    private Double attendance;
    private Double communicationSkill;
    private Double englishSkill;
    private Double total;
}
