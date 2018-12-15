//定义一个map，用来装id和价格
var m = new Map();
//用来装查询姓名
var tempName;

//默认加载学生信息表
$(function () {
    //为课程下拉框获取值
    getCourseSelect();
    
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
} );

var TableInit = function () {
    var name = $("#name").val();
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#studentsTable').bootstrapTable({
            url: '/students/getStudentsList.json',         //请求后台的URL（*）
            /*method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式*/
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            clickToSelect: true,                //是否启用点击选中行
            method: 'get',
            toolbar: '#toolbar',
            striped: true,
            cache: false,
            pagination: true,
            sidePagination:"server",
            queryParamsType:"limit",
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 15, 20, 25],
            showRefresh: true,                  //是否显示刷新按钮
            singleSelect:true,
            queryParams:function(params){
                return {
                    limit:params.limit,
                    offset:params.offset,
                    name:params.search
                }
            },
            /*sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表*/
            columns: [
                {
                checkbox: true
            }, {
                field: 'id',
                hidden:true
            }, {
                field: 'name',
                title: '姓名',
                align:'center'
            }, {
                field: 'age',
                title: '年龄'
            }, {
                field: 'sex',
                title: '性别',
                align:'center',
                formatter(value,row,index){
                    if (value == 1) {
                        return "男";
                    } else {
                        return "女";
                    }
                }
            }, {
                field: 'courseName',
                title: '课程',
                align:'center'
            },{
                field: 'paymentAmount',
                title: '缴费金额',
                align:'center'
            }, {
                field: 'parentPhone',
                title: '父母电话',
                align:'center'
            },{
                field: 'address',
                title: '家庭住址',
                align:'center',
                    formatter(value,row,index) {
                        if (value != null && value != '') {
                            return row.province + row.city + row.district + value;
                        } else {
                            return "";
                        }
                    }
            },{
                field: 'insertTime',
                title: '记录时间',
                align:'center',
                    formatter(value,row,index) {
                        if (value != null && value != '') {
                            return value.substr(0,value.length - 2);
                        } else {
                            return "";
                        }
                    }
            },],
            loadMsg:'正在加载...',
            onLoadSuccess:function(data){
                console.log('data',data);
            },
            onLoadError : function(e) {
                console.log("查询学生列表发生错误:" + e);
            },
            responseHandler:function(res){
                return res;
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        tempName = params.search;
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            name: $("#name").val(),
            search:params.search
        };
        return temp;
    };
    return oTableInit;
};

var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };
    return oInit;
};

//打开修改窗口
function openEditModal() {
    //获取选择的行
    var row = $('#studentsTable').bootstrapTable('getSelections');
    if (row == null || row[0] == null) {
        alert("请先选中一条数据修改！");
        return ;
    }
    //给输入框填充数据
    setModalData(row[0]);
    //打开模态框
    $('#editModal').modal('show');
}

//给模态窗口填充数据
function setModalData(row) {
    //赋值ID
    $("#id").val(row.id);
    //填充姓名
    $("#name").val(row.name);
    //填充性别
    $("input[name='sex']").removeAttr("checked");
    $("input[name='sex'][value=" + row.sex + "]").attr("checked",true);
    //填充年龄
    $("#age").val(row.age);
    //填充电话
    $("#parentPhone").val(row.parentPhone);
    //填充地址
    //初始化省市区下拉框
    $('#distpicker').distpicker("destroy");
    $('#distpicker').distpicker({
        province: row.province,
        city: row.city,
        district: row.district
    });
    $("#address").val(row.address);
    //填充课程
    $("#courseSelect").val(row.courseId);
    //填充缴费金额
    $("#paymentAmount").val(row.paymentAmount);
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
                $("#courseSelect").append(selectOptions);
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

//提交修改
function doEdit() {
    //提交之前校验
    var flag = verify();

    $.ajax({
        url : '/students/updateStudent.json',
        type : 'post',
        dataType : 'json',
        data : {
            id:$("#id").val(),
            name : $("#name").val(),
            age : $("#age").val(),
            sex : $("input[name='sex']:checked").val(),
            parentPhone : $("#parentPhone").val(),
            province:$("#province").val(),
            city:$("#city").val(),
            district:$("#district").val(),
            //获取省市区 下拉框的值$("#distpicker option:checked").text()
            address : $("#address").val(),
            courseId:$("#courceSelect").val(),
            courseName:$("#courceSelect").find("option:selected").text(),
            // paymentMode:$("#paymentMode").val(),
            paymentModeName:$("#paymentMode").find("option:selected").text(),
            paymentAmount:$("#paymentAmount").val()
        },
        success : function(d) {
            if (d.success) {
                //提示修改成功
                alert("修改成功");
                //关闭模态框
                $('#editModal').modal('hide');
                //刷新页面
                var name = $("#name").val();
                $("#studentsTable").bootstrapTable("refresh",{query: {name: tempName}})
            }
        }
    });
}

//返回报名系统
function doBackEnroll() {
    window.location.href="/shangwu/enroll/enroll.jsp";
}



//下拉框选择事件
function selectOnchang() {
    //拿到select对象
    var myselect=document.getElementById("courseSelect");
    //拿到选中项的索引
    var index=myselect.selectedIndex ;
    //拿到选中项的value
    var value = myselect.options[index].value;
    //选中事件后，给下面的缴费金额赋值
    $("#paymentAmount").val(m.get(parseInt(value)));
}

//校验方法
function verify() {
    //校验姓名
    var name = $("#name").val();
    if (name == "" ) {
        //在姓名后面提示手机号错误
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