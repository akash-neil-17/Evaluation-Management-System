package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.*;
import com.finalproject.EvaluationManagementSystem.model.TaskEvaluationModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeSearchModel;
import com.finalproject.EvaluationManagementSystem.repository.*;
import com.finalproject.EvaluationManagementSystem.service.TaskEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskEvaluationServiceImplementation implements TaskEvaluationService {
    private final TaskEvaluationRepository taskEvaluationRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final BatchRepository batchRepository;
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final TaskCreateRepository taskCreateRepository;
    private final TaskSubmitRepository taskSubmitRepository;
    @Override
    public ResponseEntity<TaskEvaluationEntity> upload(TaskEvaluationModel evaluationModel) {
        Optional<TaskSubmissionEntity> taskSubmission = taskSubmitRepository.findById(evaluationModel.getSubmissionID());
        Optional<TaskCreateEntity> taskCreate = taskCreateRepository.findByTaskName(evaluationModel.getTaskName());
        Optional<TaskTypeEntity> taskType = taskTypeRepository.findByTypeName(evaluationModel.getTypeName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(evaluationModel.getBatchName());
        Optional<UserEntity> user = userRepository.findByFullName(evaluationModel.getFullName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else{
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (taskSubmission.isEmpty() || taskCreate.isEmpty() || taskType.isEmpty() || batch.isEmpty() || trainee.isEmpty()){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else{
                Optional<TaskEvaluationEntity> taskEvaluationEntity = taskEvaluationRepository.findByTaskSubmissionSubmissionID(taskSubmission.get().getSubmissionID());
                if (taskEvaluationEntity.isPresent()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else{
                    TaskEvaluationEntity taskEvaluation = TaskEvaluationEntity.builder()
                            .taskCreation(taskCreate.get())
                            .batchEntity(batch.get())
                            .traineeEntity(trainee.get())
                            .taskType(taskType.get())
                            .taskSubmission(taskSubmission.get())
                            .expectedOutput(evaluationModel.getExpectedOutput())
                            .requirementUnderstanding(evaluationModel.getRequirementUnderstanding())
                            .codeQuality(evaluationModel.getCodeQuality())
                            .presentation(evaluationModel.getPresentation())
                            .liveCoding(evaluationModel.getLiveCoding())
                            .total(evaluationModel.getExpectedOutput()+evaluationModel.getRequirementUnderstanding()+evaluationModel.getPresentation()+evaluationModel.getLiveCoding())
                            .build();
                    taskEvaluationRepository.save(taskEvaluation);
                    return new ResponseEntity<>(taskEvaluation, HttpStatus.CREATED);
                }

            }
        }
    }

    @Override
    public ResponseEntity<TaskEvaluationEntity> update(TaskEvaluationModel evaluationModel) {
        Optional<TaskEvaluationEntity> taskEvaluationEntity = taskEvaluationRepository.findByTaskSubmissionSubmissionID(evaluationModel.getSubmissionID());
        if (taskEvaluationEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TaskEvaluationEntity taskEvaluation = taskEvaluationEntity.get();
            taskEvaluation.setExpectedOutput(evaluationModel.getExpectedOutput());
            taskEvaluation.setRequirementUnderstanding(evaluationModel.getRequirementUnderstanding());
            taskEvaluation.setCodeQuality(evaluationModel.getCodeQuality());
            taskEvaluation.setLiveCoding(evaluationModel.getLiveCoding());
            taskEvaluation.setPresentation(evaluationModel.getPresentation());
            Double total = taskEvaluation.getLiveCoding()+ taskEvaluation.getExpectedOutput()+ taskEvaluation.getCodeQuality()+ taskEvaluation.getPresentation()+ taskEvaluation.getRequirementUnderstanding();
            taskEvaluation.setTotal(total);
            taskEvaluationRepository.save(taskEvaluation);
            return new ResponseEntity<>(taskEvaluation, HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<List<TaskEvaluationEntity>> viewAll() {
        List<TaskEvaluationEntity> list = taskEvaluationRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<TaskEvaluationEntity>> view(TraineeSearchModel traineeSearchModel) {
        Optional<TaskTypeEntity> taskType = taskTypeRepository.findByTypeName(traineeSearchModel.getTypeName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(traineeSearchModel.getBatchName());
        Optional<UserEntity> user = userRepository.findByFullName(traineeSearchModel.getFullName());

        if (user.isPresent() && batch.isPresent() && taskType.isPresent()){
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent()){
                List<TaskEvaluationEntity> list = taskEvaluationRepository.findByTaskTypeTypeNameAndTraineeEntityTraineeIDAndBatchEntityBatchName(taskType.get().getTypeName(), trainee.get().getTraineeID(), batch.get().getBatchName());
                return new ResponseEntity<>(list, HttpStatus.FOUND);
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        else if (user.isEmpty() && batch.isEmpty() && taskType.isEmpty()) {
            List<TaskEvaluationEntity> list = taskEvaluationRepository.findAll();
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }
        else {
            if (user.isEmpty()){
                if (taskType.isPresent() && batch.isPresent()) {
                    List<TaskEvaluationEntity> list = taskEvaluationRepository.findByTaskTypeTypeNameAndBatchEntityBatchName(taskType.get().getTypeName(), batch.get().getBatchName());
                    return new ResponseEntity<>(list, HttpStatus.FOUND);
                } else if (taskType.isEmpty()) {
                    List<TaskEvaluationEntity> list = taskEvaluationRepository.findByBatchEntityBatchName(batch.get().getBatchName());
                    return new ResponseEntity<>(list, HttpStatus.FOUND);
                } else {
                    List<TaskEvaluationEntity> list = taskEvaluationRepository.findByTaskTypeTypeName(taskType.get().getTypeName());
                    return new ResponseEntity<>(list, HttpStatus.FOUND);
                }
            }
            else {
                Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
                if (trainee.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else{
                    if (taskType.isEmpty() && batch.isEmpty()) {
                        List<TaskEvaluationEntity> list = taskEvaluationRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                        return new ResponseEntity<>(list, HttpStatus.FOUND);
                    }else if (taskType.isEmpty()){
                        List<TaskEvaluationEntity> list = taskEvaluationRepository.findByBatchEntityBatchNameAndTraineeEntityTraineeID(batch.get().getBatchName(), trainee.get().getTraineeID());
                        return new ResponseEntity<>(list, HttpStatus.FOUND);
                    }else {
                        List<TaskEvaluationEntity> list = taskEvaluationRepository.findByTaskTypeTypeNameAndTraineeEntityTraineeID(taskType.get().getTypeName(), trainee.get().getTraineeID());
                        return new ResponseEntity<>(list, HttpStatus.FOUND);
                    }
                }

            }
        }
    }
}
