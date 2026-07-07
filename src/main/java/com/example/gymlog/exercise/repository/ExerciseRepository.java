package com.example.gymlog.exercise.repository;

import com.example.gymlog.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
}
