package com.dailyroutines.repository;

import com.dailyroutines.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    
    List<Routine> findByScheduledDate(String scheduledDate);
    
    List<Routine> findByCategory(String category);
    
    List<Routine> findByCompleted(Boolean completed);
    
    List<Routine> findByScheduledDateAndCompleted(String scheduledDate, Boolean completed);
}
