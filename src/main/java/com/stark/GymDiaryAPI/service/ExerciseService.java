package com.stark.GymDiaryAPI.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.model.Exercise.ExerciseType;
import com.stark.GymDiaryAPI.repository.ExerciseRepository;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }
    public List<Exercise> getExercisesByMuscleGroup(String muscleGroup){
        return exerciseRepository.findByMuscleGroup(muscleGroup);
    }
    public List<Exercise> getExercisesByType(ExerciseType type){
        return exerciseRepository.findByType(type);
    }

    public List<Exercise> filterExercises(String muscleGroup, ExerciseType type, String searchQuery) {
        if (muscleGroup != null && type != null && searchQuery != null) {
            // Combined search with all filters
            return exerciseRepository.findAll().stream()
                .filter(e -> e.getMuscleGroup().equalsIgnoreCase(muscleGroup))
                .filter(e -> e.getType() == type)
                .filter(e -> e.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());
        }
        // Handle other combinations...
        if (muscleGroup != null && type != null) {
            return exerciseRepository.findByMuscleGroupAndType(muscleGroup, type);
        }
        if (muscleGroup != null) {
            return exerciseRepository.findByMuscleGroup(muscleGroup);
        }
        if (type != null) {
            return exerciseRepository.findByType(type);
        }
        if (searchQuery != null) {
            return exerciseRepository.findByNameContainingIgnoreCase(searchQuery);
        }
        return exerciseRepository.findAll();
    }

}
