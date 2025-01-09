package com.stark.GymDiaryAPI.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stark.GymDiaryAPI.model.Split;

@Repository
public interface SplitRepository extends MongoRepository<Split,Integer> {
    
}
