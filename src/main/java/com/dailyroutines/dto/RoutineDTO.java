package com.dailyroutines.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object for Routine")
public class RoutineDTO {
    
    @Schema(description = "Unique identifier of the routine", example = "1")
    private Long id;
    
    @Schema(description = "Title of the routine", example = "Morning Jog")
    private String title;
    
    @Schema(description = "Detailed description of the routine", example = "A 30-minute jog around the park")
    private String description;
    
    @Schema(description = "Category of the routine", example = "Exercise")
    private String category;
    
    @Schema(description = "Time of the routine (HH:mm format)", example = "06:00")
    private String time;
    
    @Schema(description = "Whether the routine is completed", example = "false")
    private Boolean completed;
    
    @Schema(description = "Scheduled date for the routine (yyyy-MM-dd format)", example = "2026-03-03")
    private String scheduledDate;
}
