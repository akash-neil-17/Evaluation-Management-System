package com.finalproject.EvaluationManagementSystem.service.implementation;

import com.finalproject.EvaluationManagementSystem.entity.*;
import com.finalproject.EvaluationManagementSystem.model.FinalScoreModel;
import com.finalproject.EvaluationManagementSystem.repository.*;
import com.finalproject.EvaluationManagementSystem.service.FinalScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinalScoreServiceImplementation implements FinalScoreService {
    private final FinalScoreRepository finalScoreRepository;
    private final UserRepository userRepository;
    private final BatchRepository batchRepository;
    private final TaskEvaluationRepository taskEvaluationRepository;
    private final FinalProjectEvaluationRepository finalProjectEvaluationRepository;
    private final ManagerEvaluationRepository managerEvaluationRepository;
    private final AptitudeAndInterviewRepository aptitudeAndInterviewRepository;
    private final TraineeRepository traineeRepository;
    @Override
    public ResponseEntity<FinalScoreEntity> generate(FinalScoreModel finalScoreModel) {
        Optional<UserEntity> user = userRepository.findByFullName(finalScoreModel.getFullName());
        if (user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            Optional<TraineeEntity> trainee = traineeRepository.findByUserEntityUserID(user.get().getUserID());
            Optional<BatchEntity> batch = batchRepository.findByBatchName(finalScoreModel.getBatchName());
            if (trainee.isEmpty() || batch.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else{

                double dailyTotal =0.0, miniTotal =0.0, midTotal =0.0, finalTotal, managerTotal,aptitudeTotal;

                List<TaskEvaluationEntity> dailyEvaluationList = taskEvaluationRepository.findByTaskTypeTypeNameAndTraineeEntityTraineeIDAndBatchEntityBatchName("Daily Task", trainee.get().getTraineeID(), batch.get().getBatchName());
                List<TaskEvaluationEntity> miniEvaluationList = taskEvaluationRepository.findByTaskTypeTypeNameAndTraineeEntityTraineeIDAndBatchEntityBatchName("Mini Project", trainee.get().getTraineeID(), batch.get().getBatchName());
                List<TaskEvaluationEntity> midEvaluationList = taskEvaluationRepository.findByTaskTypeTypeNameAndTraineeEntityTraineeIDAndBatchEntityBatchName("Midterm Project", trainee.get().getTraineeID(), batch.get().getBatchName());

                Optional<FinalProjectEvaluationEntity> finalProjectEvaluation= finalProjectEvaluationRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                Optional<ManagerEvaluationEntity> managerEvaluation =managerEvaluationRepository.findByTraineeEntityTraineeID(trainee.get().getTraineeID());
                Optional<AptitudeAndInterviewEntity> aptitudeAndInterview = aptitudeAndInterviewRepository.findByBatchEntityBatchNameAndTraineeEntityTraineeID(batch.get().getBatchName(), trainee.get().getTraineeID());

                for (TaskEvaluationEntity dailyEvaluation:dailyEvaluationList){
                    Double temp = dailyEvaluation.getTotal();
                    if (temp!=null){
                        dailyTotal+=temp;
                    }
                }
                for (TaskEvaluationEntity miniEvaluation:miniEvaluationList){
                    Double temp = miniEvaluation.getTotal();
                    if (temp!=null){
                        miniTotal+=temp;
                    }
                }
                for (TaskEvaluationEntity midEvaluation:midEvaluationList){
                    Double temp = midEvaluation.getTotal();
                    if (temp!=null){
                        midTotal+=temp;
                    }
                }
                if (finalProjectEvaluation.isEmpty() || managerEvaluation.isEmpty() || aptitudeAndInterview.isEmpty()){
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                else {
                    finalTotal= finalProjectEvaluation.get().getTotal();
                    managerTotal = managerEvaluation.get().getTotal();
                    aptitudeTotal = aptitudeAndInterview.get().getTotal();
                }

                //Calculation
                Double finalDaily = ((finalScoreModel.getDailyTask())/100.0)*dailyTotal;
                Double finalMini = ((finalScoreModel.getMiniProject())/100.0)*miniTotal;
                Double finalMid = ((finalScoreModel.getMidtermProject())/100.0)*midTotal;
                Double finalFinalProject = ((finalScoreModel.getFinalProject())/100.0)*finalTotal;
                Double finalManager = ((finalScoreModel.getManagerEvaluation())/100.0)*managerTotal;
                Double finalAptitude = ((finalScoreModel.getAptitudeAndInterview())/100.0)*aptitudeTotal;

                FinalScoreEntity finalScore = FinalScoreEntity.builder()
                        .traineeEntity(trainee.get())
                        .batchEntity(batch.get())
                        .managerEvaluationEntity(managerEvaluation.get())
                        .finalProjectEvaluationEntity(finalProjectEvaluation.get())
                        .aptitudeAndInterviewEntity(aptitudeAndInterview.get())
                        .total(finalDaily+finalMini+finalMid+finalFinalProject+finalManager+finalAptitude)
                        .build();
                finalScoreRepository.save(finalScore);
                return new ResponseEntity<>(finalScore, HttpStatus.CREATED);


            }

        }
    }

    @Override
    public ResponseEntity<List<FinalScoreEntity>> viewAll() {
        List<FinalScoreEntity> list = finalScoreRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<List<FinalScoreEntity>> viewByBatch(FinalScoreModel finalScoreModel) {
        Optional<BatchEntity> batch = batchRepository.findByBatchName(finalScoreModel.getBatchName());
        if (batch.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            List<FinalScoreEntity> list = finalScoreRepository.findByBatchEntityBatchName(batch.get().getBatchName());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }
}
