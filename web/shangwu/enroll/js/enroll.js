//定义一个map，用来装id和价格
var m = new Map();

$(function () {

    $('[data-toggle="popover"]').popover();
    //初始化省市区下拉框
    var $distpicker = $('#distpicker');
    $distpicker.distpicker({
        province: '湖北省',
        city: '武汉市',
        district: '黄陂区'
    });
    //为课程下拉框获取值
    getCourseSelect();
})
//提交方法
function doSubmit() {
    //提交之前校验
    var flag = verify();
    if(flag == true) {
        var name = $("#name").val();
        var parentPhone = $("#parentPhone").val();
        var courseName = $("#courceSelect").find("option:selected").text();
        var paymentAmount = $("#paymentAmount").val();
        var paymentModeName = $("#paymentMode").find("option:selected").text();
        //防止重复提交
        $("#submit").prop("disabled","disabled");
        //发送ajax请求
        $.ajax({
            url : '/enroll/enroll.json',
            type : 'post',
            data : {
                name : name,
                age : $("#age").val(),
                sex : $("input[name='sex']:checked").val(),
                parentPhone : parentPhone,
                province:$("#province").val(),
                city:$("#city").val(),
                district:$("#district").val(),
                //获取省市区 下拉框的值$("#distpicker option:checked").text()
                address : $("#address").val(),
                courseId:$("#courceSelect").val(),
                courseName:courseName,
                paymentMode:$("#paymentMode").val(),
                paymentModeName:paymentModeName,
                paymentAmount:paymentAmount
            },
            dataType : 'json',
            success : function(d) {
                if (d.success) {
                    //打印发票
                    doPrint(name,parentPhone,courseName,paymentAmount,paymentModeName);
                    //恢复按钮
                    $("#submit").removeAttr("disabled");
                    $('#submit').popover("show");
                    //1秒后隐藏弹出层
                    setTimeout(function () { $('#submit').popover("hide");},1000);
                    //清空输入框
                    resetForm();
                }else {
                    //恢复按钮
                    $("#submit").removeAttr("disabled");
                    $('#submitErrorDiv').show();
                    //1秒后隐藏弹出层
                    setTimeout(function () { $('#submitErrorDiv').hide();},1000);
                }
            },
            error:function (e) {
                $('#submitErrorDiv').show();
                //1秒后隐藏弹出层
                setTimeout(function () { $('#submitErrorDiv').hide();},1000);
            }
        });
    }
}

//校验方法
function verify() {
    //校验姓名
    var name = $("#name").val();
    if (name == "" ) {
        //在手机号后面提示手机号错误
        $('#nameErrorDiv').show();
        //1秒后隐藏错误框
        setTimeout(function () { $('#nameErrorDiv').hide(); },1000);
        return false;
    }
    //校验手机号
    var phone = $("#parentPhone").val();
    var flag = isPoneAvailable(phone);
    if (flag == false) {
        //在手机号后面提示手机号错误
        $('#phoneErrorDiv').show();
        //1秒后隐藏错误框
        setTimeout(function () { $('#phoneErrorDiv').hide(); },1000);
        return false;
    }
    //校验年龄
    try {
        var age = parseInt($("#age").val());
        if (age > 60 || age < 1) {
            $('#ageErrorDiv').show();
            //1秒后隐藏错误框
            setTimeout(function () { $('#ageErrorDiv').hide(); },1000);
            return false;
        }
    }catch (e) {
        console.log("年龄格式错误：" + e);
        $('#ageErrorDiv').show();
        //1秒后隐藏错误框
        setTimeout(function () { $('#ageErrorDiv').hide(); },1000);
        return false;
    }
    //校验省市区地址
    var province = $("#province").val();
    var city = $("#city").val();
    var district = $("#district").val();
    var address = $("#address").val();
    if (province == "" || city == "" || district == "" || address == "")  {
        $('#addressErrorDiv').show();
        //1秒后隐藏错误框
        setTimeout(function () { $('#addressErrorDiv').hide(); },1000);
        return false;
    }
    //校验数字（缴费金额）
    var payment = $("#paymentAmount").val();
    var isPayment = isNumber(payment);
    if (isPayment == false) {
        $('#paymentAmountErrorDiv').show();
        //1秒后隐藏错误框
        setTimeout(function () { $('#paymentAmountErrorDiv').hide(); },1000);
        return false;
    }
    return true;
}

//校验数字
function isNumber(number) {
    var myreg = /^\d+.?\d{0,2}$/;
    if (!myreg.test(number)) {
        return false;
    } else {
        return true;
    }
}

//判断是否为手机号
function isPoneAvailable(phone) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(phone)) {
        return false;
    } else {
        return true;
    }
}

