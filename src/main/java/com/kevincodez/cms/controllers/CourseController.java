package com.kevincodez.cms.controllers;

import com.kevincodez.cms.models.Course;
import com.kevincodez.cms.models.Lesson;
import com.kevincodez.cms.objects.CourseDTO;
import com.kevincodez.cms.services.CourseEnrollmentService;
import com.kevincodez.cms.services.CourseService;
import com.kevincodez.cms.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class CourseController {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseEnrollmentService courseEnrollmentService;

    public CourseController(CourseService courseService, LessonService lessonService, CourseEnrollmentService courseEnrollmentService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> courseIndex(Principal principal) {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> responseCourses = courses.stream().map(course -> {
            CourseDTO responseCourse = new CourseDTO(course);
            List<Lesson> lessons = lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier());
            responseCourse.setLessons(lessons);

            if (principal != null) {
                responseCourse.setEnrolled(courseEnrollmentService.getEnrollmentStatus(principal.getName(), course.getIdentifier()));
            }

            return responseCourse;
        }).toList();
        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @GetMapping("/course/{identifier}")
    public ResponseEntity<CourseDTO> courseDetails(@PathVariable String identifier) {
        Course course = courseService.getCourseByIdentifier(identifier);
        List<Lesson> lessons = lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier());
        CourseDTO responseCourse = new CourseDTO(course);
        responseCourse.setLessons(lessons);
        return new ResponseEntity<>(responseCourse, HttpStatus.OK);
    }

}
