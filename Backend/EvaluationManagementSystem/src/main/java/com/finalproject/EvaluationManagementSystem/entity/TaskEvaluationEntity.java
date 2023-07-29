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
@Table(name = "task_evaluation")
public class TaskEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Long evaluationID;
    @OneToOne
    @JoinColumn(name = "task_id")
    private TaskCreateEntity taskCreation;
    @OneToOne
    @JoinColumn(name = "submission_id")
    private TaskSubmissionEntity taskSubmission;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TaskTypeEntity taskType;
    @ManyToOne
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
    private Double total;
}
