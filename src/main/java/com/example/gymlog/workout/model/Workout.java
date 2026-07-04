package com.example.gymlog.workout.model;


import com.example.gymlog.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "total_volume", nullable = false)
    private int totalVolume;
    @Column(name = "is_completed", nullable = false)
    private boolean isCompleted;
    @Column(nullable = false)
    private int duration;
    @ElementCollection
    private List<String> notes;
    @Column(name = "workout_date", nullable = false)
    private LocalDate workoutDate;
    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;


}
