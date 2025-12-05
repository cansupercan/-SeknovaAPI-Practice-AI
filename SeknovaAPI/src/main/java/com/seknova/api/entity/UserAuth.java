package com.seknova.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "user_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String country;
    
    @Column(nullable = false)
    private Boolean isFirst = true;
    
    @OneToOne(mappedBy = "userAuth", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserInformation userInformation;
    
    @OneToMany(mappedBy = "userAuth", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;
    
    @OneToMany(mappedBy = "userAuth", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Record> records;
}
