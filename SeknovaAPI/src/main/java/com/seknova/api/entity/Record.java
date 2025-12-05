package com.seknova.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    private Integer count;
    
    private String recordTime;
    
    private String displayTime;
    
    private Integer temperature;
    
    private Integer adc1;
    
    private Integer battery;
    
    private Boolean check;
    
    private Boolean calibrated;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserAuth userAuth;
}
