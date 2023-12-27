package org.capston.project.epam.repository;


import org.capston.project.epam.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    @Query("SELECT t FROM Topic t JOIN FETCH t.lessons WHERE t.id = :topicId")
    Topic findTopicWithLessons(@Param("topicId") UUID topicId);
}
