package org.capston.project.epam.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.capston.project.epam.dto.lesson.LessonDto;
import org.capston.project.epam.dto.lesson.OptionDto;
import org.capston.project.epam.dto.lesson.QuizDto;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.Option;
import org.capston.project.epam.entity.Quiz;
import org.capston.project.epam.mapper.LessonMapper;
import org.capston.project.epam.repository.LessonRepository;
import org.capston.project.epam.repository.OptionRepository;
import org.capston.project.epam.repository.QuizRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final QuizRepository quizRepository;
    private final OptionRepository optionRepository;
    public List<Lesson> searchLessons(String keyword) {
        return lessonRepository.findByTitleContainingIgnoreCase(keyword);
    }

public void addQuizToLesson(UUID lessonId, QuizDto quizRequest) {
    Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new EntityNotFoundException("Lesson not found with id: " + lessonId));
    Quiz quiz = Quiz.builder()
            .lesson(lesson)
            .text(quizRequest.getText())
            .point(quizRequest.getPoint())
            .build();
    quizRepository.save(quiz);
}


    public void addOptionToQuiz(UUID quizId, Set<OptionDto> optionDtoSet) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with ID: " + quizId));

            for (OptionDto optionDto : optionDtoSet) {
                Option option = Option.builder()
                        .quiz(quiz)
                        .text(optionDto.getText())
                        .correct(optionDto.isCorrect())
                        .build();

                quiz.getOptions().add(option);
            }

            quizRepository.save(quiz);

    }


    @Transactional
    public UUID addLesson(LessonDto lessonDto) {
        Lesson lesson = lessonMapper.mapToLesson(lessonDto);
        return lessonRepository.save(lesson).getId();
    }
}
