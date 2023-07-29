package com.finalproject.EvaluationManagementSystem.model;

import com.finalproject.EvaluationManagementSystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraineeResponseModel {
    private String fullName;
    private Long traineeID;
    private String degreeName;
    private String educationalInstitute;
    private Double cgpa;
    private Year passingYear;
}
