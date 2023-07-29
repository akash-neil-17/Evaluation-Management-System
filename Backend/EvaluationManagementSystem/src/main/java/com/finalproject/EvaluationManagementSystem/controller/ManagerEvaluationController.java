package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.ManagerEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.model.ManagerEvaluationModel;
import com.finalproject.EvaluationManagementSystem.service.ManagerEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainer/managerEvaluation")
@RequiredArgsConstructor
public class ManagerEvaluationController {
    private final ManagerEvaluationService managerEvaluationService;
    @PostMapping("/upload")
    public ResponseEntity<ManagerEvaluationEntity> upload (@RequestBody ManagerEvaluationModel managerEvaluationModel){
        return managerEvaluationService.upload(managerEvaluationModel);
    }
    @PutMapping("/update")
    public ResponseEntity<ManagerEvaluationEntity> update(@RequestBody ManagerEvaluationModel managerEvaluationModel){
        return managerEvaluationService.update(managerEvaluationModel);
    }
    @GetMapping("/viewAll")
    public ResponseEntity<List<ManagerEvaluationEntity>> viewAll(){
        return managerEvaluationService.viewAll();
    }
}
