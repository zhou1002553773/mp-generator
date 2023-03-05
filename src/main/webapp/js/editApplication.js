layui.use(['table','layer','form'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;

    // 获取参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象\
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r!=null) return unescape(r[2]); return null; //返回参数值
    }
    let applicationPrimaryKey = getUrlParam('applicationPrimaryKey');

    // 查询application主表信息
    $.get("/application/tblApplication/detail",{primaryKey:applicationPrimaryKey},function(data,status){
        form.val("applicationForm", {
            "applicationName": data.data.applicationName,
            "groupId":data.data.groupId,
            "artifactId":data.data.artifactId,
            "packagePath":data.data.packagePath,
            "description":data.data.description,
            "databaseName":data.data.databaseName
        });
    });

    // 查询模块信息
    $.post("/application/tblApplicationSchema/list",{applicationPrimaryKey:applicationPrimaryKey},function(data,status){
        $("#")
    });

});