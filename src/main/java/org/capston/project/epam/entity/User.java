package org.capston.project.epam.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private int score;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String username;
    @Column(unique = true)
    private String email;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user")
    private Set<UserLesson> userLessons = new HashSet<>();
    @OneToMany(mappedBy = "user")
    private Set<UserQuizOption> userQuizOptions = new HashSet<>();
}