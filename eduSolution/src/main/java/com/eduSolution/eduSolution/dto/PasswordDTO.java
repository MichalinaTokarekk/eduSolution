package com.eduSolution.eduSolution.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class PasswordDTO {
    int id;
    String oldPassword;
    String newPassword;
    String newPasswordConfirm;
}
