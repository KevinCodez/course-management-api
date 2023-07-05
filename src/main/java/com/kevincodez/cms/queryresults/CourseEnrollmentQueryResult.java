package com.kevincodez.cms.queryresults;

import com.kevincodez.cms.models.Course;
import com.kevincodez.cms.models.User;

public class CourseEnrollmentQueryResult {
    private User user;
    private Course course;

    public CourseEnrollmentQueryResult() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
