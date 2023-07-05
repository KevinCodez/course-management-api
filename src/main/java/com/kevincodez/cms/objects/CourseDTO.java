package com.kevincodez.cms.objects;

import com.kevincodez.cms.models.Course;
import com.kevincodez.cms.models.Lesson;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO {
    private String identifier;
    private String title;
    private String teacher;
    private boolean isEnrolled;
    private List<Lesson> lessons = new ArrayList<>();

    public CourseDTO(Course course) {
        this.identifier = course.getIdentifier();
        this.title = course.getTitle();
        this.teacher = course.getTeacher();
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean isEnrolled() {
        return isEnrolled;
    }

    public void setEnrolled(boolean enrolled) {
        isEnrolled = enrolled;
    }
}
