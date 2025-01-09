package com.stark.GymDiaryAPI.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stark.GymDiaryAPI.model.Workout;

@Repository
public interface WorkoutRepository extends MongoRepository<Workout,Integer> {
    List<Workout> findBySplitId(int splitId);

}
