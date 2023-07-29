package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerRequestModel{
    private String fullName;
    private String designation;
    private Date joiningDate;
    private Integer experience;
    private String expertise;
    private UserRequestModel userRequestModel;
}
