package org.capston.project.epam.service;

import lombok.RequiredArgsConstructor;

import org.capston.project.epam.dto.lesson.TopicDto;
import org.capston.project.epam.entity.Lesson;
import org.capston.project.epam.entity.Topic;
import org.capston.project.epam.mapper.TopicMapper;
import org.capston.project.epam.repository.LessonRepository;
import org.capston.project.epam.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final LessonRepository lessonRepository;
    private final TopicMapper topicMapper;

    public UUID addTopic(TopicDto topicDto) {
        Topic topic = topicMapper.toEntity(topicDto);
        return topicRepository.save(topic).getId();
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public void addLessonToTopic(UUID topicId, List<UUID> lessonIds) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();

            List<Lesson> lessons = lessonRepository.findByIdIn(lessonIds);
            for (Lesson lesson : lessons) {
                topic.getLessons().add(lesson);
            }
            topicRepository.save(topic);
        } else {
            System.out.println("Topic not found");
        }
    }

    public Set<Lesson> getLessonsByTopic(UUID topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            return topic.getLessons();
        } else {
            System.out.println("Topic not found");
            return null;
        }
    }


}
