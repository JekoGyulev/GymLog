package com.example.gymlog.muscle_group.model;


import com.example.gymlog.muscle_group.enums.DevelopmentDifficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "muscle_groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MuscleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(name = "development_difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private DevelopmentDifficulty developmentDifficulty;
    @Column(name = "workout_frequency", nullable = false)
    private int workoutFrequencyPerWeek;
    @Column(name = "recommended_rest_hours", nullable = false)
    private int recommendedRestHours;


}
