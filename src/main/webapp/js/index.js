layui.use('table', function(){
    var table = layui.table;

    // 表格渲染
    table.render({
        elem: '#applicationList'
        ,url:'/application/tblApplication/list'
        ,method:'post'
        ,contentType:'application/json'
        ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        ,cols: [[
            {field:'id', title: 'ID', sort: true}
            ,{field:'applicationName', title: '项目名称'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
            ,{field:'groupId', title: 'groupId', sort: true}
            ,{field:'artifactId', title: 'artifactId'}
            ,{field:'description', title: '描述'}
            ,{fixed: 'right', title:'操作', toolbar: '#barApplicationList'}
        ]]
        ,parseData: function(res){ //res 即为原始返回的数据
            return {
                "code": res.success ? 0 : 1, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.rows //解析数据列表
            };
        }
    });

    //监听工具条
    table.on('tool(applicationList)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令
            });
        }else if(layEvent === 'generate'){ //编辑
            //do something
            $.get("/template/generate",{applicationPrimaryKey:data.primaryKey},function(data,status){
                alert("数据: " + data + "\n状态: " + status);
            });
        } else if(layEvent === 'edit'){ //编辑
            //do something
            console.log("ahhaha");
            //同步更新缓存对应的值
            obj.update({
                username: '123'
                ,title: 'xxx'
            });
        }
    });
});