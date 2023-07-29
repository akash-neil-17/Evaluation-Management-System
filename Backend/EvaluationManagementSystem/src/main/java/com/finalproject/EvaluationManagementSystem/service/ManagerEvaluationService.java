package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.ManagerEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.model.ManagerEvaluationModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ManagerEvaluationService {
    ResponseEntity<ManagerEvaluationEntity> upload(ManagerEvaluationModel managerEvaluationModel);

    ResponseEntity<ManagerEvaluationEntity> update(ManagerEvaluationModel managerEvaluationModel);

    ResponseEntity<List<ManagerEvaluationEntity>> viewAll();
}
