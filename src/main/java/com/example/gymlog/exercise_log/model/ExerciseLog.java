package com.example.gymlog.exercise_log.model;


import com.example.gymlog.exercise.model.Exercise;
import com.example.gymlog.workout.model.Workout;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "exercise_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseLog {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
    @ManyToOne
    @JoinColumn(name = "exercise_id",  nullable = false)
    private Exercise exercise;


}
