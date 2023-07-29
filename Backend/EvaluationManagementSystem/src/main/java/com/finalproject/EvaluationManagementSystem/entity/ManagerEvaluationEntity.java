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
@Table(name = "manager_evaluation")
public class ManagerEvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_evaluation_id")
    private Long managerEvaluationID;
    @OneToOne
    @JoinColumn(name = "trainee_id")
    private TraineeEntity traineeEntity;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    private Double bjitTools;
    private Double officeRules;
    private Double sincerityAndHardwork;
    private Double qualityMindset;
    private Double attendance;
    private Double communicationSkill;
    private Double englishSkill;
    private Double total;
}
