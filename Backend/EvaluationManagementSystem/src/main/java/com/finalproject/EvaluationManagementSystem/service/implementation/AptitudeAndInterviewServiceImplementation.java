package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.AptitudeAndInterviewEntity;
import com.finalproject.EvaluationManagementSystem.entity.BatchEntity;
import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.entity.UserEntity;
import com.finalproject.EvaluationManagementSystem.model.AptitudeAndInterviewModel;
import com.finalproject.EvaluationManagementSystem.repository.AptitudeAndInterviewRepository;
import com.finalproject.EvaluationManagementSystem.repository.BatchRepository;
import com.finalproject.EvaluationManagementSystem.repository.TraineeRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.AptitudeAndInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AptitudeAndInterviewServiceImplementation implements AptitudeAndInterviewService {
    private final AptitudeAndInterviewRepository aptitudeAndInterviewRepository;
    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;
    private final BatchRepository batchRepository;

    @Override
    public ResponseEntity<AptitudeAndInterviewEntity> upload(AptitudeAndInterviewModel aptitudeAndInterviewModel) {
        Optional<UserEntity> user = userRepository.findByFullName(aptitudeAndInterviewModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(aptitudeAndInterviewModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                Optional<AptitudeAndInterviewEntity> aptitudeAndInterviewEntity = aptitudeAndInterviewRepository.findByBatchEntityBatchNameAndTraineeEntityTraineeID(batch.get().getBatchName(), trainee.get().getTraineeID());
                if (aptitudeAndInterviewEntity.isPresent()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else {
                    AptitudeAndInterviewEntity aptitudeAndInterview = AptitudeAndInterviewEntity.builder()
                            .traineeEntity(trainee.get())
                            .batchEntity(batch.get())
                            .interview(aptitudeAndInterviewModel.getInterview())
                            .aptitudeTest(aptitudeAndInterviewModel.getAptitudeTest())
                            .total(aptitudeAndInterviewModel.getInterview()+aptitudeAndInterviewModel.getAptitudeTest())
                            .build();
                    aptitudeAndInterviewRepository.save(aptitudeAndInterview);
                    return new ResponseEntity<>(aptitudeAndInterview, HttpStatus.CREATED);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public ResponseEntity<AptitudeAndInterviewEntity> update(AptitudeAndInterviewModel aptitudeAndInterviewModel) {
        Optional<UserEntity> user = userRepository.findByFullName(aptitudeAndInterviewModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(aptitudeAndInterviewModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                Optional<AptitudeAndInterviewEntity> aptitudeAndInterviewEntity = aptitudeAndInterviewRepository.findByBatchEntityBatchNameAndTraineeEntityTraineeID(batch.get().getBatchName(), trainee.get().getTraineeID());
                if (aptitudeAndInterviewEntity.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else {
                    AptitudeAndInterviewEntity aptitudeAndInterview = aptitudeAndInterviewEntity.get();
                    aptitudeAndInterview.setInterview(aptitudeAndInterviewModel.getInterview());
                    aptitudeAndInterview.setAptitudeTest(aptitudeAndInterviewModel.getAptitudeTest());
                    aptitudeAndInterview.setTotal(aptitudeAndInterview.getInterview()+aptitudeAndInterview.getAptitudeTest());
                    aptitudeAndInterviewRepository.save(aptitudeAndInterview);
                    return new ResponseEntity<>(aptitudeAndInterview, HttpStatus.ACCEPTED);
                }
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<List<AptitudeAndInterviewEntity>> viewAll() {
        List<AptitudeAndInterviewEntity> list = aptitudeAndInterviewRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
}
