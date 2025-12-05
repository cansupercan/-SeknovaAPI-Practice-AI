package com.seknova.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private String dateTime;
    
    private String displayTime;
    
    @JdbcTypeCode(SqlTypes.JSON)
    private String[] eventAttribute;
    
    @Column(nullable = false)
    private Integer eventId;
    
    @Column(nullable = false)
    private Integer eventValue;
    
    private String note;
    
    private Boolean check;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserAuth userAuth;
}
