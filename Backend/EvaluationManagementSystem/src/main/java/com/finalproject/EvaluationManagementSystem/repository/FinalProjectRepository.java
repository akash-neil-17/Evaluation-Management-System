package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.FinalProjectEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinalProjectRepository extends JpaRepository<FinalProjectEvaluationEntity, Long> {
   Optional<FinalProjectEvaluationEntity> findByTraineeEntityTraineeID(Long traineeID);

   List<FinalProjectEvaluationEntity> findByTraineeEntityTraineeIDAndBatchEntityBatchName(Long traineeID, String batchName);
}
