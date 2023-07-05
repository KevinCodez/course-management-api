package com.kevincodez.cms.services;

import com.kevincodez.cms.models.Lesson;
import com.kevincodez.cms.repositories.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessonsByCourseIdentifier(String identifier) {
        return lessonRepository.findLessonsByCourseIdentifier(identifier);
    }
}
