package org.capston.project.epam.mapper;

import org.capston.project.epam.dto.lesson.TopicDto;
import org.capston.project.epam.entity.Topic;
import org.springframework.stereotype.Component;


@Component
public class TopicMapper {

    public Topic toEntity(TopicDto topicDto) {
        if (topicDto == null) {
            return null;
        }
        Topic topic = new Topic();
        topic.setTitle(topicDto.getTitle());
        return topic;
    }
}


