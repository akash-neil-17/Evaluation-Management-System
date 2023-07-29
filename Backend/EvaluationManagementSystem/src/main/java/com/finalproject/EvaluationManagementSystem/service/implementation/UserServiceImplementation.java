package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.config.JwtService;
import com.finalproject.EvaluationManagementSystem.entity.AdminEntity;
import com.finalproject.EvaluationManagementSystem.entity.TraineeEntity;
import com.finalproject.EvaluationManagementSystem.entity.TrainerEntity;
import com.finalproject.EvaluationManagementSystem.entity.UserEntity;
import com.finalproject.EvaluationManagementSystem.model.UserRequestModel;
import com.finalproject.EvaluationManagementSystem.model.UserResponseModel;
import com.finalproject.EvaluationManagementSystem.repository.AdminRepository;
import com.finalproject.EvaluationManagementSystem.repository.TraineeRepository;
import com.finalproject.EvaluationManagementSystem.repository.TrainerRepository;
import com.finalproject.EvaluationManagementSystem.repository.UserRepository;
import com.finalproject.EvaluationManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserResponseModel userResponseModel;

    private static final String traineeEndPoint = "http://localhost:8081/ems/trainee";
    private static final String trainerEndPoint = "http://localhost:8081/ems/trainer";
    private static final String adminEndPoint = "http://localhost:8081/ems/admin";
    @Override
    @Transactional
    public ResponseEntity<Object> register(UserRequestModel userRequestModel) {
        UserEntity userEntity = UserEntity.builder()
                .fullName(userRequestModel.getFullName())
                .image(userRequestModel.getImage())
                .contactNumber(userRequestModel.getContactNumber())
                .presentAddress(userRequestModel.getPresentAddress())
                .email(userRequestModel.getEmail())
                .password(passwordEncoder.encode(userRequestModel.getPassword()))
                .role(userRequestModel.getRole())
                .build();
        userRepository.save(userEntity);

        userResponseModel.setFullName(userEntity.getFullName());
        userResponseModel.setEmail(userEntity.getEmail());
        userResponseModel.setContactNumber(userEntity.getContactNumber());
        userResponseModel.setPresentAddress(userEntity.getPresentAddress());
        userResponseModel.setImage(userEntity.getImage());
        userResponseModel.setRole(userEntity.getRole());

        String role = userResponseModel.getRole().name();
        if(role.equalsIgnoreCase("TRAINEE")){
            TraineeEntity trainee = TraineeEntity.builder()
                    .userEntity(userEntity)
                    .build();
            traineeRepository.save(trainee);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        } else if (role.equalsIgnoreCase("TRAINER")){
            TrainerEntity trainer = TrainerEntity.builder()
                    .userEntity(userEntity)
                    .build();
            trainerRepository.save(trainer);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        }
        else {
            AdminEntity admin = AdminEntity.builder()
                    .userEntity(userEntity)
                    .build();
            adminRepository.save(admin);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        }
    }
}
