package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.TaskCreateEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskCreateModel;
import com.finalproject.EvaluationManagementSystem.model.TaskCreateRequestModel;
import com.finalproject.EvaluationManagementSystem.model.TaskSearchModel;
import com.finalproject.EvaluationManagementSystem.service.TaskCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/trainer/task")
@RequiredArgsConstructor
public class TaskCreateController {
    private final TaskCreateService taskCreateService;

    @PostMapping("/create")
    public ResponseEntity<Object> create (@RequestBody TaskCreateRequestModel taskCreateRequestModel){
        return taskCreateService.create(taskCreateRequestModel);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> update (@RequestBody TaskCreateModel updatedTask){
        return taskCreateService.update(updatedTask);
    }
    @GetMapping("/{taskID}")
    public ResponseEntity<Object> getTask (@PathVariable ("taskID") Long taskID){
        return taskCreateService.getTask(taskID);
    }
    @GetMapping
    public ResponseEntity<List<TaskCreateEntity>> taskList(@RequestBody TaskSearchModel taskSearchModel){
        return taskCreateService.taskList(taskSearchModel);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TaskCreateEntity>> allTaskList (){
        return taskCreateService.allTaskList();

    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete (@RequestBody TaskCreateModel taskCreateModel){
        return taskCreateService.delete(taskCreateModel);
    }

}
