package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.BatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatchRepository extends JpaRepository<BatchEntity, Long> {
    Optional<BatchEntity> findByBatchName(String batchName);
}
