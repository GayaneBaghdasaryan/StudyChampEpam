package org.capston.project.epam.service;

import lombok.RequiredArgsConstructor;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.Option;
import org.capston.project.epam.entity.Quiz;
import org.capston.project.epam.repository.LessonRepository;
import org.capston.project.epam.repository.QuizRepository;
import org.hibernate.query.sqm.EntityTypeException;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;
    private Set<UUID> obtainedQuizzes = new HashSet<>();
    public Quiz getQuiz(UUID lessonId) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if (optionalLesson.isPresent()) {
            Set<Quiz> quizzes = optionalLesson.get().getQuizzes();

            // Assuming you want to return the first quiz in the set
            if (!quizzes.isEmpty()) {
                return quizzes.iterator().next();
            } else {
                // Handle the case where there are no quizzes
                System.out.println("No quizzes found for the lesson");
                return null;
            }
        } else {
            System.out.println("Lesson not found");
            return null;
        }
    }
    public Quiz nextQuiz(UUID lessonId) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);

        if (optionalLesson.isPresent()) {
            Set<Quiz> quizzes = optionalLesson.get().getQuizzes();
//            Quiz quiz = quizzes.stream()
//                    .filter(innerQuiz -> innerQuiz.getId().equals(quizId))
//                    .findFirst()
//                    .orElse(null);
            if (!quizzes.isEmpty()) {
                return quizzes.iterator().next();
            } else {
                System.out.println("No quizzes found for the lesson");
                return null;
            }
        } else {
            System.out.println("Lesson not found");
            return null;
        }
    }




    public Quiz getNextQuiz(UUID lessonId) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);

        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            Set<Quiz> quizzes = lesson.getQuizzes();

            if (!quizzes.isEmpty()) {
                // Filter out quizzes that have already been obtained
                Set<Quiz> availableQuizzes = quizzes.stream()
                        .filter(quiz -> !obtainedQuizzes.contains(quiz.getId()))
                        .collect(Collectors.toSet());

                if (!availableQuizzes.isEmpty()) {
                    // Pick the next available quiz, for example, the first one
                    Quiz nextQuiz = availableQuizzes.iterator().next();
                    obtainedQuizzes.add(nextQuiz.getId()); // Mark the quiz as obtained
                    return nextQuiz;
                } else {
                    System.out.println("No more available quizzes for the lesson");
                    return null;
                }
            } else {
                System.out.println("No quizzes found for the lesson");
                return null;
            }
        } else {
            System.out.println("Lesson not found");
            return null;
        }
    }


    public Set<Option> getOptions(UUID quizId) {
        Optional<Quiz> optionalQuiz = quizRepository.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Set<Option> options = optionalQuiz.get().getOptions();
            if (options.size() <4){
            return new HashSet<>(options);
            }else {
                return null;
            }
        } else {
            return Collections.EMPTY_SET; // Return an empty list if the quiz is not found
        }
    }

}
