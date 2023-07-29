package com.finalproject.EvaluationManagementSystem.controller;

import com.finalproject.EvaluationManagementSystem.entity.TaskTypeEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskTypeModel;
import com.finalproject.EvaluationManagementSystem.service.TaskTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ems/task-type")
@RequiredArgsConstructor
public class TaskTypeController {
    private final TaskTypeService taskTypeService;
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody TaskTypeModel taskTypeModel){
        return taskTypeService.create(taskTypeModel);
    }
    @PutMapping("/update/{typeID}")
    public ResponseEntity<Object> update(@RequestBody TaskTypeModel updatedTaskTypeModel, @PathVariable("typeID") Long typeID){
        return taskTypeService.update(updatedTaskTypeModel, typeID);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody TaskTypeModel taskTypeModel){
        return taskTypeService.delete(taskTypeModel);
    }
    @GetMapping("/all")
    public ResponseEntity<List<TaskTypeEntity>> getAll(){
        return taskTypeService.typeList();
    }
    @GetMapping("/{typeID}")
    public ResponseEntity<Object> getTaskType(@PathVariable("typeID") Long typeID){
        return taskTypeService.getTaskTypeByID(typeID);
    }
    @GetMapping("/{typeName}")
    public ResponseEntity<Object> getTaskTypeByName (@PathVariable ("typeName") String typeName){
        return taskTypeService.getTaskTypeByName(typeName);
    }
}
