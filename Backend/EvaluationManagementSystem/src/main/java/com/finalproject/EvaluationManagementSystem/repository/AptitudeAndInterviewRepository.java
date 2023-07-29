package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.AptitudeAndInterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AptitudeAndInterviewRepository extends JpaRepository<AptitudeAndInterviewEntity, Long> {
   Optional<AptitudeAndInterviewEntity>  findByBatchEntityBatchNameAndTraineeEntityTraineeID(String batchName, Long traineeID);
}
