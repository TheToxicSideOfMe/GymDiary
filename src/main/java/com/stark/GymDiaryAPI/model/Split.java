package com.stark.GymDiaryAPI.model;


import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Document(collection = "splits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Split {
    @Id
    private String id;
    
    @NotBlank
    private String name;

    
    
  
}