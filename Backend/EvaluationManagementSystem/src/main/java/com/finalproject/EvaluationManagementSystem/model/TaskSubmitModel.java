package com.finalproject.EvaluationManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskSubmitModel {
    private String taskName;
    private String typeName;
    private String fullName;
    private String batchName;
    private Date submissionDate;
    private File submittedFile;
}
