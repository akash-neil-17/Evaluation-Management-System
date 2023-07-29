package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.BatchEntity;
import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.entity.UserEntity;
import com.finalproject.EvaluationManagementSystem.model.AssignBatchModel;
import com.finalproject.EvaluationManagementSystem.model.BatchModel;
import com.finalproject.EvaluationManagementSystem.repository.AdminRepository;
import com.finalproject.EvaluationManagementSystem.repository.BatchRepository;
import com.finalproject.EvaluationManagementSystem.repository.TraineeRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;
    private final BatchRepository batchRepository;
    @Override
    public ResponseEntity<Object> assignTrainee(AssignBatchModel assignBatchModel) {
        Optional<BatchEntity> batchEntity = batchRepository.findByBatchName(assignBatchModel.getBatchName());
        Optional<UserEntity> userEntity = userRepository.findByFullName(assignBatchModel.getFullName());
        if (batchEntity.isEmpty() || userEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Optional<TraineeEntity> traineeEntity = traineeRepository.findByUserEntityUserID(userEntity.get().getUserID());
            if (traineeEntity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else {
                TraineeEntity trainee = traineeEntity.get();
                trainee.setBatchEntity(batchEntity.get());
                traineeRepository.save(trainee);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }
    }
}
