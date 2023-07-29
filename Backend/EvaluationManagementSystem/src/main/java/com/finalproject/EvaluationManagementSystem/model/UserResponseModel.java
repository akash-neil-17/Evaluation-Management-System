package com.finalproject.EvaluationManagementSystem.model;

import com.finalproject.EvaluationManagementSystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseModel {
    private String fullName;
    private String image;
    private String email;
    private String contactNumber;
    private String presentAddress;
    private Role role;
}
