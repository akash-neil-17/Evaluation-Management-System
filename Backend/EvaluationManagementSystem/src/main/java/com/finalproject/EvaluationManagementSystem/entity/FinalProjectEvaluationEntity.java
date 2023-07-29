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
@Table(name = "final_project_evaluation")
public class FinalProjectEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_evaluation_id")
    private Long fpEvaluationID;
    @OneToOne
    @JoinColumn(name = "trainee_id")
    private TraineeEntity traineeEntity;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    private Double requirementUnderstanding;
    private Double expectedOutput;
    private Double codeQuality;
    private Double presentation;
    private Double liveCoding;
    private Double srs;
    private Double wbs;
    private Double ppt;
    private Double designDocument;
    private Double total;
}
