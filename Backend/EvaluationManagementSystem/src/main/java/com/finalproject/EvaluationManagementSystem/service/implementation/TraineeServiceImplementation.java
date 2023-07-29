package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.entity.UserEntity;
import com.finalproject.EvaluationManagementSystem.model.TraineeRequestModel;
import com.finalproject.EvaluationManagementSystem.model.TraineeResponseModel;
import com.finalproject.EvaluationManagementSystem.repository.TraineeRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TraineeServiceImplementation implements TraineeService {
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final TraineeResponseModel traineeResponseModel;
    @Override
    public ResponseEntity<Object> create(TraineeRequestModel traineeRequestModel) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(TraineeRequestModel updatedTraineeRequestModel) {
        Optional<UserEntity> userEntity = userRepository.findByFullName(updatedTraineeRequestModel.getFullName());
        if (userEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Optional<TraineeEntity> traineeEntity = traineeRepository.findByUserEntityUserID(userEntity.get().getUserID());
            if (traineeEntity.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{
                TraineeEntity trainee = traineeEntity.get();
                trainee.setCgpa(updatedTraineeRequestModel.getCgpa());
                trainee.setDob(updatedTraineeRequestModel.getDob());
                trainee.setGender(updatedTraineeRequestModel.getGender());
                trainee.setDegreeName(updatedTraineeRequestModel.getDegreeName());
                trainee.setEducationalInstitute(updatedTraineeRequestModel.getEducationalInstitute());
                trainee.setPassingYear(updatedTraineeRequestModel.getPassingYear());
                traineeRepository.save(trainee);

                traineeResponseModel.setTraineeID(trainee.getTraineeID());
                traineeResponseModel.setFullName(updatedTraineeRequestModel.getFullName());
                traineeResponseModel.setCgpa(trainee.getCgpa());
                traineeResponseModel.setEducationalInstitute(trainee.getEducationalInstitute());
                traineeResponseModel.setDegreeName(trainee.getDegreeName());
                traineeResponseModel.setPassingYear(trainee.getPassingYear());

                return new ResponseEntity<>(traineeResponseModel, HttpStatus.CREATED);
            }
        }
    }

    @Override
    public ResponseEntity<Object> getTrainee(Long traineeID) {
        Optional<TraineeEntity> traineeEntity = traineeRepository.findById(traineeID);
        if (traineeEntity.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            TraineeEntity trainee = traineeEntity.get();
            return new ResponseEntity<>(trainee, HttpStatus.FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> delete(TraineeRequestModel traineeRequestModel) {
        return null;
    }

    @Override
    public ResponseEntity<List<TraineeEntity>> getAll() {
        List<TraineeEntity> list = traineeRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
}
