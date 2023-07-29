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
public class TaskCreateModel {
    private Long batchID;
    private Long typeID;
    private Long trainerID;
    private String taskName;
    private Date createDate;
    private Date deadline;
    private File file;
}
