package com.stark.GymDiaryAPI;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stark.GymDiaryAPI.model.MuscleGroup;
import com.stark.GymDiaryAPI.repository.MuscleGroupsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class MuscleGroupsDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MuscleGroupsDataLoader.class);

    private final MuscleGroupsRepository muscleGroupsRepository;
    private final ObjectMapper objectMapper;

    public MuscleGroupsDataLoader(MuscleGroupsRepository muscleGroupsRepository, ObjectMapper objectMapper) {
        this.muscleGroupsRepository = muscleGroupsRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        if (muscleGroupsRepository.count() == 0) {
            log.info("Loading MuscleGroups data...");
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/Data/MuscleGroups.json")) {
                if (inputStream == null) {
                    throw new RuntimeException("File not found: /Data/MuscleGroups.json");
                }

                List<MuscleGroup> muscleGroups = objectMapper.readValue(inputStream, new TypeReference<List<MuscleGroup>>() {});
                muscleGroupsRepository.saveAll(muscleGroups);

                log.info("Saved {} MuscleGroups to the database.", muscleGroups.size());
            } catch (Exception e) {
                log.error("Failed to load MuscleGroups data: {}", e.getMessage(), e);
            }
        } else {
            log.info("MuscleGroups collection already contains data. Skipping loading.");
        }
    }
}
