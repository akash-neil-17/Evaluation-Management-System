package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.TaskEvaluationEntity;
import com.finalproject.EvaluationManagementSystem.entity.TaskSubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskEvaluationRepository extends JpaRepository<TaskEvaluationEntity, Long> {
    List<TaskEvaluationEntity> findByTaskTypeTypeNameAndBatchEntityBatchName(String typeName, String batchName);

    List<TaskEvaluationEntity> findByBatchEntityBatchNameAndTraineeEntityTraineeID(String batchName, Long traineeID);

    List<TaskEvaluationEntity> findByTaskTypeTypeNameAndTraineeEntityTraineeID(String typeName, Long traineeID);

    List<TaskEvaluationEntity> findByTaskTypeTypeNameAndTraineeEntityTraineeIDAndBatchEntityBatchName(String typeName, Long traineeID, String batchName);

    List<TaskEvaluationEntity> findByBatchEntityBatchName(String batchName);

    List<TaskEvaluationEntity> findByTaskTypeTypeName(String typeName);

    List<TaskEvaluationEntity> findByTraineeEntityTraineeID(Long traineeID);

    Optional<TaskEvaluationEntity> findByTaskSubmissionSubmissionID(Long submissionID);
}
