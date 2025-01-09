package com.stark.GymDiaryAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stark.GymDiaryAPI.model.Exercise;
import com.stark.GymDiaryAPI.repository.ExerciseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class ExerciseDataLoader implements CommandLineRunner {
    
    private static final Logger log = LoggerFactory.getLogger(ExerciseDataLoader.class);
    private final ExerciseRepository exerciseRepository;
    private final ObjectMapper objectMapper;

    public ExerciseDataLoader(ExerciseRepository exerciseRepository, ObjectMapper objectMapper) {
        this.exerciseRepository = exerciseRepository;
        this.objectMapper = objectMapper;
    }

    
    @Override
    public void run(String... args) {
        if (exerciseRepository.count() == 0) {
            log.info("Loading Exercise data...");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/Data/Exercises.json")) {
                if (inputStream == null) {
                    throw new RuntimeException("File not found: /Data/Exercises.json");
                }

                List<Exercise> exercises = objectMapper.readValue(inputStream, new TypeReference<List<Exercise>>() {});
                exerciseRepository.saveAll(exercises);

                log.info("Saved {} Exercises to the database.", exercises.size());
            } catch (Exception e) {
                log.error("Failed to load Exercise data: {}", e.getMessage(), e);
            }
        } else {
            log.info("Exercise collection already contains data. Skipping loading.");
        }
    }
}
