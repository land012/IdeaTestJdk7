package com.umbrella.demo.json.jackson.codehaus.domain;

/**
 * Created by 大洲 on 15-3-11.
 * 课程
 */
public class Course {
    private long courseId;
    private String courseName;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
