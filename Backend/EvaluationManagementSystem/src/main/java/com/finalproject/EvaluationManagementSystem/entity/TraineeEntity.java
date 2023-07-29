package com.finalproject.EvaluationManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trainees")
public class TraineeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainee_id")
    private Long traineeID;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @OneToOne
    @JoinColumn(name = "batch_id")
    private BatchEntity batchEntity;
    private String gender;
    private Date dob;
    private String degreeName;
    private String educationalInstitute;
    private Double cgpa;
    private Year passingYear;
}
