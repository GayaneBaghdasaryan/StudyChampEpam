package org.capston.project.epam.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
    @Column(nullable = false)
    private String text;
    private int point;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "quiz")
    @JsonIgnore
    private Set<Option> options = new HashSet<>();
    @OneToMany(mappedBy = "quiz")
    private Set<UserQuizOption> userQuizOptions = new HashSet<>();
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}