package com.finalproject.EvaluationManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "final_score")
public class FinalScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_score_id")
    private Long finalScoreID;
    @OneToOne
    @JoinColumn(name = "trainee_id")
    private TraineeEntity traineeEntity;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    @OneToOne
    @JoinColumn(name = "final_project_id")
    private FinalProjectEvaluationEntity finalProjectEvaluationEntity;
    @OneToOne
    @JoinColumn(name = "manager_evaluation_id")
    private ManagerEvaluationEntity managerEvaluationEntity;
    @OneToOne
    @JoinColumn(name = "aptitude_interview_id")
    private AptitudeAndInterviewEntity aptitudeAndInterviewEntity;
    private Double total;
}
