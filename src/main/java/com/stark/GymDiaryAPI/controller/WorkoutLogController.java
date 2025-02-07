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
public class WorkoutLogController {
    @Autowired
    private WorkoutLogService workoutLogService;

    @PostMapping("/api/workouts/{workoutId}/logs")
    public ResponseEntity<WorkoutLog> createLog(@PathVariable String workoutId, @RequestBody WorkoutLog workoutLog){
        return new ResponseEntity<WorkoutLog>(workoutLogService.createLog(workoutId,workoutLog),HttpStatus.CREATED);
    }
    @GetMapping("/api/logs")
    public ResponseEntity<List<WorkoutLog>> getAllLogs(){
        return new ResponseEntity<List<WorkoutLog>>(workoutLogService.getAllLogs(),HttpStatus.OK);
    }

    
}
