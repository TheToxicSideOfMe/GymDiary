package com.stark.GymDiaryAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stark.GymDiaryAPI.model.Exercise;



@Repository
public interface ExerciseRepository extends MongoRepository<Exercise, Integer> {

}
