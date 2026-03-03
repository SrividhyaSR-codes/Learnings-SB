package com.dailyroutines.service;

import com.dailyroutines.dto.RoutineDTO;
import com.dailyroutines.entity.Routine;
import com.dailyroutines.exception.ResourceNotFoundException;
import com.dailyroutines.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutineService {

    private final RoutineRepository routineRepository;

    /**
     * Get all routines
     */
    public List<RoutineDTO> getAllRoutines() {
        return routineRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get routine by ID
     */
    public RoutineDTO getRoutineById(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routine not found with id: " + id));
        return convertToDTO(routine);
    }

    /**
     * Create a new routine
     */
    public RoutineDTO createRoutine(RoutineDTO routineDTO) {
        Routine routine = convertToEntity(routineDTO);
        routine.setCreatedAt(LocalDateTime.now());
        routine.setUpdatedAt(LocalDateTime.now());
        routine.setCompleted(false);
        Routine savedRoutine = routineRepository.save(routine);
        return convertToDTO(savedRoutine);
    }

    /**
     * Update an existing routine
     */
    public RoutineDTO updateRoutine(Long id, RoutineDTO routineDTO) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routine not found with id: " + id));
        
        if (routineDTO.getTitle() != null) {
            routine.setTitle(routineDTO.getTitle());
        }
        if (routineDTO.getDescription() != null) {
            routine.setDescription(routineDTO.getDescription());
        }
        if (routineDTO.getCategory() != null) {
            routine.setCategory(routineDTO.getCategory());
        }
        if (routineDTO.getTime() != null) {
            routine.setTime(routineDTO.getTime());
        }
        if (routineDTO.getCompleted() != null) {
            routine.setCompleted(routineDTO.getCompleted());
        }
        if (routineDTO.getScheduledDate() != null) {
            routine.setScheduledDate(routineDTO.getScheduledDate());
        }
        
        routine.setUpdatedAt(LocalDateTime.now());
        Routine updatedRoutine = routineRepository.save(routine);
        return convertToDTO(updatedRoutine);
    }

    /**
     * Delete a routine
     */
    public void deleteRoutine(Long id) {
        if (!routineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Routine not found with id: " + id);
        }
        routineRepository.deleteById(id);
    }

    /**
     * Get routines by scheduled date
     */
    public List<RoutineDTO> getRoutinesByDate(String scheduledDate) {
        return routineRepository.findByScheduledDate(scheduledDate)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get routines by category
     */
    public List<RoutineDTO> getRoutinesByCategory(String category) {
        return routineRepository.findByCategory(category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get completed routines
     */
    public List<RoutineDTO> getCompletedRoutines() {
        return routineRepository.findByCompleted(true)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get pending routines
     */
    public List<RoutineDTO> getPendingRoutines() {
        return routineRepository.findByCompleted(false)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get routines by date and completion status
     */
    public List<RoutineDTO> getRoutinesByDateAndStatus(String scheduledDate, Boolean completed) {
        return routineRepository.findByScheduledDateAndCompleted(scheduledDate, completed)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Mark routine as completed
     */
    public RoutineDTO markAsCompleted(Long id) {
        Routine routine = routineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Routine not found with id: " + id));
        routine.setCompleted(true);
        routine.setUpdatedAt(LocalDateTime.now());
        Routine updatedRoutine = routineRepository.save(routine);
        return convertToDTO(updatedRoutine);
    }

    /**
     * Convert Entity to DTO
     */
    private RoutineDTO convertToDTO(Routine routine) {
        return new RoutineDTO(
                routine.getId(),
                routine.getTitle(),
                routine.getDescription(),
                routine.getCategory(),
                routine.getTime(),
                routine.getCompleted(),
                routine.getScheduledDate()
        );
    }

    /**
     * Convert DTO to Entity
     */
    private Routine convertToEntity(RoutineDTO dto) {
        Routine routine = new Routine();
        routine.setTitle(dto.getTitle());
        routine.setDescription(dto.getDescription());
        routine.setCategory(dto.getCategory());
        routine.setTime(dto.getTime());
        routine.setScheduledDate(dto.getScheduledDate());
        routine.setCompleted(dto.getCompleted() != null ? dto.getCompleted() : false);
        return routine;
    }
}
