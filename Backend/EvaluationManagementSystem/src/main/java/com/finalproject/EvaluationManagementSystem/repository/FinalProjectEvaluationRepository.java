package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.FinalProjectEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinalProjectEvaluationRepository extends JpaRepository<FinalProjectEvaluationEntity,Long> {
  Optional<FinalProjectEvaluationEntity>  findByTraineeEntityTraineeID(Long traineeID);
}
