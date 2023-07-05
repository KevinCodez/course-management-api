package com.kevincodez.cms.services;

import com.kevincodez.cms.models.Course;
import com.kevincodez.cms.queryresults.CourseEnrollmentQueryResult;
import com.kevincodez.cms.repositories.CourseRepository;
import com.kevincodez.cms.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrollmentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseEnrollmentService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Boolean getEnrollmentStatus(String username, String identifier) {
        return userRepository.findEnrollmentStatus(username, identifier);
    }

    public CourseEnrollmentQueryResult enrollIn(String username, String identifier) {
        return userRepository.createEnrollmentRelationship(username, identifier);
    }

    public List<Course> getAllEnrolledCoursesByUsername(String username) {
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }
}
