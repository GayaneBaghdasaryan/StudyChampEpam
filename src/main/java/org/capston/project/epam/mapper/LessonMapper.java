package org.capston.project.epam.mapper;


import org.capston.project.epam.dto.lesson.LessonContentDto;
import org.capston.project.epam.dto.lesson.LessonDto;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.LessonContent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonMapper {
    public Lesson mapToLesson(LessonDto lessonDto) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDto.getTitle());

        List<LessonContent> lessonContents = lessonDto.getLessonContents().stream()
                .map(this::mapToLessonContent)
                .collect(Collectors.toList());

        lesson.setLessonContents(lessonContents);
        return lesson;
    }

    private LessonContent mapToLessonContent(LessonContentDto lessonContentDto) {
        return LessonContent.builder()
                .text(lessonContentDto.getText())
                .videoUrl(lessonContentDto.getVideoUrl())
                .order(lessonContentDto.getOrder())
                .build();
    }

}



