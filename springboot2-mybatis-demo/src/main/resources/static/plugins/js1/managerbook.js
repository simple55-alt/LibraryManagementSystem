
$(function () {

    loadBookType();
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

function loadBookType() {
    $('#book_type_search').combobox({
        url: '/code/selectCodeList?codeKey=SYS-BOOK-KEY&codeParentId=-1',
        method : "get",
        valueField: 'codeId',
        textField: 'codeText',
        selected: 'selected',
        onSelect: function (r) {//选择事件

        },
        //添加请选择项
        loadFilter:function(data){
            var obj={};
            obj.codeId='';
            obj.codeText='--请选择--'
            data.splice(0,0,obj)//在数组0位置插入obj,不删除原来的元素
            return data;

        },
        //设置默认选中项
        onLoadSuccess: function () {
            $(this).combobox('setText', '--请选择--');
        }
    });

    $('#book_type_add').combobox({
        url: '/code/selectCodeList?codeKey=SYS-BOOK-KEY&codeParentId=-1',
        method : "get",
        valueField: 'codeId',
        textField: 'codeText',
        selected: 'selected',
        onLoadSuccess: function () {
            $(this).combobox('setText', '--请选择--');
        }
    });
    $('#book_type_edit').combobox({
        url: '/code/selectCodeList?codeKey=SYS-BOOK-KEY&codeParentId=-1',
        method : "get",
        valueField: 'codeId',
        textField: 'codeText',
        selected: 'selected',
        onLoadSuccess: function () {
            $(this).combobox('setText', '--请选择--');
        }
    });
}


function switchbutton(unitState) {
    var thisSwitchbuttonObj = $(".easyui-switchbutton").find("[switchbuttonName='bookDisFlag']");//获取switchbutton对象
    if(unitState){
        thisSwitchbuttonObj.switchbutton("check");
    }
    if(!unitState){
        thisSwitchbuttonObj.switchbutton("uncheck");
    }
}


function datagrid() {
    $('#datagrid').datagrid({
        url:'/book/selectBookList',
        pagination: true,//允许分页
        fitColumns: false,//列宽是否可调
        rownumbers: true,//行号
        singleSelect: true,//只选择一行
        pageSize: 10,//每一页数据数量
        pageList: [5, 10, 15, 20, 25],
        loadMsg: "正在努力加载数据，请稍后...",
        columns:[[
            {field:'bookCode',title:'编码',align:'center',width:'9%'},
            {field:'bookName',title:'书名',align:'center',width:'15%'},
            {field:'bookType',title:'类别',align:'center',width:'15%',formatter: formatBookType},
            {field:'bookAuthor',title:'作者',align:'center',width:'15%'},
            {field:'bookAddr',title:'书架',align:'center',width:'15%'},
            {field:'bookAmount',title:'单价',align:'center',width:'15%'},
            {field:'bookDisFlag',title:'是否折扣',align:'center',width:'10%',
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


function formatBookType(value,row) {

    console.log(row.sysCode.codeText)
    if (value == null || value == '') {
        return '';
    }else{
        return row.sysCode.codeText;
    }
}


//添加新用户
function newBooks(){
    //添加用户对话框
    $('#adddg').dialog('open').dialog('setTitle','添加书籍');
    //数据清空
    $('#fam').form('clear');

}

//用户信息修改
function editBooks(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改信息');
        //显示未修改前的用户信息
        $('#fim').form('load',row);
    }
}


//信息保存按钮事件
function saveBooks(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/book/updateBookInfo";
    } else{
        add="/book/updateBookInfo";
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
function addBooks(){
    var add="/book/addBook";
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
function removeBooks(){
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        // $.messager.defaults = {ok:"确定111",cancle:"取消222"};
        $.messager.confirm('提示框','确定要删除该信息?',function(r){
            if (r){
                $.post('/book/delBookInfo',{id:row.id},function(result){
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
    var url='/book/selectBookList';
    $('#datagrid').datagrid('options').url=url;
    $("#datagrid").datagrid('options').queryParams=query; //把查询条件赋值给datagrid内部变量
    $("#datagrid").datagrid('reload'); //重新加载
}

function clearForm() {
    $("#searchForm").form('clear');
}