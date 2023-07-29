package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.*;
import com.finalproject.EvaluationManagementSystem.model.ManagerEvaluationModel;
import com.finalproject.EvaluationManagementSystem.repository.BatchRepository;
import com.finalproject.EvaluationManagementSystem.repository.ManagerEvaluationRepository;
import com.finalproject.EvaluationManagementSystem.repository.TraineeRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.ManagerEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerEvaluationServiceImplementation implements ManagerEvaluationService {
    private final ManagerEvaluationRepository managerEvaluationRepository;
    private final UserRepository userRepository;
    private final BatchRepository batchRepository;
    private final TraineeRepository traineeRepository;
    @Override
    public ResponseEntity<ManagerEvaluationEntity> upload(ManagerEvaluationModel managerEvaluationModel) {
        Optional<UserEntity> user = userRepository.findByFullName(managerEvaluationModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(managerEvaluationModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                Optional<ManagerEvaluationEntity> managerEvaluationEntity = managerEvaluationRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                if (managerEvaluationEntity.isPresent()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else{
                    ManagerEvaluationEntity managerEvaluation = ManagerEvaluationEntity.builder()
                            .batchEntity(batch.get())
                            .traineeEntity(trainee.get())
                            .bjitTools(managerEvaluationModel.getBjitTools())
                            .officeRules(managerEvaluationModel.getOfficeRules())
                            .qualityMindset(managerEvaluationModel.getQualityMindset())
                            .sincerityAndHardwork(managerEvaluationModel.getSincerityAndHardWork())
                            .attendance(managerEvaluationModel.getAttendance())
                            .communicationSkill(managerEvaluationModel.getCommunicationSkill())
                            .englishSkill(managerEvaluationModel.getEnglishSkill())
                            .total(((managerEvaluationModel.getBjitTools()+managerEvaluationModel.getOfficeRules()+managerEvaluationModel.getQualityMindset()+managerEvaluationModel.getSincerityAndHardWork()+managerEvaluationModel.getAttendance()+managerEvaluationModel.getCommunicationSkill()+managerEvaluationModel.getEnglishSkill())/85.0)*100)
                            .build();
                    managerEvaluationRepository.save(managerEvaluation);
                    return new ResponseEntity<>(managerEvaluation, HttpStatus.CREATED);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<ManagerEvaluationEntity> update(ManagerEvaluationModel managerEvaluationModel) {
        Optional<UserEntity> user = userRepository.findByFullName(managerEvaluationModel.getFullName());
        Optional<BatchEntity> batch = batchRepository.findByBatchName(managerEvaluationModel.getBatchName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            if (trainee.isPresent() && batch.isPresent()){
                Optional<ManagerEvaluationEntity> managerEvaluationEntity = managerEvaluationRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                if (managerEvaluationEntity.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                else {
                    ManagerEvaluationEntity managerEvaluation = managerEvaluationEntity.get();
                    managerEvaluation.setBjitTools(managerEvaluationModel.getBjitTools());
                    managerEvaluation.setOfficeRules(managerEvaluationModel.getOfficeRules());
                    managerEvaluation.setQualityMindset(managerEvaluationModel.getQualityMindset());
                    managerEvaluation.setAttendance(managerEvaluationModel.getAttendance());
                    managerEvaluation.setSincerityAndHardwork(managerEvaluationModel.getSincerityAndHardWork());
                    managerEvaluation.setCommunicationSkill(managerEvaluationModel.getCommunicationSkill());
                    managerEvaluation.setEnglishSkill(managerEvaluationModel.getEnglishSkill());

                    Double total = ((managerEvaluation.getBjitTools()+managerEvaluation.getOfficeRules()+managerEvaluation.getQualityMindset()+managerEvaluation.getAttendance()+managerEvaluation.getSincerityAndHardwork()+managerEvaluation.getCommunicationSkill()+managerEvaluation.getEnglishSkill())/85.0)*100;

                    managerEvaluation.setTotal(total);
                    managerEvaluationRepository.save(managerEvaluation);
                    return new ResponseEntity<>(managerEvaluation, HttpStatus.ACCEPTED);
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Override
    public ResponseEntity<List<ManagerEvaluationEntity>> viewAll() {
        List<ManagerEvaluationEntity> list = managerEvaluationRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
}
