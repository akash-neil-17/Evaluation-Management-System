package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.TaskSubmissionEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskSearchModel;
import com.finalproject.EvaluationManagementSystem.model.TaskSubmitModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskSubmitService {
    ResponseEntity<Object> submit(TaskSubmitModel taskSubmitModel);

    ResponseEntity<List<TaskSubmissionEntity>> viewSubmission(TaskSearchModel taskSearchModel);

    ResponseEntity<List<TaskSubmissionEntity>> viewAll();
}
