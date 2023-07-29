package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraineeRequestModel{
    private String fullName;
    private String gender;
    private Date dob;
    private String degreeName;
    private String educationalInstitute;
    private Double cgpa;
    private Year passingYear;
    private Long userID;
}
