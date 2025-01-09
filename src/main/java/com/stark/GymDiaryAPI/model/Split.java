package com.stark.GymDiaryAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
public record Split(
    @Id int id,  // Unique identifier for the split
    String name // Name of the split (e.g., "Full Body", "Upper/Lower Split")
) {}
