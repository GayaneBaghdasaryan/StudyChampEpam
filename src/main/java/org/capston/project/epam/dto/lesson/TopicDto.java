package org.capston.project.epam.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicDto {
    private String title;
    private String description;
    private Set<LessonDto> lessonDtos;
}
