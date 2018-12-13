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

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", parentPhone='" + parentPhone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
