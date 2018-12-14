package com.shangwu.students.domain;

/**
 * @author ruanhui
 * @date 2018/12/14
 */
public class CourseInfo {
    private Integer id;
    private String courseName;
    private String coursePrice;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", coursePrice='" + coursePrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
