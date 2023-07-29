package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.*;
import com.finalproject.EvaluationManagementSystem.model.TaskCreateModel;
import com.finalproject.EvaluationManagementSystem.model.TaskCreateRequestModel;
import com.finalproject.EvaluationManagementSystem.model.TaskSearchModel;
import com.finalproject.EvaluationManagementSystem.repository.*;
import com.finalproject.EvaluationManagementSystem.service.TaskCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskCreateServiceImplementation implements TaskCreateService {
    private final TaskCreateRepository taskCreateRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final BatchRepository batchRepository;
    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<Object> create(TaskCreateRequestModel taskCreateRequestModel) {

        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findByTypeName(taskCreateRequestModel.getTypeName());
        Optional<BatchEntity> batchEntity = batchRepository.findByBatchName(taskCreateRequestModel.getBatchName());
        Optional<UserEntity> userEntity = userRepository.findByFullName(taskCreateRequestModel.getFullName());

        if (taskTypeEntity.isPresent() && batchEntity.isPresent() && userEntity.isPresent()){
            TaskTypeEntity taskType = taskTypeEntity.get();
            BatchEntity batch = batchEntity.get();
            UserEntity user = userEntity.get();
            Optional<TrainerEntity> trainerEntity = trainerRepository.findByUserEntityUserID(user.getUserID());

            if (trainerEntity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//Trainer's name does not exist
            }
            else {
                TrainerEntity trainer = trainerEntity.get();
                TaskCreateEntity taskCreate = TaskCreateEntity.builder()
                        .batchEntity(batch)
                        .taskType(taskType)
                        .trainerEntity(trainer)
                        .taskName(taskCreateRequestModel.getTaskName())
                        .createDate(taskCreateRequestModel.getCreateDate())
                        .deadline(taskCreateRequestModel.getDeadline())
                        .file(taskCreateRequestModel.getFile())
                        .build();
                taskCreateRepository.save(taskCreate);
                return new ResponseEntity<>(taskCreate, HttpStatus.CREATED);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Object> update(TaskCreateModel updatedTask) {
        return null;
    }

    @Override
    public ResponseEntity<Object> getTask(Long taskID) {
        Optional<TaskCreateEntity> taskCreateEntity = taskCreateRepository.findById(taskID);
        if (taskCreateEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TaskCreateEntity taskCreate = taskCreateEntity.get();
            return new ResponseEntity<>(taskCreate, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TaskCreateEntity>> taskList(TaskSearchModel taskSearchModel) {
        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findByTypeName(taskSearchModel.getTypeName());
        Optional<BatchEntity> batchEntity = batchRepository.findByBatchName(taskSearchModel.getBatchName());
        if (taskTypeEntity.isPresent() && batchEntity.isPresent()){
            List<TaskCreateEntity> list = taskCreateRepository.findByTaskTypeTypeNameAndBatchEntityBatchName(taskTypeEntity.get().getTypeName(), batchEntity.get().getBatchName());
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> delete(TaskCreateModel taskCreateModel) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaskCreateEntity>> allTaskList() {
        List<TaskCreateEntity> list = taskCreateRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
}
