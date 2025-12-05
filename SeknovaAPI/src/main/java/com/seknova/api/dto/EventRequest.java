package com.seknova.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    private String dateTime;
    private String displayTime;
    private String[] eventAttribute;
    private Integer eventId;
    private Integer eventValue;
    private String note;
    private Boolean check;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class EventChangeRequest {
    private Long id;
    private String dateTime;
    private String displayTime;
    private String[] eventAttribute;
    private Integer eventId;
    private Integer eventValue;
    private String note;
    private Boolean check;
}
