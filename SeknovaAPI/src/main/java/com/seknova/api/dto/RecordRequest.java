package com.seknova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequest {
    private Integer count;
    private String recordTime;
    private String displayTime;
    private Integer temperature;
    private Integer adc1;
    private Integer battery;
    private Boolean check;
    private Boolean calibrated;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class RecordChangeRequest {
    private Long id;
    private Integer count;
    private String recordTime;
    private String displayTime;
    private Integer temperature;
    private Integer adc1;
    private Integer battery;
    private Boolean check;
    private Boolean calibrated;
}
