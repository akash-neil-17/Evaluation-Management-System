package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.BatchEntity;
import com.finalproject.EvaluationManagementSystem.entity.FinalProjectEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.entity.UserEntity;
import com.finalproject.EvaluationManagementSystem.model.FinalProjectModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeSearchModel;
import com.finalproject.EvaluationManagementSystem.repository.BatchRepository;
import com.finalproject.EvaluationManagementSystem.repository.FinalProjectRepository;
import com.finalproject.EvaluationManagementSystem.repository.TraineeRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.FinalProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinalProjectServiceImplementation implements FinalProjectService {
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final FinalProjectRepository finalProjectRepository;
    private final BatchRepository batchRepository;

    @Override
    public ResponseEntity<FinalProjectEvaluationEntity> upload(FinalProjectModel finalProjectModel) {
        Optional<UserEntity> user = userRepository.findByFullName(finalProjectModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(finalProjectModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                Optional<FinalProjectEvaluationEntity> finalProjectEvaluationEntity = finalProjectRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                if (finalProjectEvaluationEntity.isPresent()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }else {
                    FinalProjectEvaluationEntity finalProjectEvaluation = FinalProjectEvaluationEntity.builder()
                            .traineeEntity(trainee.get())
                            .batchEntity(batch.get())
                            .expectedOutput(finalProjectModel.getExpectedOutput())
                            .requirementUnderstanding(finalProjectModel.getRequirementUnderstanding())
                            .codeQuality(finalProjectModel.getCodeQuality())
                            .liveCoding(finalProjectModel.getLiveCoding())
                            .presentation(finalProjectModel.getPresentation())
                            .srs(finalProjectModel.getSrs())
                            .wbs(finalProjectModel.getWbs())
                            .ppt(finalProjectModel.getPpt())
                            .designDocument(finalProjectModel.getDesignDocument())
                            .total(finalProjectModel.getExpectedOutput()+finalProjectModel.getRequirementUnderstanding()+finalProjectModel.getLiveCoding()+finalProjectModel.getCodeQuality()+finalProjectModel.getPresentation()+finalProjectModel.getSrs()+finalProjectModel.getWbs()+finalProjectModel.getPpt())
                            .build();
                    finalProjectRepository.save(finalProjectEvaluation);
                    return new ResponseEntity<>(finalProjectEvaluation, HttpStatus.CREATED);
                }

            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        }
    }

    @Override
    public ResponseEntity<FinalProjectEvaluationEntity> update(FinalProjectModel finalProjectModel) {
        Optional<UserEntity> user = userRepository.findByFullName(finalProjectModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(finalProjectModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                Optional<FinalProjectEvaluationEntity> finalProjectEvaluationEntity = finalProjectRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                if (finalProjectEvaluationEntity.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else {
                    FinalProjectEvaluationEntity finalProjectEvaluation = finalProjectEvaluationEntity.get();
                    finalProjectEvaluation.setExpectedOutput(finalProjectModel.getExpectedOutput());
                    finalProjectEvaluation.setRequirementUnderstanding(finalProjectModel.getRequirementUnderstanding());
                    finalProjectEvaluation.setCodeQuality(finalProjectModel.getCodeQuality());
                    finalProjectEvaluation.setLiveCoding(finalProjectModel.getLiveCoding());
                    finalProjectEvaluation.setPresentation(finalProjectModel.getPresentation());
                    finalProjectEvaluation.setSrs(finalProjectModel.getSrs());
                    finalProjectEvaluation.setWbs(finalProjectModel.getWbs());
                    finalProjectEvaluation.setPpt(finalProjectModel.getPpt());
                    finalProjectEvaluation.setDesignDocument(finalProjectModel.getDesignDocument());

                    Double total = finalProjectEvaluation.getPpt()+finalProjectEvaluation.getDesignDocument()+finalProjectEvaluation.getSrs()+finalProjectEvaluation.getWbs()+finalProjectEvaluation.getPresentation()+finalProjectEvaluation.getLiveCoding()+ finalProjectEvaluation.getCodeQuality()+finalProjectEvaluation.getRequirementUnderstanding()+finalProjectEvaluation.getExpectedOutput();

                    finalProjectEvaluation.setTotal(total);
                    finalProjectRepository.save(finalProjectEvaluation);
                    return new ResponseEntity<>(finalProjectEvaluation, HttpStatus.ACCEPTED);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<List<FinalProjectEvaluationEntity>> viewAll() {
        List<FinalProjectEvaluationEntity> list = finalProjectRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<FinalProjectEvaluationEntity>> view(TraineeSearchModel traineeSearchModel) {
        Optional<UserEntity> user = userRepository.findByFullName(traineeSearchModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(traineeSearchModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                List<FinalProjectEvaluationEntity> list = finalProjectRepository.findByTraineeEntityTraineeIDAndBatchEntityBatchName(trainee.get().getTraineeID(), batch.get().getBatchName());
                return new ResponseEntity<>(list, HttpStatus.FOUND);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
