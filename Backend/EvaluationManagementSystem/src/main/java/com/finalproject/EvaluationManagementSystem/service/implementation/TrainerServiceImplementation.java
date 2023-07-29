package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.TrainerEntity;
import com.finalproject.EvaluationManagementSystem.entity.UserEntity;
import com.finalproject.EvaluationManagementSystem.model.TrainerRequestModel;
import com.finalproject.EvaluationManagementSystem.model.TrainerResponseModel;
import com.finalproject.EvaluationManagementSystem.repository.TrainerRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerServiceImplementation implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainerResponseModel trainerResponseModel;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<Object> create(TrainerRequestModel trainerRequestModel) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(TrainerRequestModel updatedTrainerRequestModel) {
        Optional<UserEntity> userEntity = userRepository.findByFullName(updatedTrainerRequestModel.getFullName());
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Optional<TrainerEntity> trainerEntity = trainerRepository.findByUserEntityUserID(userEntity.get().getUserID());
            if (trainerEntity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                TrainerEntity trainer = trainerEntity.get();
                trainer.setDesignation(updatedTrainerRequestModel.getDesignation());
                trainer.setExperience(updatedTrainerRequestModel.getExperience());
                trainer.setExpertise(updatedTrainerRequestModel.getExpertise());
                trainer.setJoiningDate(updatedTrainerRequestModel.getJoiningDate());
                trainerRepository.save(trainer);

                trainerResponseModel.setTrainerID(trainer.getTrainerID());
                trainerResponseModel.setFullName(updatedTrainerRequestModel.getFullName());
                trainerResponseModel.setDesignation(trainer.getDesignation());
                trainerResponseModel.setExperience(trainer.getExperience());
                trainerResponseModel.setExpertise(trainer.getExpertise());
                trainerResponseModel.setJoiningDate(trainer.getJoiningDate());

                return new ResponseEntity<>(trainerResponseModel, HttpStatus.CREATED);
            }
        }
    }

    @Override
    public ResponseEntity<Object> getTrainee(Long trainerID) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(trainerID);
        if (trainerEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TrainerEntity trainer = trainerEntity.get();
            return new ResponseEntity<>(trainer, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<List<TrainerEntity>> getAll() {
        List<TrainerEntity> list = trainerRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Object> delete(TrainerRequestModel trainerRequestModel) {
        return null;
    }
}
