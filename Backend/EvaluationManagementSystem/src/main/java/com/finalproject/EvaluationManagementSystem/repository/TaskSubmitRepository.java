package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.TaskSubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskSubmitRepository extends JpaRepository<TaskSubmissionEntity, Long> {
    List<TaskSubmissionEntity> findByTaskTypeTypeNameAndBatchEntityBatchName(String typeName, String batchName);
}
