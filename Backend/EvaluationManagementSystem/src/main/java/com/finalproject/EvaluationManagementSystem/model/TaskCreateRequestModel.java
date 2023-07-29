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
public class TaskCreateRequestModel {
    private String taskName;
    private String typeName;
    private String batchName;
    private String fullName;
    private Date createDate;
    private Date deadline;
    private File file;
}
