package com.shangwu.students.service;

import com.shangwu.students.domain.CourseInfo;
import com.shangwu.students.domain.StudentInfo;
import com.shangwu.students.mapper.EnrollMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruanhui
 * @date 2018/12/13
 */

@Service
public class EnrollService {

    @Autowired
    private EnrollMapper mapper;

    //插入学生数据
    public Map insertStudent(StudentInfo studentInfo) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //插入学生表
            mapper.insertStudent(studentInfo);
            //获取学生的id
            Integer id = studentInfo.getId();
            //插入缴费表
            mapper.insertPayment(studentInfo);
            //TODO 打印小票

            resultMap.put("success",true);
        }catch (Exception e) {
            e.printStackTrace();
            resultMap.put("success",false);
        }
        return resultMap;
    }

    public Map<String,Object> getCourseSelect() {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            List<CourseInfo> optionsList = mapper.getCourseSelect();
            resultMap.put("success",true);
            resultMap.put("couses",optionsList);
        }catch (Exception e) {
            e.printStackTrace();
            resultMap.put("success",false);
        }
        return resultMap;
    }
}
