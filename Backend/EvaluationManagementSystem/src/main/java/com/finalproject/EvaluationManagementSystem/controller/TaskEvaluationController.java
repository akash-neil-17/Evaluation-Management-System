package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.TaskEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskEvaluationModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeSearchModel;
import com.finalproject.EvaluationManagementSystem.service.TaskEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainer/task/evaluate")
@RequiredArgsConstructor
public class TaskEvaluationController {
    private final TaskEvaluationService taskEvaluationService;

    @PostMapping("/upload")
    public ResponseEntity<TaskEvaluationEntity> upload (@RequestBody TaskEvaluationModel evaluationModel){
        return taskEvaluationService.upload(evaluationModel);
    }
    @PutMapping("/update")
    public ResponseEntity<TaskEvaluationEntity> update (@RequestBody TaskEvaluationModel evaluationModel){
        return taskEvaluationService.update(evaluationModel);
    }
    @GetMapping("/viewAll")
    public ResponseEntity<List<TaskEvaluationEntity>> viewAll (){
        return taskEvaluationService.viewAll();
    }
    @PostMapping("/view")
    public ResponseEntity<List<TaskEvaluationEntity>> view (@RequestBody TraineeSearchModel traineeSearchModel){
        return taskEvaluationService.view(traineeSearchModel);
    }
}
