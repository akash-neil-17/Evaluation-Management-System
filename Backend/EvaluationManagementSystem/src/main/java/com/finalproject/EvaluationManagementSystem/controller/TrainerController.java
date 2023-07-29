package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.TrainerEntity;
import com.finalproject.EvaluationManagementSystem.model.TrainerRequestModel;
import com.finalproject.EvaluationManagementSystem.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainer")
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService trainerService;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody TrainerRequestModel trainerRequestModel){
        return trainerService.create(trainerRequestModel);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update (@RequestBody TrainerRequestModel updatedTrainerRequestModel){
        return trainerService.update(updatedTrainerRequestModel);
    }
    @GetMapping("/{trainerID}")
    public ResponseEntity<Object> getTrainee (@PathVariable ("trainerID") Long trainerID){
        return trainerService.getTrainee(trainerID);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TrainerEntity>> trainersList(){
        return trainerService.getAll();
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody TrainerRequestModel trainerRequestModel){
        return trainerService.delete(trainerRequestModel);
    }
}
