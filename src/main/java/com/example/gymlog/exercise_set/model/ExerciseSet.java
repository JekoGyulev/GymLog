package com.example.gymlog.exercise_set.model;


import com.example.gymlog.exercise_log.model.ExerciseLog;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "exercise_sets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "set_number", nullable = false)
    private int setNumber;
    @Column(nullable = false)
    private int reps;
    @Column(nullable = false)
    private double weight;
    @Column(name = "rest_time_seconds", nullable = false)
    private int restTimeSeconds;
    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "exercise_log", nullable = false)
    private ExerciseLog exerciseLog;


}
