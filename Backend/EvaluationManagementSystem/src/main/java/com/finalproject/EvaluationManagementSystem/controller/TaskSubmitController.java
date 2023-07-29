package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.TaskSubmissionEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskSearchModel;
import com.finalproject.EvaluationManagementSystem.model.TaskSubmitModel;
import com.finalproject.EvaluationManagementSystem.service.TaskSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainee/task")
@RequiredArgsConstructor
public class TaskSubmitController {
    private final TaskSubmitService taskSubmitService;
    @PostMapping("/submit")
    public ResponseEntity<Object> submit (@RequestBody TaskSubmitModel taskSubmitModel){
        return taskSubmitService.submit(taskSubmitModel);
    }
    @PostMapping("/view")
    public ResponseEntity<List<TaskSubmissionEntity>> viewSubmission (@RequestBody TaskSearchModel taskSearchModel){
        return taskSubmitService.viewSubmission(taskSearchModel);
    }
    @GetMapping("/view/all")
    public ResponseEntity<List<TaskSubmissionEntity>> viewAll (){
        return taskSubmitService.viewAll();
    }
}
