package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.FinalScoreEntity;
import com.finalproject.EvaluationManagementSystem.model.FinalScoreModel;
import com.finalproject.EvaluationManagementSystem.service.FinalScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/admin/finalScore")
@RequiredArgsConstructor
public class FinalScoreController {
    private final FinalScoreService finalScoreService;
    @PostMapping("/generate")
    public ResponseEntity<FinalScoreEntity> generate (@RequestBody FinalScoreModel finalScoreModel){
        return finalScoreService.generate(finalScoreModel);
    }
    @GetMapping("/all")
    public ResponseEntity<List<FinalScoreEntity>> view (){
        return finalScoreService.viewAll();
    }
    @PostMapping("/view")
    public ResponseEntity<List<FinalScoreEntity>> viewByBatch(@RequestBody FinalScoreModel finalScoreModel){
        return finalScoreService.viewByBatch(finalScoreModel);
    }
}
