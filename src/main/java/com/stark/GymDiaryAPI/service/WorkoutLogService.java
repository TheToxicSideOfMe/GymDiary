package com.stark.GymDiaryAPI.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.model.Workout;
import com.stark.GymDiaryAPI.model.WorkoutLog;
import com.stark.GymDiaryAPI.repository.ExerciseRepository;
import com.stark.GymDiaryAPI.repository.WorkoutLogRepository;
import com.stark.GymDiaryAPI.repository.WorkoutRepository;

@Service
public class WorkoutLogService {
    @Autowired
    private WorkoutLogRepository workoutLogRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ExerciseRepository exerciseRepository;

    public WorkoutLog createLog(String workoutId, WorkoutLog workoutLog){
        Workout workout=workoutRepository.findById(workoutId)
            .orElseThrow(()-> new IllegalArgumentException("workout not found with id: "+ workoutId));

            
        workoutLog.getExerciseLogs().forEach(exerciseLog -> {
            Exercise completeExercise = exerciseRepository.findById(exerciseLog.getExercise().getId())
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));
            exerciseLog.setExercise(completeExercise);
        });

        workoutLog.setWorkout(workout);
        return workoutLogRepository.save(workoutLog);
    }

    public List<WorkoutLog>getWorkoutLogs(String WorkoutId){
        return workoutLogRepository.findWorkoutLogsByWorkoutId(WorkoutId);
    }

    public WorkoutLog getLogById(String id){
        return workoutLogRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Workout Log not found with id: " + id));
    }

    public WorkoutLog updateLog(String id, WorkoutLog NewLog){
        Update update= new Update();
        update.set("exerciseLogs", NewLog.getExerciseLogs());
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(id)), update, WorkoutLog.class);
        return NewLog;
    }
    public Boolean deleteLog(String logId) {
        if (workoutLogRepository.existsById(logId)) {
            workoutLogRepository.deleteById(logId);
            return true;
        } else {
            return false;
        }
    }
}
