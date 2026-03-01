package com.dailyroutines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutineDTO {
    
    private Long id;
    private String title;
    private String description;
    private String category;
    private String time;
    private Boolean completed;
    private String scheduledDate;
}
