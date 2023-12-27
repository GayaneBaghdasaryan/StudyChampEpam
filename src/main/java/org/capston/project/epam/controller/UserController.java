package org.capston.project.epam.controller;

import lombok.RequiredArgsConstructor;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.Quiz;
import org.capston.project.epam.service.QuizService;
import org.capston.project.epam.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserController {
    private final TopicService topicService;
    private final QuizService quizService;

    @GetMapping("/lessons")
    public ResponseEntity<Set<Lesson>> getLessonsByTopic(@RequestParam UUID topicId) {
        Set<Lesson> lessons = topicService.getLessonsByTopic(topicId);
        return ResponseEntity.ok(lessons);
    }
//    @GetMapping("/quiz")
//    public String getQuiz(@RequestParam UUID lessonId){
//        Quiz quiz = quizService.getQuiz(lessonId);
//        return "index";
//    }

}
