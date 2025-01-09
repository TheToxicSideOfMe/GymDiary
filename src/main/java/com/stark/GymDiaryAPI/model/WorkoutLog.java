package com.stark.GymDiaryAPI.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record WorkoutLog(
    @Id
    int id,
    int workoutId,
    List<ExerciseLog> exerciseLogs,
    LocalDate date
) {
    public record ExerciseLog(
        int exerciseId,
        List<Set> sets
    ) {}

    public record Set(
        int setNumber,
        int reps,
        int weight
    ) {}
}
