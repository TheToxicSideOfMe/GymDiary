package com.stark.GymDiaryAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.model.Exercise.ExerciseType;
import com.stark.GymDiaryAPI.service.ExerciseService;

@RestController
@RequestMapping("/api/exercises")
public class exerciseController {   
    @Autowired
    private ExerciseService exerciseService;


    //get All exercises in the database
    @GetMapping
    public ResponseEntity<List<Exercise>>getAllExercises(){
        return new ResponseEntity<List<Exercise>>(exerciseService.getAllExercises(),HttpStatus.OK);
    }

    //get exercises by muscleGroup
    @GetMapping("/muscleGroup/{muscleGroup}")
    public ResponseEntity<List<Exercise>>getExercisseByMuscleGroup(@PathVariable String muscleGroup){
        return new ResponseEntity<List<Exercise>>(exerciseService.getExercisesByMuscleGroup(muscleGroup),HttpStatus.OK);
    }

    //get exercises by Type
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Exercise>>getExercisesByType(@PathVariable ExerciseType type){
        return new ResponseEntity<List<Exercise>>(exerciseService.getExercisesByType(type),HttpStatus.OK);
    }

     @GetMapping("/filter")
    public ResponseEntity<List<Exercise>> filterExercises(
            @RequestParam(required = false) String muscleGroup,
            @RequestParam(required = false) ExerciseType type,
            @RequestParam(required = false) String searchQuery) {
        return ResponseEntity.ok(exerciseService.filterExercises(muscleGroup, type, searchQuery));
    }
}

