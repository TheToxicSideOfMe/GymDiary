package com.stark.GymDiaryAPI.repository;




import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stark.GymDiaryAPI.model.WorkoutLog;

@Repository
public interface WorkoutLogRepository extends MongoRepository<WorkoutLog, String> {
    List<WorkoutLog> findWorkoutLogsByWorkoutId(String WorkoutId);
}
