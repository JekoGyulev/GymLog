package com.example.gymlog.exercise_set.repository;

import com.example.gymlog.exercise_set.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {


}
