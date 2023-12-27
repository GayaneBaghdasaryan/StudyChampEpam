package org.capston.project.epam.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonDto {
    String topicId;
    String title;
    QuizDto quiz;
    List<LessonContentDto> lessonContents;
}
