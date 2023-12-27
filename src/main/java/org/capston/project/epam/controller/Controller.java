package org.capston.project.epam.controller;


import lombok.RequiredArgsConstructor;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.Option;
import org.capston.project.epam.entity.Quiz;
import org.capston.project.epam.entity.Topic;
import org.capston.project.epam.repository.LessonRepository;
import org.capston.project.epam.repository.OptionRepository;
import org.capston.project.epam.repository.QuizRepository;
import org.capston.project.epam.service.LessonService;
import org.capston.project.epam.service.QuizService;
import org.capston.project.epam.service.TopicService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final TopicService topicService;
    private final LessonService lessonService;
    private final QuizService quizService;
    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;
    private final OptionRepository optionRepository;


    @GetMapping("/topic")
    public String topicPage(Model model) {
        List<Topic> topics = topicService.getAllTopics();
        if (!topics.isEmpty()) {
            Topic topic = topics.get(0);
            model.addAttribute("topicName", topic.getTitle());
            model.addAttribute("lessons", topic.getLessons());
        } else {
            model.addAttribute("topicName", "No Topic Available");
        }
        return "topic";
    }
    @GetMapping("/getQuiz/{lessonId}")
    public String getQuizPage(@PathVariable UUID lessonId, Model model) {
        Quiz quiz = quizService.getQuiz(lessonId);
        model.addAttribute("quiz", quiz); // Replace "quizData" with the actual variable name
        return "index";
    }

    @GetMapping("/next/option")
    public String getQuizOptions(){
        return "lool";
    }

    @GetMapping("/get/next/option/{lessonId}")
    public String getNextOption(@PathVariable UUID lessonId, Model model) {
     Quiz quiz =  quizService.nextQuiz(lessonId);
        System.out.println(quiz);
        // Add the updated quiz to the model to be rendered in the view
        model.addAttribute("quiz", quiz);
        System.out.println(lessonId);
        // Return the view name, assuming you have a Thymeleaf template named "quiz" to render the quiz
        return "login";
    }

}
