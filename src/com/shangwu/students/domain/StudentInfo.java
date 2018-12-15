package com.shangwu.students.domain;

/**
 * @author ruanhui
 * @date 2018/12/13
 * 学生实体类
 */
public class StudentInfo{
    private Integer id;
    private String name;    //姓名
    private Integer sex;        //性别
    private String age;    //年龄
    private String parentPhone;     //父母电话
    private String province;     //省
    private String city;     //市
    private String district;     //区
    private String address;     //详细住址
    private String courseId;     //课程ID
    private String courseName;     //课程名称
    private String paymentMode;     //支付方式
    private String paymentModeName;     //支付方式名称
    private String paymentAmount;     //支付金额
    private String insertTime;     //记录时间
    private int offset;
    private int limit;
    private int start;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = offset * limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

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
                ", insertTime='" + insertTime + '\'' +
                '}';
    }
}
