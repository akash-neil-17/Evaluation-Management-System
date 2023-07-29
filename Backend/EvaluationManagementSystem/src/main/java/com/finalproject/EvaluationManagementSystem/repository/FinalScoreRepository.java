package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.FinalScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalScoreRepository extends JpaRepository<FinalScoreEntity, Long> {
    List<FinalScoreEntity> findByBatchEntityBatchName(String batchName);
}
