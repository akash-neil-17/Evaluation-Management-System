package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerResponseModel {
    private String fullName;
    private Long trainerID;
    private String designation;
    private Integer experience;
    private String expertise;
    private Date joiningDate;
}
