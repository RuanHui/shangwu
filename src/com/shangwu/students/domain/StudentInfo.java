package com.shangwu.students.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

/**
 * @author ruanhui
 * @date 2018/12/13
 * 学生实体类
 */
public class StudentInfo {
    private Integer id;
    private String name;    //姓名
    private Integer sex;        //性别
    private String age;    //年龄
    private String parentPhone;     //父母电话
    private String address;     //家庭住址
    private String courseId;     //课程ID
    private String courseName;     //课程名称
    private String paymentMode;     //支付方式
    private String paymentModeName;     //支付方式名称
    private String paymentAmount;     //支付金额

    public String getPaymentModeName() {
        return paymentModeName;
    }

    public void setPaymentModeName(String paymentModeName) {
        this.paymentModeName = paymentModeName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age='" + age + '\'' +
                ", parentPhone='" + parentPhone + '\'' +
                ", address='" + address + '\'' +
                ", courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", paymentModeName='" + paymentModeName + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                '}';
    }
}
