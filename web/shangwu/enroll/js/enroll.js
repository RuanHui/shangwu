
//提交方法
function doSubmit() {
    //提交之前校验
    var flag = verify();
    //发送ajax请求
    $.ajax({
        url : 'controller/reportCenter/indexWarn/delete.json',
        type : 'post',
        data : {
            id : selectedRow[0].id
        },
        dataType : 'json',
        success : function(d) {
            //更新成功之后刷新页面
            doSearch();
        },
        error:function (e) {
            $.messager.alert("提示","删除失败，请稍后重试！");
        }
    });
}

//校验方法
function verify() {

}