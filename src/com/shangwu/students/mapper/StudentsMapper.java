package com.shangwu.students.mapper;

import com.shangwu.students.domain.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ruanhui
 * @date 2018/12/15
 */
public interface StudentsMapper {

    List<StudentInfo> getStudentsList(StudentInfo studentInfo);

    int getStudentsListTotal(StudentInfo studentInfo);

    void updateStudent(StudentInfo studentInfo);

    void updatePayment(StudentInfo studentInfo);

    void deleteStudent(@Param("id") Integer id);
}
