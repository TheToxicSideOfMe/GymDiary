package com.stark.GymDiaryAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.stark.GymDiaryAPI.model.Workout;
import com.stark.GymDiaryAPI.service.WorkoutService;


@RestController
@RequestMapping("/api/splits/{splitId}/workouts")
public class workoutController {
    @Autowired
    private WorkoutService workoutService;

    //get all workouts by splitId
    @GetMapping
    public ResponseEntity<List<Workout>> getWorkoutsBySplit(@PathVariable String splitId) {
        List<Workout> workouts = workoutService.findWorkoutsBySplitId(splitId);
        return ResponseEntity.ok(workouts);
    }

    //get a specific workout 
    @GetMapping("/{workoutId}")
    public ResponseEntity<Optional<Workout>> getSingleWorkout(@PathVariable String workoutId){
        return new ResponseEntity<Optional<Workout>>(workoutService.getWorkoutById(workoutId), HttpStatus.OK);
    }

    //create new workout
    @PostMapping
    public ResponseEntity<Workout> createWorkout(@PathVariable String splitId, @RequestBody Workout workout){
        return new ResponseEntity<Workout>(workoutService.createWorkout(splitId,workout), HttpStatus.CREATED);
    }

    //update a specific workout
    @PutMapping("/{workoutId}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable String workoutId, @RequestBody Workout updatedWorkout){
        return new ResponseEntity<Workout>(workoutService.updateWorkout(workoutId, updatedWorkout), HttpStatus.ACCEPTED);
    }

    //delete a specific workout
    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Boolean> deleteWorkout(@PathVariable String workoutId){
        return new ResponseEntity<Boolean>(workoutService.deleteWorkout(workoutId), HttpStatus.ACCEPTED);
    }
}
