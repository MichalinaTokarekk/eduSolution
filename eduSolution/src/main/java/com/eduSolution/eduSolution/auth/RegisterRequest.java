package com.eduSolution.eduSolution.auth;

import lombok.*;

import java.util.Set;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNr;
    private String dateOfBirth;
    private long albumNr;
    private Set<Integer> classGroups;

}
