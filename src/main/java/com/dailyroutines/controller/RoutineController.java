package com.dailyroutines.controller;

import com.dailyroutines.dto.RoutineDTO;
import com.dailyroutines.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    /**
     * Get all routines
     */
    @GetMapping
    public ResponseEntity<List<RoutineDTO>> getAllRoutines() {
        return ResponseEntity.ok(routineService.getAllRoutines());
    }

    /**
     * Get routine by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoutineDTO> getRoutineById(@PathVariable Long id) {
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }

    /**
     * Create a new routine
     */
    @PostMapping
    public ResponseEntity<RoutineDTO> createRoutine(@RequestBody RoutineDTO routineDTO) {
        RoutineDTO createdRoutine = routineService.createRoutine(routineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    /**
     * Update an existing routine
     */
    @PutMapping("/{id}")
    public ResponseEntity<RoutineDTO> updateRoutine(@PathVariable Long id, @RequestBody RoutineDTO routineDTO) {
        return ResponseEntity.ok(routineService.updateRoutine(id, routineDTO));
    }

    /**
     * Delete a routine
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get routines by scheduled date
     */
    @GetMapping("/by-date/{scheduledDate}")
    public ResponseEntity<List<RoutineDTO>> getRoutinesByDate(@PathVariable String scheduledDate) {
        return ResponseEntity.ok(routineService.getRoutinesByDate(scheduledDate));
    }

    /**
     * Get routines by category
     */
    @GetMapping("/by-category/{category}")
    public ResponseEntity<List<RoutineDTO>> getRoutinesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(routineService.getRoutinesByCategory(category));
    }

    /**
     * Get completed routines
     */
    @GetMapping("/completed")
    public ResponseEntity<List<RoutineDTO>> getCompletedRoutines() {
        return ResponseEntity.ok(routineService.getCompletedRoutines());
    }

    /**
     * Get pending routines
     */
    @GetMapping("/pending")
    public ResponseEntity<List<RoutineDTO>> getPendingRoutines() {
        return ResponseEntity.ok(routineService.getPendingRoutines());
    }

    /**
     * Get routines by date and completion status
     */
    @GetMapping("/by-date-and-status/{scheduledDate}/{completed}")
    public ResponseEntity<List<RoutineDTO>> getRoutinesByDateAndStatus(
            @PathVariable String scheduledDate,
            @PathVariable Boolean completed) {
        return ResponseEntity.ok(routineService.getRoutinesByDateAndStatus(scheduledDate, completed));
    }

    /**
     * Mark routine as completed
     */
    @PatchMapping("/{id}/mark-completed")
    public ResponseEntity<RoutineDTO> markAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(routineService.markAsCompleted(id));
    }
}
