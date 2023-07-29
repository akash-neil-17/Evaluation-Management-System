package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.model.TraineeRequestModel;
import com.finalproject.EvaluationManagementSystem.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainee")
@RequiredArgsConstructor
public class TraineeController {
    private final TraineeService traineeService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody TraineeRequestModel traineeRequestModel){
        return traineeService.create(traineeRequestModel);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update (@RequestBody TraineeRequestModel updatedTraineeRequestModel){
        return traineeService.update(updatedTraineeRequestModel);
    }
    @GetMapping("/{traineeID}")
    public ResponseEntity<Object> getTrainee (@PathVariable ("traineeID") Long traineeID){
        return traineeService.getTrainee(traineeID);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TraineeEntity>> traineesList(){
        return traineeService.getAll();
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody TraineeRequestModel traineeRequestModel){
        return traineeService.delete(traineeRequestModel);
    }
}
