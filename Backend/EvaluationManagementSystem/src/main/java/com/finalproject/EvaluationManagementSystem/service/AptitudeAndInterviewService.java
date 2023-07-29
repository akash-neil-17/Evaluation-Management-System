package com.finalproject.EvaluationManagementSystem.service;

import com.finalproject.EvaluationManagementSystem.entity.AptitudeAndInterviewEntity;
import com.finalproject.EvaluationManagementSystem.model.AptitudeAndInterviewModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AptitudeAndInterviewService {
    ResponseEntity<AptitudeAndInterviewEntity> upload(AptitudeAndInterviewModel aptitudeAndInterviewModel);

    ResponseEntity<AptitudeAndInterviewEntity> update(AptitudeAndInterviewModel aptitudeAndInterviewModel);

    ResponseEntity<List<AptitudeAndInterviewEntity>> viewAll();
}
