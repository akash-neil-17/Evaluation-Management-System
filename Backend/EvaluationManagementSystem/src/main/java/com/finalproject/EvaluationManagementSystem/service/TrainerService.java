package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.TrainerEntity;
import com.finalproject.EvaluationManagementSystem.model.TrainerRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainerService {
    ResponseEntity<Object> create(TrainerRequestModel trainerRequestModel);

    ResponseEntity<Object> update(TrainerRequestModel updatedTrainerRequestModel);

    ResponseEntity<Object> getTrainee(Long trainerID);

    ResponseEntity<List<TrainerEntity>> getAll();

    ResponseEntity<Object> delete(TrainerRequestModel trainerRequestModel);
}
