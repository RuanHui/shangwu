package com.shangwu.students.mapper;

import com.shangwu.students.domain.CourseInfo;
import com.shangwu.students.domain.StudentInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ruanhui
 * @date 2018/12/13
 */
public interface EnrollMapper {
    int insertStudent(StudentInfo studentInfo);

    List<CourseInfo> getCourseSelect();

    void insertPayment(StudentInfo studentInfo);
}
