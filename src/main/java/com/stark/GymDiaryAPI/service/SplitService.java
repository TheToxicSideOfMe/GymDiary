package com.stark.GymDiaryAPI.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.stark.GymDiaryAPI.model.Split;

import com.stark.GymDiaryAPI.repository.SplitRepository;

@Service
public class SplitService {
    @Autowired
    private SplitRepository splitRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Split>getAllSplits(){
        return splitRepository.findAll();
    }
    public Optional<Split>getSplitByid(String id){
        return splitRepository.findById(id);
    }

    public Split createSplit(Split split){
        return splitRepository.save(split);
    }


    public Optional<Split> updateSplit(String id,Split split){
        Update update= new Update();
        update.set("name", split.getName());
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(id)), update, Split.class);
        return splitRepository.findById(id);
    }
    public Boolean deleteSplit(String id){
        int beforeDelete = (int) splitRepository.count();

        splitRepository.deleteById(id);
        return (int) splitRepository.count()<beforeDelete;
    }
}
