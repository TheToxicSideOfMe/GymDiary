package com.stark.GymDiaryAPI.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "exercises")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    private String id;
    
    @NotBlank
    @Indexed(unique = true)
    private String name;
    
    private ExerciseType type;
    
    @NotBlank
    private String muscleGroup; 
    
    private String description;

    
    public enum ExerciseType {
        COMPOUND, ISOLATION
    }
}