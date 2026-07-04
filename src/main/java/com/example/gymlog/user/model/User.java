package com.example.gymlog.user.model;


import com.example.gymlog.user.enums.Role;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "profile_picture_url", nullable = false)
    private String profilePictureUrL;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    // Add necessary relationships


}
