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
        //防止重复提交
        $("#submit").prop("disabled","disabled");
        //发送ajax请求
        $.ajax({
            url : '/enroll/enroll.json',
            type : 'post',
            data : {
                name : $("#name").val(),
                age : $("#age").val(),
                sex : $("input[name='sex']:checked").val(),
                parentPhone : $("#parentPhone").val(),
                address : $("#distpicker option:checked").text() + $("#address").val(),
                courseId:$("#courceSelect").val(),
                courseName:$("#courceSelect").find("option:selected").text(),
                paymentMode:$("#paymentMode").val(),
                paymentModeName:$("#paymentMode").find("option:selected").text(),
                paymentAmount:$("#paymentAmount").val()
            },
            dataType : 'json',
            success : function(d) {
                if (d.success) {
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