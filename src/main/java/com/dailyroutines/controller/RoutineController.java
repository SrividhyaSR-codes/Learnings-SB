package com.dailyroutines.controller;

import com.dailyroutines.dto.RoutineDTO;
import com.dailyroutines.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
@Tag(name = "Routines", description = "API endpoints for managing daily routines")
public class RoutineController {

    private final RoutineService routineService;

    /**
     * Get all routines
     */
    @GetMapping
    @Operation(summary = "Get all routines", 
               description = "Retrieve a list of all routines in the system")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all routines",
                 content = @Content(mediaType = "application/json", 
                                   schema = @Schema(implementation = RoutineDTO.class)))
    public ResponseEntity<List<RoutineDTO>> getAllRoutines() {
        return ResponseEntity.ok(routineService.getAllRoutines());
    }

    /**
     * Get routine by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get routine by ID", 
               description = "Retrieve a specific routine by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Routine found",
                     content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = RoutineDTO.class))),
        @ApiResponse(responseCode = "404", description = "Routine not found")
    })
    public ResponseEntity<RoutineDTO> getRoutineById(
            @Parameter(description = "The ID of the routine to retrieve")
            @PathVariable Long id) {
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }

    /**
     * Create a new routine
     */
    @PostMapping
    @Operation(summary = "Create a new routine", 
               description = "Create a new routine entry in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Routine created successfully",
                     content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = RoutineDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<RoutineDTO> createRoutine(
            @Parameter(description = "Routine details")
            @RequestBody RoutineDTO routineDTO) {
        RoutineDTO createdRoutine = routineService.createRoutine(routineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    /**
     * Update an existing routine
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing routine", 
               description = "Update the details of an existing routine")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Routine updated successfully",
                     content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = RoutineDTO.class))),
        @ApiResponse(responseCode = "404", description = "Routine not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<RoutineDTO> updateRoutine(
            @Parameter(description = "The ID of the routine to update")
            @PathVariable Long id,
            @Parameter(description = "Updated routine details")
            @RequestBody RoutineDTO routineDTO) {
        return ResponseEntity.ok(routineService.updateRoutine(id, routineDTO));
    }

    /**
     * Delete a routine
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a routine", 
               description = "Delete a routine from the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Routine deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Routine not found")
    })
    public ResponseEntity<Void> deleteRoutine(
            @Parameter(description = "The ID of the routine to delete")
            @PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get routines by scheduled date
     */
    @GetMapping("/by-date/{scheduledDate}")
    @Operation(summary = "Get routines by date", 
               description = "Retrieve all routines scheduled for a specific date")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved routines",
                 content = @Content(mediaType = "application/json",
                                   schema = @Schema(implementation = RoutineDTO.class)))
    public ResponseEntity<List<RoutineDTO>> getRoutinesByDate(
            @Parameter(description = "The scheduled date (format: yyyy-MM-dd)")
            @PathVariable String scheduledDate) {
        return ResponseEntity.ok(routineService.getRoutinesByDate(scheduledDate));
    }

    /**
     * Get routines by category
     */
    @GetMapping("/by-category/{category}")
    @Operation(summary = "Get routines by category", 
               description = "Retrieve all routines in a specific category")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved routines",
                 content = @Content(mediaType = "application/json",
                                   schema = @Schema(implementation = RoutineDTO.class)))
    public ResponseEntity<List<RoutineDTO>> getRoutinesByCategory(
            @Parameter(description = "The category name")
            @PathVariable String category) {
        return ResponseEntity.ok(routineService.getRoutinesByCategory(category));
    }

    /**
     * Get completed routines
     */
    @GetMapping("/completed")
    @Operation(summary = "Get completed routines", 
               description = "Retrieve all routines that have been marked as completed")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved completed routines",
                 content = @Content(mediaType = "application/json",
                                   schema = @Schema(implementation = RoutineDTO.class)))
    public ResponseEntity<List<RoutineDTO>> getCompletedRoutines() {
        return ResponseEntity.ok(routineService.getCompletedRoutines());
    }

    /**
     * Get pending routines
     */
    @GetMapping("/pending")
    @Operation(summary = "Get pending routines", 
               description = "Retrieve all routines that have not been completed yet")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved pending routines",
                 content = @Content(mediaType = "application/json",
                                   schema = @Schema(implementation = RoutineDTO.class)))
    public ResponseEntity<List<RoutineDTO>> getPendingRoutines() {
        return ResponseEntity.ok(routineService.getPendingRoutines());
    }

    /**
     * Get routines by date and completion status
     */
    @GetMapping("/by-date-and-status/{scheduledDate}/{completed}")
    @Operation(summary = "Get routines by date and status", 
               description = "Retrieve routines for a specific date with a specific completion status")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved routines",
                 content = @Content(mediaType = "application/json",
                                   schema = @Schema(implementation = RoutineDTO.class)))
    public ResponseEntity<List<RoutineDTO>> getRoutinesByDateAndStatus(
            @Parameter(description = "The scheduled date (format: yyyy-MM-dd)")
            @PathVariable String scheduledDate,
            @Parameter(description = "Completion status (true/false)")
            @PathVariable Boolean completed) {
        return ResponseEntity.ok(routineService.getRoutinesByDateAndStatus(scheduledDate, completed));
    }

    /**
     * Mark routine as completed
     */
    @PatchMapping("/{id}/mark-completed")
    @Operation(summary = "Mark routine as completed", 
               description = "Mark a specific routine as completed")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Routine marked as completed successfully",
                     content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = RoutineDTO.class))),
        @ApiResponse(responseCode = "404", description = "Routine not found")
    })
    public ResponseEntity<RoutineDTO> markAsCompleted(
            @Parameter(description = "The ID of the routine to mark as completed")
            @PathVariable Long id) {
        return ResponseEntity.ok(routineService.markAsCompleted(id));
    }
}
