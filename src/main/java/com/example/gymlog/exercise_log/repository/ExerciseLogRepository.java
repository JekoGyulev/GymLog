package com.example.gymlog.exercise_log.repository;

import com.example.gymlog.exercise_log.model.ExerciseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, UUID> {
}
