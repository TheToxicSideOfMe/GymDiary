package com.stark.GymDiaryAPI.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.model.Exercise.ExerciseType;

public interface ExerciseRepository extends MongoRepository<Exercise,String> {
    List<Exercise> findByMuscleGroup(String muscleGroup);
    List<Exercise> findByType(ExerciseType type);
    List<Exercise> findByNameContainingIgnoreCase(String query);
    List<Exercise> findByMuscleGroupAndType(String muscleGroup, ExerciseType type);

}