//清空表单
function resetForm() {
    $("#name").val("");
    $("#age").val("");
    $("#parentPhone").val("");
    $("#address").val("");
}

//打印方法
function doPrint(name,parentPhone,courseName,paymentAmount,paymentModeName) {
    var nowDate = new Date();
    //初始化打印机
    var LODOP=getLodop();
    LODOP.PRINT_INIT("打印学生信息");               //首先一个初始化语句
    LODOP.ADD_PRINT_TEXT(0,20,100,20,"尚武道场");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(25,5,150,20,"--------------------------------");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(50,5,150,20,"学生姓名：" + name);//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(75,5,150,20,"手机号码：" + parentPhone);//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(100,5,150,20,"课程名称：" + courseName);//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(125,5,150,20,"缴费金额：" + paymentAmount + " RMB");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(150,5,150,20,"支付方式：" + paymentModeName);//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(175,5,150,20,"打印时间：" + nowDate.getFullYear() + "-" + nowDate.getMonth() + "-" + nowDate.getDate() + " " + nowDate.getHours() + ":" + nowDate.getMinutes() + ":" + nowDate.getSeconds());//然后多个ADD语句及SET语句
    // ADD_PRINT_TEXT(intTop,intLeft,intWidth,intHeight,strContent)增加纯文本项
    LODOP.ADD_PRINT_IMAGE(200,20,150,150,"<img border='0' src='../../resource/images/shangwuCode.jpg' width='150' height='150'/>"); //打印二维码
    // LODOP.SET_PRINT_STYLEA(0,"Stretch",2);//按原图比例(不变形)缩放模式
    LODOP.PRINT();                               //最后一个打印(或预览、维护、设计)语句
}

//获取课程下拉框的值
function getCourseSelect() {
    $.ajax({
        url : '/enroll/getCourseSelect.json',
        type : 'post',
        dataType : 'json',
        success : function(d) {
            if (d.success) {
                var options = d.couses;
                //遍历options数组
                //拼接options
                var selectOptions = "";
                for(var option in options) {
                    m.set(options[option].id,options[option].coursePrice)
                    selectOptions += "<option value='" + options[option].id + "'>" +  options[option].courseName + "</option>";
                }
                $("#courceSelect").append(selectOptions);
                //给缴费金额框赋默认值
                if (m.get(1)) {
                    $("#paymentAmount").val(m.get(1));
                }
            }
        },
        error:function (e) {

        }
    });
}

//下拉框选择事件
function selectOnchang() {
    //拿到select对象
    var myselect=document.getElementById("courceSelect");
    //拿到选中项的索引
    var index=myselect.selectedIndex ;
    //拿到选中项的value
    var value = myselect.options[index].value;
    //选中事件后，给下面的缴费金额赋值
    $("#paymentAmount").val(m.get(parseInt(value)));
}

//查看列表页面
function doStudentsList() {
    window.location.href="/shangwu/students/students.jsp";
}

//测试打印
function doTestPrint() {
    var nowDate = new Date();
    //初始化打印机
    var LODOP=getLodop();
    LODOP.PRINT_INIT("测试打印");               //首先一个初始化语句
    LODOP.ADD_PRINT_TEXT(0,20,100,20,"尚武道场");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(25,5,150,20,"--------------------------------");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(50,5,150,20,"学生姓名：诺贝尔爱情奖");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(75,5,150,20,"手机号码：17607195348");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(100,5,150,20,"课程名称：年卡");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(125,5,150,20,"缴费金额：3580 RMB");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(150,5,150,20,"支付方式：支付宝支付");//然后多个ADD语句及SET语句
    LODOP.ADD_PRINT_TEXT(175,5,150,20,"打印时间：" + nowDate.getFullYear() + "-" + nowDate.getMonth() + "-" + nowDate.getDate() + " " + nowDate.getHours() + ":" + nowDate.getMinutes() + ":" + nowDate.getSeconds());//然后多个ADD语句及SET语句
    // ADD_PRINT_TEXT(intTop,intLeft,intWidth,intHeight,strContent)增加纯文本项
    LODOP.ADD_PRINT_IMAGE(200,20,150,150,"<img border='0' src='../../resource/images/shangwuCode.jpg' width='150' height='150'/>"); //打印二维码
    LODOP.SET_PRINT_STYLEA(0,"Stretch",2);//按原图比例(不变形)缩放模式
    LODOP.PREVIEW();    //打印预览
    // LODOP.PRINT();                               //最后一个打印(或预览、维护、设计)语句
    /*$.ajax({
        url : '/enroll/testPring.json',
        type : 'post',
        dataType : 'json',
        success : function(d) {

        }
    });*/
}