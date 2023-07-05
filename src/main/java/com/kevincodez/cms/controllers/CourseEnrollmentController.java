package com.kevincodez.cms.controllers;

import com.kevincodez.cms.models.Course;
import com.kevincodez.cms.models.Lesson;
import com.kevincodez.cms.objects.CourseDTO;
import com.kevincodez.cms.objects.CourseEnrollmentDTO;
import com.kevincodez.cms.queryresults.CourseEnrollmentQueryResult;
import com.kevincodez.cms.requests.CourseEnrollmentRequest;
import com.kevincodez.cms.services.CourseEnrollmentService;
import com.kevincodez.cms.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class CourseEnrollmentController {
    private final CourseEnrollmentService courseEnrollmentService;
    private final LessonService lessonService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService, LessonService lessonService) {
        this.courseEnrollmentService = courseEnrollmentService;
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> enrollments(Principal principal) {
        List<Course> courses = courseEnrollmentService.getAllEnrolledCoursesByUsername(principal.getName());
        List<CourseDTO> responseCourses = courses.stream().map(course -> {
            List<Lesson> lessons = lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier());
            CourseDTO responseCourse = new CourseDTO(course);
            responseCourse.setLessons(lessons);
            responseCourse.setEnrolled(true);
            return responseCourse;
        }).toList();
        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseEnrollmentDTO> enrollIn(@RequestBody CourseEnrollmentRequest request, Principal principal) {
        CourseEnrollmentQueryResult enrollmentQueryResult = courseEnrollmentService.enrollIn(principal.getName(), request.getCourseIdentifier());
        CourseEnrollmentDTO responseEnrollment = new CourseEnrollmentDTO(
                enrollmentQueryResult.getUser().getUsername(),
                enrollmentQueryResult.getUser().getName(),
                enrollmentQueryResult.getCourse()
        );
        return new ResponseEntity<>(responseEnrollment, HttpStatus.OK);
    }
}
