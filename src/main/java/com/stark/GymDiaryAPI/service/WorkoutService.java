package com.stark.GymDiaryAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.stark.GymDiaryAPI.repository.ExerciseRepository;
import com.stark.GymDiaryAPI.repository.SplitRepository;
import com.stark.GymDiaryAPI.repository.WorkoutRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.model.Split;
import com.stark.GymDiaryAPI.model.Workout;

@Service
public class WorkoutService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private SplitRepository splitRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;


    public Optional<Workout> getWorkoutById(String id) {
        return workoutRepository.findById(id);
    }

    public List<Workout>findWorkoutsBySplitId(String splitId) {
        return workoutRepository.findWorkoutsBySplitId(splitId);
        }

    public Workout createWorkout(String splitId, Workout workout) {
        Split split = splitRepository.findById(splitId)
            .orElseThrow(() -> new IllegalArgumentException("Split not found with id: " + splitId));
        
        List<Exercise> completeExercises = workout.getExercises().stream()
            .map(exercise -> exerciseRepository.findById(exercise.getId())
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found ")))
                    .collect(Collectors.toList());

                    
        workout.setExercises(completeExercises);
        workout.setSplit(split);
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(String workoutId, Workout updatedWorkout) {
        Update update= new Update();
        update.set("title", updatedWorkout.getTitle());
        update.set("exercises", updatedWorkout.getExercises());
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(workoutId)), update, Workout.class);
        return updatedWorkout;
    }

    public Boolean deleteWorkout(String workoutId) {
        if (workoutRepository.existsById(workoutId)) {
            workoutRepository.deleteById(workoutId);
            return true;
        } else {
            return false;
        }
    }

}
