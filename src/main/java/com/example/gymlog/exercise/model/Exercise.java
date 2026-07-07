package com.example.gymlog.exercise.model;


import com.example.gymlog.exercise.enums.Difficulty;
import com.example.gymlog.exercise.enums.EquipmentType;
import com.example.gymlog.muscle_group.model.MuscleGroup;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @ManyToOne
    @JoinColumn(name = "primary_muscle_id", nullable = false)
    private MuscleGroup primaryMuscle;
    @Column(name = "image_url")
    private String imageUrl;
    @ElementCollection(targetClass = EquipmentType.class)
    @Enumerated(EnumType.STRING)
    private List<EquipmentType> equipment;
    @ElementCollection
    private List<String> instructions;



}
