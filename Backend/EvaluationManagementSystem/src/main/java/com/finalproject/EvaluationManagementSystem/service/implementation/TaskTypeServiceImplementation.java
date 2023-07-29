package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.TaskTypeEntity;
import com.finalproject.EvaluationManagementSystem.model.TaskTypeModel;
import com.finalproject.EvaluationManagementSystem.repository.TaskTypeRepository;
import com.finalproject.EvaluationManagementSystem.service.TaskTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskTypeServiceImplementation implements TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;
    @Override
    public ResponseEntity<Object> create(TaskTypeModel taskTypeModel) {
        TaskTypeEntity taskTypeEntity = TaskTypeEntity.builder()
                .typeName(taskTypeModel.getTypeName())
                .build();
        TaskTypeEntity savedTaskTypeEntity = taskTypeRepository.save(taskTypeEntity);
        return new ResponseEntity<>(savedTaskTypeEntity, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> update(TaskTypeModel updatedTaskTypeModel, Long typeID) {
        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findById(typeID);
        if (taskTypeEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TaskTypeEntity existingTaskType = taskTypeEntity.get();
            existingTaskType.setTypeName(updatedTaskTypeModel.getTypeName());
            TaskTypeEntity savedUpdate = taskTypeRepository.save(existingTaskType);
            return new ResponseEntity<>(savedUpdate, HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<Object> delete(TaskTypeModel taskTypeModel) {
        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findByTypeName(taskTypeModel.getTypeName());
        if(taskTypeEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TaskTypeEntity existingTaskType = taskTypeEntity.get();
            taskTypeRepository.delete(existingTaskType);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<List<TaskTypeEntity>> typeList() {
        List<TaskTypeEntity> list = taskTypeRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Object> getTaskTypeByID(Long typeID) {
        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findById(typeID);
        if (taskTypeEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            TaskTypeEntity existingTaskType = taskTypeEntity.get();
            TaskTypeModel taskTypeModel = new TaskTypeModel();
            taskTypeModel.setTypeName(existingTaskType.getTypeName());
            return new ResponseEntity<>(taskTypeModel, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getTaskTypeByName(String typeName) {
        Optional<TaskTypeEntity> taskType = taskTypeRepository.findByTypeName(typeName);
        if (taskType.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TaskTypeEntity taskTypeEntity = taskType.get();
            return new ResponseEntity<>(taskTypeEntity, HttpStatus.FOUND);
        }
    }
}
