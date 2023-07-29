package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.FinalProjectEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.entity.TaskEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.model.FinalProjectModel;
import com.finalproject.EvaluationManagementSystem.model.TaskEvaluationModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeSearchModel;
import com.finalproject.EvaluationManagementSystem.service.FinalProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainer/final/evaluate")
@RequiredArgsConstructor
public class FinalProjectEvaluationController {
    private final FinalProjectService finalProjectService;
    @PostMapping("/upload")
    public ResponseEntity<FinalProjectEvaluationEntity> upload (@RequestBody FinalProjectModel finalProjectModel){
        return finalProjectService.upload(finalProjectModel);
    }
    @PutMapping("/update")
    public ResponseEntity<FinalProjectEvaluationEntity> update (@RequestBody FinalProjectModel finalProjectModel){
        return finalProjectService.update(finalProjectModel);
    }
    @GetMapping("/viewAll")
    public ResponseEntity<List<FinalProjectEvaluationEntity>> viewAll (){
        return finalProjectService.viewAll();
    }
    @PostMapping("/view")
    public ResponseEntity<List<FinalProjectEvaluationEntity>> view (@RequestBody TraineeSearchModel traineeSearchModel){
        return finalProjectService.view(traineeSearchModel);
    }
}
