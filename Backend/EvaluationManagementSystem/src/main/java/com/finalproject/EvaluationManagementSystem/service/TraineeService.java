package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.model.TraineeRequestModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TraineeService {
    ResponseEntity<Object> create(TraineeRequestModel userDTO);

    ResponseEntity<Object> update(TraineeRequestModel updatedTraineeRequestModel);

    ResponseEntity<Object> getTrainee(Long traineeID);

    ResponseEntity<Object> delete(TraineeRequestModel traineeRequestModel);

    ResponseEntity<List<TraineeEntity>> getAll();
}
