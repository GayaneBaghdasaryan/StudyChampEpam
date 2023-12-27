package org.capston.project.epam.controller;

import lombok.RequiredArgsConstructor;
import org.capston.project.epam.dto.lesson.LessonDto;
import org.capston.project.epam.dto.lesson.OptionDto;
import org.capston.project.epam.dto.lesson.QuizDto;
import org.capston.project.epam.dto.lesson.TopicDto;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.Option;
import org.capston.project.epam.entity.Quiz;
import org.capston.project.epam.entity.Topic;
import org.capston.project.epam.repository.QuizRepository;
import org.capston.project.epam.service.LessonService;
import org.capston.project.epam.service.QuizService;
import org.capston.project.epam.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LessonService lessonService;
    private final TopicService topicService;
    private final QuizService quizService;

    @PostMapping("/add/lesson")
    public ResponseEntity<UUID> addLesson(@RequestBody LessonDto lessonDto) {
        return ResponseEntity.ok(lessonService.addLesson(lessonDto));
    }

    @PostMapping("/add/topic")
    public ResponseEntity<UUID> addTopic(@RequestBody TopicDto topicDto) {
        return new ResponseEntity<>(topicService.addTopic(topicDto), HttpStatus.CREATED);
    }

    @PostMapping("/add/lessonToTopic")
    public ResponseEntity<Topic> addLessonToTopic(
            @RequestParam UUID topicId,
            @RequestParam List<UUID> lessonId) {
        topicService.addLessonToTopic(topicId, lessonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> getTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
    @GetMapping("/lessons/search")
    public ResponseEntity<List<Lesson>> searchLessons(@RequestParam String keyword) {
        List<Lesson> searchResults = lessonService.searchLessons(keyword);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PostMapping("/add/quiz/{lessonId}")
    public ResponseEntity<String> addQuizToLesson(@RequestParam UUID lessonId, @RequestBody QuizDto quizRequest) {
        lessonService.addQuizToLesson(lessonId, quizRequest);
        return ResponseEntity.ok("Quiz added to lesson successfully");
    }
    @PostMapping("/{quizId}/add/option")
    public ResponseEntity<String> addOptionToQuiz(
            @RequestParam UUID quizId,
            @RequestBody Set<OptionDto> optionDto) {
        try {
            if (optionDto.size() == 4) {
                lessonService.addOptionToQuiz(quizId, optionDto);
                return ResponseEntity.ok("Quiz added to lesson successfully");
            } else {
                return ResponseEntity.badRequest().body("A quiz can have only four options.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/options")
    public Set<Option> getOptions(@RequestParam UUID quizId){
        return quizService.getOptions(quizId);
    }
}
