package com.shangwu.students.service;

import com.alibaba.fastjson.JSONObject;
import com.shangwu.students.domain.StudentInfo;
import com.shangwu.students.mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.org.mozilla.javascript.internal.EcmaError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author ruanhui
 * @date 2018/12/15
 */

@Service
public class StudentsService {

    @Autowired
    private StudentsMapper mapper;

    /**
     *@author ruanhui
     *@date 2018/12/15
     *@description 获取学生信息列表
     */
    public JSONObject getStudentsList(HttpServletRequest request, HttpServletResponse response, StudentInfo studentInfo) {
        JSONObject json=new JSONObject();
        try {
            //查询列表 数据
            List<StudentInfo> resultList=mapper.getStudentsList(studentInfo);
            //查询总数
            int total=mapper.getStudentsListTotal(studentInfo);
            //需要返回的数据有总记录数和行数据
            json.put("rows", resultList);
            json.put("total", total);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     *@author ruanhui
     *@date 2018/12/15
     *@description 修改学生信息
     */
    public Map updateStudent(HttpServletRequest request, HttpServletResponse response, StudentInfo studentInfo) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //更新学生信息表
            mapper.updateStudent(studentInfo);
            //更新缴费信息表
            mapper.updatePayment(studentInfo);
            resultMap.put("success",true);
        }catch (Exception e) {
            resultMap.put("success",false);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     *@author ruanhui
     *@date 2018/12/17
     *@description 删除功能
     */
    public void deleteStudent(HttpServletRequest request, HttpServletResponse response, StudentInfo studentInfo) {
        mapper.deleteStudent(studentInfo.getId());
    }
}
