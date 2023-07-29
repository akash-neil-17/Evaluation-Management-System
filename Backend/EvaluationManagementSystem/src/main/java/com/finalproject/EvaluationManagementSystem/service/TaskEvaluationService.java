package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.TaskEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskEvaluationModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeSearchModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskEvaluationService {
    ResponseEntity<TaskEvaluationEntity> upload(TaskEvaluationModel evaluationModel);

    ResponseEntity<TaskEvaluationEntity> update(TaskEvaluationModel evaluationModel);

    ResponseEntity<List<TaskEvaluationEntity>> viewAll();

    ResponseEntity<List<TaskEvaluationEntity>> view(TraineeSearchModel traineeSearchModel);
}
