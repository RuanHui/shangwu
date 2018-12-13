package com.shangwu.students.controller;

import com.shangwu.students.domain.StudentInfo;
import com.shangwu.students.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ruanhui
 * @date 2018/12/13
 */
@Controller
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    private EnrollService service;

    @RequestMapping(value = "/enroll.json",method = RequestMethod.POST)
    public Map enroll(StudentInfo studentInfo) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //插入学生表
            service.insertStudent(studentInfo);
            //获取学生的id
            Integer id = studentInfo.getId();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
