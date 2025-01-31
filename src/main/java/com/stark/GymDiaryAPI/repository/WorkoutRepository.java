package com.stark.GymDiaryAPI.repository;







import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stark.GymDiaryAPI.model.Workout;
import java.util.List;


@Repository
public interface WorkoutRepository extends MongoRepository<Workout,String> {
    List<Workout> findWorkoutsBySplitId(String splitId);
}
