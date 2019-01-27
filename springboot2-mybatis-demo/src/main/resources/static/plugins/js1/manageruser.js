
$(function () {
    //加载datagrid
    datagrid();
    //是否按钮
    $('.easyui-switchbutton').switchbutton({
        checked: true,
        onChange: function(checked){
            if (checked == true){
                switchbutton(true);
                return;
            }
            if (checked == false){
                switchbutton(false);
                return;
            }}
    });

})

function switchbutton(unitState) {
    var thisSwitchbuttonObj = $(".easyui-switchbutton").find("[switchbuttonName='userType']");//获取switchbutton对象
    if(unitState){
        thisSwitchbuttonObj.switchbutton("check");
    }
    if(!unitState){
        thisSwitchbuttonObj.switchbutton("uncheck");
    }
}


function datagrid() {
    $('#datagrid').datagrid({
        url:'/user/selectUserList',
        pagination: true,//允许分页
        fitColumns: false,//列宽是否可调
        rownumbers: true,//行号
        singleSelect: true,//只选择一行
        pageSize: 10,//每一页数据数量
        pageList: [5, 10, 15, 20, 25],
        loadMsg: "正在努力加载数据，请稍后...",
        columns:[[
            {field:'userNice',title:'昵称',align:'center',width:'9%'},
            {field:'userPhone',title:'手机号',align:'center',width:'15%'},
            {field:'userEmail',title:'邮箱',align:'center',width:'15%'},
            {field:'userCredit',title:'信用等级',align:'center',width:'15%'},
            {field:'userLevel',title:'会员等级',align:'center',width:'15%'},
            {field:'userType',title:'是否管理员',align:'center',width:'10%',
                formatter: function (val,row) {
                    if(val == 0){
                        return "否";
                    }else if(val == 1){
                        return "是";
                    }
                } },
            {field:'addTime',title:'添加时间',align:'center',width:'20%',
                formatter: formatDateTimebox }
        ]] ,

    });
}


//添加新用户
function newUsers(){
    //添加用户对话框
    $('#adddg').dialog('open').dialog('setTitle','添加用户');
    //数据清空
    $('#fam').form('clear');

}

//用户信息修改
function editUsers(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改信息');
        //显示未修改前的用户信息
        $('#fim').form('load',row);
    }
}


//信息保存按钮事件
function saveUsers(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/user/updateUserInfo";
    } else{
        add="/user/updateUserInfo";
    }

    $('#fim').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//用户信息添加按钮事件
function addUsers(){
    var add="/user/addUser";
    $('#fam').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}


//用户删除按钮事件
function removeUsers(){
    var row = $('#datagrid').datagrid('getSelected');
    debugger;
    if (row){
        $.messager.confirm('Confirm','确定要删除该用户?',function(r){
            if (r){
                $.post('/user/delUserInfo',{id:row.id},function(result){
                    if (result.success){

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                },'json');
            }
        });
    }
}

function submitForm() {
    var seachform=$('#searchForm').form();
    var query = serializeObject(seachform);
    $('#searchForm').form('submit',{
        onSubmit: function(){
            var isVali =  $(this).form('validate');
            if(isVali){
                reloadgrid(query);
            }else{
                $.messager("输入有误");
            }
        },
    });
}


function cleargrid() {
    $("#datagrid").datagrid("loadData", { total: 0, rows: [] });
}



function reloadgrid(query) {
    var url='/user/selectUserList';
    $('#datagrid').datagrid('options').url=url;
    $("#datagrid").datagrid('options').queryParams=query; //把查询条件赋值给datagrid内部变量
    $("#datagrid").datagrid('reload'); //重新加载
}

function clearForm() {
    $("#searchForm").form('clear');
}


