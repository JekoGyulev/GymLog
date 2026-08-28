package com.example.gymlog.personal_record.model;


import com.example.gymlog.exercise.model.Exercise;
import com.example.gymlog.user.model.User;
import com.example.gymlog.workout.model.Workout;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "personal_records")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "best_weight", nullable = false)
    private double bestWeight;
    @Column(name = "estimated_1_RM", nullable = false)
    private double estimated1RM;
    @Column(name = "achieved_on", nullable = false)
    private LocalDate achievedOn;
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

}
