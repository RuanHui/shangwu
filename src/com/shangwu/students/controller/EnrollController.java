package com.shangwu.students.controller;

import com.shangwu.students.domain.StudentInfo;
import com.shangwu.students.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    /**
     *@author ruanhui
     *@date 2018/12/14
     *@description 报名新增
     */
    @RequestMapping(value = "/enroll.json",method = RequestMethod.POST)
    @ResponseBody
    public Map enroll(HttpServletRequest request,StudentInfo studentInfo) {
        return service.insertStudent(request,studentInfo);
    }

    /**
     *@author ruanhui
     *@date 2018/12/14
     *@description 获取课程下拉框
     */
    @RequestMapping(value = "/getCourseSelect.json",method = RequestMethod.POST)
    @ResponseBody
    public Map getCourseSelect(HttpServletRequest request, HttpServletResponse response) {
        return service.getCourseSelect();
    }

    /**
     *@author ruanhui
     *@date 2018/12/14
     *@description 测试打印
     */
    @RequestMapping(value = "/testPring.json",method = RequestMethod.POST)
    @ResponseBody
    public void testPring(HttpServletRequest request, HttpServletResponse response) {
        service.testPring(request);
    }
}
