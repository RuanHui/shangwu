package com.shangwu.students.controller;

import com.alibaba.fastjson.JSONObject;
import com.shangwu.students.domain.StudentInfo;
import com.shangwu.students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ruanhui
 * @date 2018/12/15
 * 学生信息列表
 */
@Controller
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsService service;

    /**
     *@author ruanhui
     *@date 2018/12/15
     *@description 获取学生信息
     */
    @RequestMapping(value = "/getStudentsList.json")
    @ResponseBody
    public JSONObject getStudentsList(HttpServletRequest request, HttpServletResponse response, StudentInfo studentInfo) {
        return service.getStudentsList(request,response,studentInfo);
    }


    /**
     *@author ruanhui
     *@date 2018/12/15
     *@description 修改学生信息
     */
    @RequestMapping(value = "/updateStudent.json")
    @ResponseBody
    public Map updateStudent(HttpServletRequest request, HttpServletResponse response, StudentInfo studentInfo) {
        return service.updateStudent(request,response,studentInfo);
    }

    /**
     *@author ruanhui
     *@date 2018/12/15
     *@description 删除学生信息
     */
    @RequestMapping(value = "/deleteStudent.json")
    @ResponseBody
    public Map<String, Object> deleteStudent(HttpServletRequest request, HttpServletResponse response, StudentInfo studentInfo) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap.put("success",true);
            service.deleteStudent(request,response,studentInfo);
        }catch (Exception e) {
            resultMap.put("success",false);
            System.out.println("删除学生遇到错误：" + e);
            e.printStackTrace();
        }
        return resultMap;
    }
}
