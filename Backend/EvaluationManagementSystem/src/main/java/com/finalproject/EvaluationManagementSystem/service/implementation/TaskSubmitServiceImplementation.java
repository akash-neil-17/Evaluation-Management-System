package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.*;
import com.finalproject.EvaluationManagementSystem.model.TaskSearchModel;
import com.finalproject.EvaluationManagementSystem.model.TaskSubmitModel;
import com.finalproject.EvaluationManagementSystem.repository.*;
import com.finalproject.EvaluationManagementSystem.service.TaskSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskSubmitServiceImplementation implements TaskSubmitService {
    private final TaskSubmitRepository taskSubmitRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final TaskCreateRepository taskCreateRepository;
    private final BatchRepository batchRepository;
    @Override
    public ResponseEntity<Object> submit(TaskSubmitModel taskSubmitModel) {
        Optional<TaskCreateEntity> taskCreateEntity = taskCreateRepository.findByTaskName(taskSubmitModel.getTaskName());
        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findByTypeName(taskSubmitModel.getTypeName());
        Optional<BatchEntity> batchEntity = batchRepository.findByBatchName(taskSubmitModel.getBatchName());
        Optional<UserEntity> userEntity = userRepository.findByFullName(taskSubmitModel.getFullName());
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Optional<TraineeEntity> traineeEntity = traineeRepository.findByUserEntityUserID(userEntity.get().getUserID());
            if (taskCreateEntity.isEmpty() || taskTypeEntity.isEmpty() || traineeEntity.isEmpty() || batchEntity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else {
                TaskSubmissionEntity taskSubmissionEntity = TaskSubmissionEntity.builder()
                        .taskType(taskTypeEntity.get())
                        .taskCreation(taskCreateEntity.get())
                        .traineeEntity(traineeEntity.get())
                        .batchEntity(batchEntity.get())
                        .submissionDate(taskSubmitModel.getSubmissionDate())
                        .file(taskSubmitModel.getSubmittedFile())
                        .build();
                taskSubmitRepository.save(taskSubmissionEntity);
                return new ResponseEntity<>(taskSubmissionEntity, HttpStatus.CREATED);
            }
        }
    }

    @Override
    public ResponseEntity<List<TaskSubmissionEntity>> viewSubmission(TaskSearchModel taskSearchModel) {
        Optional<TaskTypeEntity> taskTypeEntity = taskTypeRepository.findByTypeName(taskSearchModel.getTypeName());
        Optional<BatchEntity> batchEntity = batchRepository.findByBatchName(taskSearchModel.getBatchName());
        if (taskTypeEntity.isEmpty() || batchEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            List<TaskSubmissionEntity> list = taskSubmitRepository.findByTaskTypeTypeNameAndBatchEntityBatchName(taskTypeEntity.get().getTypeName(), batchEntity.get().getBatchName());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<List<TaskSubmissionEntity>> viewAll() {
        List<TaskSubmissionEntity> list = taskSubmitRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
}
