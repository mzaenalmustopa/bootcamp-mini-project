package com.demo.quickcount.config;

import com.demo.quickcount.model.entity.SurveyDataEntity;
import com.demo.quickcount.model.entity.SurveyEntity;
import com.demo.quickcount.repository.SurveyRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final SurveyRepo surveyRepo;
    @Override
    public void run(String... args) throws Exception {
        if (surveyRepo.count() == 0) {
            try {
                surveyRepo.saveAll(Arrays.asList(
                        new SurveyEntity(UUID.randomUUID().toString(), "PASLON 01", 0L, 0.0),
                        new SurveyEntity(UUID.randomUUID().toString(), "PASLON 02", 0L, 0.0),
                        new SurveyEntity(UUID.randomUUID().toString(), "PASLON 03", 0L, 0.0)
                ));
            }catch (Exception e){
                log.error("Survey entity could not be saved");
                e.printStackTrace();
            }
        }
    }
}
