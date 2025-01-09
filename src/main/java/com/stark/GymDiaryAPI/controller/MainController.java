package com.stark.GymDiaryAPI.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.model.Split;
import com.stark.GymDiaryAPI.model.Workout;
import com.stark.GymDiaryAPI.model.WorkoutLog;
import com.stark.GymDiaryAPI.repository.ExerciseRepository;
import com.stark.GymDiaryAPI.repository.SplitRepository;
import com.stark.GymDiaryAPI.repository.WorkoutLogRepository;
import com.stark.GymDiaryAPI.repository.WorkoutRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





    @Component
    @RestController
    @RequestMapping("/api")
    public class MainController {

        @Autowired
        private final WorkoutLogRepository workoutLogRepository;
        private final WorkoutRepository workoutRepository;
        private final SplitRepository splitRepository;

        public MainController(WorkoutRepository workoutRepository, SplitRepository splitRepository, WorkoutLogRepository workoutLogRepository) {
            this.workoutRepository = workoutRepository;
            this.splitRepository = splitRepository;
            this.workoutLogRepository = workoutLogRepository;
        }

        @PostMapping("/AddSplit")
        public Split addSplit(@RequestBody Split split) {
            return splitRepository.save(split);
        }

        // Update Split
        @PutMapping("/UpdateSplit/{splitId}")
        public Split updateSplit(@PathVariable int splitId, @RequestBody Split updatedSplit) {
            Split existingSplit = splitRepository.findById(splitId)
                    .orElseThrow(() -> new RuntimeException("Split not found"));
            
            // Update fields (title, exercises, etc.)
            existingSplit = new Split(existingSplit.id(), updatedSplit.name());
            
            return splitRepository.save(existingSplit); // Save updated split
        }

        @DeleteMapping("/DeleteSplit/{splitId}")
        public void deleteSplit(@PathVariable int splitId) {
            // Fetch the split by ID
            Split split = splitRepository.findById(splitId)
                .orElseThrow(() -> new RuntimeException("Split not found"));

            // Delete all workouts related to the split
            workoutRepository.deleteAll(workoutRepository.findBySplitId(splitId));

            // Now delete the split
            splitRepository.delete(split);
        }
        

        @PostMapping("/AddWorkout")
        public Workout addWorkout(@RequestBody Workout workout) {
            return workoutRepository.save(workout);
        }

        @DeleteMapping("/DeleteWorkout/{workoutId}")
        public void deleteWorkout(@PathVariable int workoutId) {
            // Fetch the workout by ID
            Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));

            // Delete the workout
            workoutRepository.delete(workout);
        }


        @PutMapping("/UpdateWorkout/{workoutId}")
        public Workout updateWorkout(@PathVariable int workoutId, @RequestBody Workout updatedWorkout) {
            Workout existingWorkout = workoutRepository.findById(workoutId)
                    .orElseThrow(() -> new RuntimeException("Workout not found"));
            
            // Update fields (title, exerciseIds, etc.)
            existingWorkout = new Workout(existingWorkout.id(), updatedWorkout.splitId(), updatedWorkout.title(), updatedWorkout.exerciseIds());
            
            return workoutRepository.save(existingWorkout); // Save updated workout
        }

        //SECTION 2
        @GetMapping("/MySplits")
        public List<Split> GetAllSplits(){
            return splitRepository.findAll();
        }

        @GetMapping("/MySplits/{splitId}/GetWorkouts")
        public List<Workout> GetAllWorkouts(@PathVariable int splitId){ 
            return workoutRepository.findBySplitId(splitId);
        }

        @GetMapping("/MySplits/{splitId}/GetWorkouts/{workoutId}/GetExercises")
        public List<Integer> GetAllExercises(@PathVariable int workoutId) {
            Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new RuntimeException("Workout not found"));
            return workout.exerciseIds();  // Directly access the exercises from the record
        }



        @PostMapping("/LogWorkout")
        public WorkoutLog logWorkout(@RequestBody WorkoutLog workoutLog) {
            return workoutLogRepository.save(workoutLog);
        }
        
    }
