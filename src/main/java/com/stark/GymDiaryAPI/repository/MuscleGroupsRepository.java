package com.stark.GymDiaryAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.stark.GymDiaryAPI.model.MuscleGroup;


@Repository
public interface MuscleGroupsRepository extends MongoRepository<MuscleGroup, Integer> {

}
