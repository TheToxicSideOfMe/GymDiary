package com.stark.GymDiaryAPI.model;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Document(collection = "workoutLogs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutLog {

    @Id
    private String id;

    @DocumentReference
    private Workout workout;
    private List<ExerciseLog> exerciseLogs;
    private LocalDate date = LocalDate.now();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExerciseLog {
        
        @DocumentReference
        private Exercise exercise;
        private List<Set> sets;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Set {
        private int setNumber;
        private int reps;
        private int weight;
    }
}
