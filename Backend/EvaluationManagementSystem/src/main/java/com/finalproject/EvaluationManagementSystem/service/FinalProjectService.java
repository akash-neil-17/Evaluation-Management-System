package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.FinalProjectEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.model.FinalProjectModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeSearchModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FinalProjectService {
    ResponseEntity<FinalProjectEvaluationEntity> upload(FinalProjectModel finalProjectModel);

    ResponseEntity<FinalProjectEvaluationEntity> update(FinalProjectModel finalProjectModel);

    ResponseEntity<List<FinalProjectEvaluationEntity>> viewAll();

    ResponseEntity<List<FinalProjectEvaluationEntity>> view(TraineeSearchModel traineeSearchModel);
}
