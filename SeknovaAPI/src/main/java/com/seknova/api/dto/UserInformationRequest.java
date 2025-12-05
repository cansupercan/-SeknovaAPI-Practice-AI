package com.seknova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationRequest {
    private String firstName;
    private String lastName;
    private String birthDay;
    private String phone;
    private String address;
    private String gender;
    private Integer height;
    private Integer weight;
    private String race;
    private String liquor;
    private Boolean smoke;
    private Boolean check;
    private Boolean phoneVerified;
}
