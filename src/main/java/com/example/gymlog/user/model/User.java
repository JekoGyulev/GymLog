package com.example.gymlog.user.model;


import com.example.gymlog.user.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "phone_number",unique = true)
    private String phoneNumber;
    @Column(name = "weight_in_kg")
    private Double weightInKG;
    @Column
    private Double height;
    @Column(name = "body_fat_percentage")
    private Double bodyfatPercentage;
    @Column(name = "preferred_metric")
    @Enumerated(EnumType.STRING)
    private Unit preferredUnit;
    @Column
    private String bio;
    @Column
    private String location;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "experience_level")
    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;
    @Column(name = "primary_workout_goal")
    @Enumerated(EnumType.STRING)
    private PrimaryWorkoutGoal primaryWorkoutGoal;
    @Column(name = "preferred_training_split")
    @Enumerated(EnumType.STRING)
    private TrainingSplit primaryTrainingSplit;
    @Column(name = "frequency_per_week_to_gym")
    private Integer frequencyPerWeekToGym;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "profile_picture_url", unique = true)
    private String profilePictureUrL;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "registered_on", nullable = false)
    @CreationTimestamp
    private LocalDate registeredOn;
    @Column(name = "is_active",  nullable = false)
    private boolean isActive;


}
