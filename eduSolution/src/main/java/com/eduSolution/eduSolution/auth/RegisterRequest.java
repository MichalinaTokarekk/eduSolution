package com.eduSolution.eduSolution.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String streetName;
    private String buildingNumber;
    private String apartmentNumber;
    private String city;
    private String post;
    private String postCode;
    private String country;

    private String yearBook;
    private int classGroup;
    private Set<Integer> teachingClassGroups;

}
