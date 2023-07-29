package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.model.AssignBatchModel;
import com.finalproject.EvaluationManagementSystem.model.BatchModel;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<Object> assignTrainee(AssignBatchModel assignBatchModel);
}
