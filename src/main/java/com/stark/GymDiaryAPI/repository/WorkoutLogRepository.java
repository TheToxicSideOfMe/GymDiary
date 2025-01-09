package com.stark.GymDiaryAPI.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stark.GymDiaryAPI.model.WorkoutLog;

public interface WorkoutLogRepository extends MongoRepository<WorkoutLog, Integer> {
    Optional<WorkoutLog> findByWorkoutId(int workoutId);
}
