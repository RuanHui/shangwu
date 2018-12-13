package com.shangwu.students.service;

import com.shangwu.students.domain.StudentInfo;
import com.shangwu.students.mapper.EnrollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ruanhui
 * @date 2018/12/13
 */

@Service
public class EnrollService {

    @Autowired
    private EnrollMapper mapper;

    //插入学生数据
    public int insertStudent(StudentInfo studentInfo) {
        return mapper.insertStudent(studentInfo);
    }
}
