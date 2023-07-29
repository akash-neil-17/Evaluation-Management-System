package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.AptitudeAndInterviewEntity;
import com.finalproject.EvaluationManagementSystem.model.AptitudeAndInterviewModel;
import com.finalproject.EvaluationManagementSystem.service.AptitudeAndInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainer/aptitude")
@RequiredArgsConstructor
public class AptitudeAndInterviewController {
    private final AptitudeAndInterviewService aptitudeAndInterviewService;
    @PostMapping("/upload")
    public ResponseEntity<AptitudeAndInterviewEntity> upload (@RequestBody AptitudeAndInterviewModel aptitudeAndInterviewModel){
        return aptitudeAndInterviewService.upload(aptitudeAndInterviewModel);
    }
    @PutMapping("/update")
    public ResponseEntity<AptitudeAndInterviewEntity> update(@RequestBody AptitudeAndInterviewModel aptitudeAndInterviewModel){
        return aptitudeAndInterviewService.update(aptitudeAndInterviewModel);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AptitudeAndInterviewEntity>> view (){
        return aptitudeAndInterviewService.viewAll();
    }
}
