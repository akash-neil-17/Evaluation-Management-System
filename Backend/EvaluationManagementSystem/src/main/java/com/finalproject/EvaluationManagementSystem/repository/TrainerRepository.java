package com.finalproject.EvaluationManagementSystem.repository;

import com.finalproject.EvaluationManagementSystem.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {
//    Optional <TrainerEntity> findByUserId(Long userID);

    Optional<TrainerEntity> findByUserEntityUserID(Long userID);
}
