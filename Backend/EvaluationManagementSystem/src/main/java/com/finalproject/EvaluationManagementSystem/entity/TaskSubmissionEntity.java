package com.finalproject.EvaluationManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "submitted_tasks")
public class TaskSubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long submissionID;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private TaskCreateEntity taskCreation;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TaskTypeEntity taskType;
    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private TraineeEntity traineeEntity;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    private Date submissionDate;
    private File file;
}
