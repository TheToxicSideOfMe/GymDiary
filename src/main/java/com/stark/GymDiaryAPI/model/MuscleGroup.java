package com.stark.GymDiaryAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record MuscleGroup(
    @Id int id,
    String name
) {}
