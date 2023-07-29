package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.TaskCreateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskCreateRepository extends JpaRepository<TaskCreateEntity, Long> {
//    List<TaskCreateEntity> findByTypeNameAndBatchName(String typeName, String batchName);

    List<TaskCreateEntity> findByTaskTypeTypeNameAndBatchEntityBatchName(String typeName, String batchName);

    Optional<TaskCreateEntity> findByTaskName(String taskName);
}
