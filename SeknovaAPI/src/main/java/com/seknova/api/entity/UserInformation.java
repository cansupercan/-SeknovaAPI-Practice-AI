package com.seknova.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_information")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
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
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserAuth userAuth;
}
