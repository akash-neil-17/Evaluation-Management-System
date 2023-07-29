package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.ManagerEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerEvaluationRepository extends JpaRepository<ManagerEvaluationEntity, Long> {
   Optional<ManagerEvaluationEntity>  findByTraineeEntityTraineeID(Long traineeID);
}
