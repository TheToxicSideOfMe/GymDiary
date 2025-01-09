package com.stark.GymDiaryAPI.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Workout(
    @Id
    int id,
    int splitId,
    String title,
    List<Integer> exerciseIds
) {

}
