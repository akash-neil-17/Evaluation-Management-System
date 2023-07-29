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
@Table(name = "task_list")
public class TaskCreationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskID;
    private String taskName;
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainerEntity;
    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TaskTypeEntity taskType;
    private Date createDate;
    private Date deadline;
    private File file;
}
