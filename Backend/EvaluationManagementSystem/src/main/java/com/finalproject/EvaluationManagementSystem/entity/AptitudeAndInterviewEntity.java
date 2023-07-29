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
@Table(name = "aptitude_interview_score")
public class AptitudeAndInterviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aptitude_interview_id")
    private Long aptitudeInterviewID;
    @OneToOne
    @JoinColumn(name = "trainee_id")
    private TraineeEntity traineeEntity;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    private Double aptitudeTest;
    private Double interview;
    private Double total;
}
