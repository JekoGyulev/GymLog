package com.example.gymlog.muscle_group.repository;

import com.example.gymlog.muscle_group.model.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, UUID> {
}
