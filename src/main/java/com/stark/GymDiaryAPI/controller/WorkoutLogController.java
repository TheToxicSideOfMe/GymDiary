package com.stark.GymDiaryAPI.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.stark.GymDiaryAPI.model.WorkoutLog;
import com.stark.GymDiaryAPI.service.WorkoutLogService;


@RestController
@RequestMapping("/api/workouts/{workoutId}/logs")
public class WorkoutLogController {
    @Autowired
    private WorkoutLogService workoutLogService;

    @PostMapping
    public ResponseEntity<WorkoutLog> createLog(@PathVariable String workoutId, @RequestBody WorkoutLog workoutLog){
        return new ResponseEntity<WorkoutLog>(workoutLogService.createLog(workoutId,workoutLog),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutLog>> getWorkoutLogs(@PathVariable String workoutId){
        return new ResponseEntity<List<WorkoutLog>>(workoutLogService.getWorkoutLogs(workoutId),HttpStatus.OK);
    }

    @GetMapping("/{logId}")
    public ResponseEntity<WorkoutLog> getLogById(@PathVariable String logId){
        return new ResponseEntity <WorkoutLog>(workoutLogService.getLogById(logId),HttpStatus.OK);
    }

    // Update a log
    @PutMapping("/{logId}")
    public ResponseEntity<WorkoutLog> updateLog(@PathVariable String logId, @RequestBody WorkoutLog workoutLog){
        return new ResponseEntity<WorkoutLog>(workoutLogService.updateLog(logId,workoutLog),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Boolean> deleteLog(@PathVariable String logId){
        return new ResponseEntity<Boolean>(workoutLogService.deleteLog(logId),HttpStatus.OK);
    }
}
