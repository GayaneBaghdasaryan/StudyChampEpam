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
public class QuizDto {
    private String text;
    private int point;
    private Set<OptionDto> options;
}
