package com.finalproject.EvaluationManagementSystem.model;

import com.finalproject.EvaluationManagementSystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestModel {
    private String fullName;
    private String image;
    private String email;
    private String password;
    private String contactNumber;
    private String presentAddress;
    private Role role;
}
