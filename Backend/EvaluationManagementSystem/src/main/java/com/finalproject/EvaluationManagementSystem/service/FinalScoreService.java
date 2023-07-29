package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.FinalScoreEntity;
import com.finalproject.EvaluationManagementSystem.model.FinalScoreModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FinalScoreService {
    ResponseEntity<FinalScoreEntity> generate(FinalScoreModel finalScoreModel);

    ResponseEntity<List<FinalScoreEntity>> viewAll();

    ResponseEntity<List<FinalScoreEntity>> viewByBatch(FinalScoreModel finalScoreModel);
}
