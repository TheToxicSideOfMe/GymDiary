package com.stark.GymDiaryAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Exercise(
    @Id int id,
    String name,
    ExerciseType type,
    String description,
    MuscleGroup muscleGroup 
) {
    public enum ExerciseType {
        COMPOUND,
        ISOLATION
    }
}
